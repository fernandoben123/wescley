import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sala {
    private int idSala;
    private int capacidadeSala;
    private String descricao;
    private String status;

    public Sala(int idSala, int capacidadeSala, String descricao, String status) {
        this.idSala = idSala;
        this.capacidadeSala = capacidadeSala;
        this.descricao = descricao;
        this.status = status;
    }

    public int getIdSala() {
        return this.idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public int getCapacidadeSala() {
        return this.capacidadeSala;
    }

    public void setCapacidadeSala(int capacidadeSala) {
        this.capacidadeSala = capacidadeSala;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void detalhes() {
        System.out.println("ID Sala: " + this.idSala + ", Capacidade: " + this.capacidadeSala +
                ", Descrição: " + this.descricao + ", Status: " + this.status);
    }


    public boolean cadastrar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/salas.txt", true))) {
            writer.write(this.idSala + ";" + this.capacidadeSala + ";" +
                    this.descricao + ";" + this.status);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean editar() {
        List<Sala> salas = listar();
        boolean encontrado = false;

        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).getIdSala() == this.idSala) {
                salas.set(i, this);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/salas.txt"))) {
                for (Sala s : salas) {
                    writer.write(s.getIdSala() + ";" + s.getCapacidadeSala() + ";" +
                            s.getDescricao() + ";" + s.getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public Sala consultar(int idSala) {
        List<Sala> salas = listar();
        for (Sala sala : salas) {
            if (sala.getIdSala() == idSala) {
                return sala;
            }
        }
        return null;
    }


    public List<Sala> listar() {
        List<Sala> salas = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/salas.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int idSala = Integer.parseInt(dados[0]);
                int capacidadeSala = Integer.parseInt(dados[1]);
                String descricao = dados[2];
                String status = dados[3];
                salas.add(new Sala(idSala, capacidadeSala, descricao, status));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return salas;
    }

    @Override
    public String toString() {
        return "ID Sala: " + idSala + ", Capacidade: " + capacidadeSala +
                ", Descrição: " + descricao + ", Status: " + status;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Cadastro de Sala:");
        System.out.print("Digite o ID da sala: ");
        int idSala = scanner.nextInt();
        System.out.print("Digite a capacidade da sala: ");
        int capacidadeSala = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Digite a descrição da sala: ");
        String descricao = scanner.nextLine();
        System.out.print("Digite o status da sala: ");
        String status = scanner.nextLine();

        Sala sala = new Sala(idSala, capacidadeSala, descricao, status);
        if (sala.cadastrar()) {
            System.out.println("Sala cadastrada com sucesso.");
        }


        System.out.println("\nLista de salas:");
        List<Sala> salas = sala.listar();
        for (Sala s : salas) {
            s.detalhes();
        }


        System.out.println("\nEdição de Sala:");
        System.out.print("Digite o ID da sala para editar: ");
        int idSalaEditar = scanner.nextInt();
        scanner.nextLine(); 
        Sala salaConsultada = sala.consultar(idSalaEditar);
        if (salaConsultada != null) {
            System.out.print("Digite a nova capacidade da sala: ");
            int novaCapacidade = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite a nova descrição da sala: ");
            String novaDescricao = scanner.nextLine();
            System.out.print("Digite o novo status da sala: ");
            String novoStatus = scanner.nextLine();

            salaConsultada.setCapacidadeSala(novaCapacidade);
            salaConsultada.setDescricao(novaDescricao);
            salaConsultada.setStatus(novoStatus);

            if (salaConsultada.editar()) {
                System.out.println("Sala editada com sucesso.");
            }
        } else {
            System.out.println("Sala não encontrada.");
        }

        System.out.println("\nConsulta de Sala:");
        System.out.print("Digite o ID da sala para consultar: ");
        int idSalaConsultar = scanner.nextInt();
        Sala salaConsulta = sala.consultar(idSalaConsultar);
        if (salaConsulta != null) {
            salaConsulta.detalhes();
        } else {
            System.out.println("Sala não encontrada.");
        }

        scanner.close();
    }
}

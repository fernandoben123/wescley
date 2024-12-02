import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ator extends Pessoa {
    private int registro;

    public Ator(String cpf, String nome, String email, int registro){
        super(cpf,nome,email);
        this.registro = registro;
    }

    public void setRegistro (int registro){
        this.registro = registro;
    }

    public int getRegistro(){
        return this.registro;
    }

    public void exibirInfo(){
        System.out.println("Registro: " + this.registro + ", Nome: " + getNome() + ", CPF: " + getCpf() + ", Email: " + getEmail());
    }

    public boolean cadastrar(Ator ator) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/atores.txt", true))) {
            writer.write(ator.getRegistro() + ";" + ator.getNome() + ";" + ator.getCpf() + ";" + ator.getEmail());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Ator ator) {
        List<Ator> atores = listar();
        boolean encontrado = false;
        
        for (int i = 0; i < atores.size(); i++) {
            if (atores.get(i).getRegistro() == ator.getRegistro()) {
                atores.set(i, ator);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/atores.txt"))) {
                for (Ator a : atores) {
                    writer.write(a.getRegistro() + ";" + a.getNome() + ";" + a.getCpf() + ";" + a.getEmail());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Ator consultar(int registro) {
        List<Ator> atores = listar();
        for (Ator ator : atores) {
            if (ator.getRegistro() == registro) {
                return ator;
            }
        }
        return null;
    }

    public List<Ator> listar() {
        List<Ator> atores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/atores.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int registro = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String cpf = dados[2];
                String email = dados[3];
                atores.add(new Ator(cpf, nome, email, registro));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return atores;
    }

    @Override
    public String toString() {
        return "Registro: " + this.registro + ", Nome: " + getNome() + ", CPF: " + getCpf() + ", Email: " + getEmail();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Ator ator = new Ator("", "", "", 0);

        System.out.println("Cadastro de ator:");
        System.out.print("Digite o número de registro: ");
        int registro = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o nome do ator: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do ator: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o email do ator: ");
        String email = scanner.nextLine();

        Ator novoAtor = new Ator(cpf, nome, email, registro);
        if (ator.cadastrar(novoAtor)) {
            System.out.println("Ator cadastrado com sucesso.");
        }

        System.out.println("\nLista de atores:");
        List<Ator> atores = ator.listar();
        for (Ator a : atores) {
            a.exibirInfo();
        }

        System.out.println("\nEdição de ator:");
        System.out.print("Digite o número de registro do ator para editar: ");
        int registroEditar = scanner.nextInt();
        scanner.nextLine();
        Ator atorConsultado = ator.consultar(registroEditar);
        if (atorConsultado != null) {
            System.out.print("Digite o novo nome do ator: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo CPF do ator: ");
            String novoCpf = scanner.nextLine();
            System.out.print("Digite o novo email do ator: ");
            String novoEmail = scanner.nextLine();
            atorConsultado.setNome(novoNome);
            atorConsultado.setCpf(novoCpf);
            atorConsultado.setEmail(novoEmail);
            if (ator.editar(atorConsultado)) {
                System.out.println("Ator editado com sucesso.");
            }
        } else {
            System.out.println("Ator não encontrado.");
        }

        System.out.println("\nConsulta de ator:");
        System.out.print("Digite o número de registro para consultar: ");
        int registroConsultar = scanner.nextInt();
        Ator atorConsulta = ator.consultar(registroConsultar);
        if (atorConsulta != null) {
            atorConsulta.exibirInfo();
        } else {
            System.out.println("Ator não encontrado.");
        }

        scanner.close();
    }
}
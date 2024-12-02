import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Genero{
    private int id;
    private String descricao;
    private String status;

    public Genero(int id, String descricao, String status){
        this.id = id;
        this.descricao= descricao;
        this.status = status;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public int getId(){
        return this.id;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public String getStatus(){
        return this.status;
    }

    public void detalhes(){
        System.out.println("id: " + this.id + ", desc: " + this.descricao + ", status: " + this.status);
    }

    public Genero buscarGenero(String desc) {
        Genero a = new Genero(0, desc, "");
        try (FileReader fr = new FileReader("bd/genero.txt");
            BufferedReader reader = new BufferedReader(fr)) {
            
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (desc.equals(dados[1])) {
                    a = new Genero(Integer.parseInt(dados[0]), dados[1], dados[2]);
                    break;
                }
            }
            return a;
    
        } catch (IOException e) {
            e.printStackTrace();
            return a;
        }
    }
    
    public void cadastrar() {
        try (FileWriter fw = new FileWriter("bd/genero.txt", true);
            BufferedWriter writer = new BufferedWriter(fw)) {
            
            if (buscarGenero(this.descricao).id == 0) {
                writer.write(this.getId() + ";" + this.getDescricao() + ";" + this.getStatus());
                writer.newLine();
                System.out.println("Dados do gênero gravado!");
            } else {
                System.out.println("Gênero já existe");
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Genero> listar() {
        List<Genero> generos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/genero.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int id = Integer.parseInt(dados[0]);
                String descricao = dados[1];
                String status = dados[2];
                generos.add(new Genero(id,descricao,status));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return generos;
    }

    public boolean editar(Genero genero) {
        List<Genero> generos = listar(); 
        boolean encontrado = false;
        
        
        for (int i = 0; i < generos.size(); i++) {
            if (generos.get(i).getId() == genero.getId()) {
                generos.set(i, genero);
                encontrado = true;
                break;
            }
        }

        
        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/genero.txt"))) {
                for (Genero g : generos) {
                    writer.write(g.getId() + ";" + g.getDescricao() + ";" + g.getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

            @Override
            public String toString() {
            return "Genero: " +
                "id =" + this.id +
                ", descricao='" + this.descricao +
                ", status='" + this.status;
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
    
            Genero genero = new Genero(0, "", "");
            
    
            System.out.println("Digite a descrição do gênero: ");
            String descricao = scanner.nextLine();
    
            System.out.println("Digite o ID do gênero: ");
            int id = scanner.nextInt();
            scanner.nextLine();
    
            Genero novoGenero = new Genero(id, descricao, "A");
            novoGenero.cadastrar();
    
            System.out.println("\nLista de gêneros:");
            List<Genero> generos = genero.listar();
            for (Genero g : generos) {
                g.detalhes();
            }
    
            System.out.println("\nConsulta de gênero:");
            System.out.print("Digite a descrição do gênero para consultar: ");
            String descricaoConsultar = scanner.nextLine();
            Genero generoConsulta = genero.buscarGenero(descricaoConsultar);
            if (generoConsulta != null) {
                generoConsulta.detalhes();
            } else {
                System.out.println("Gênero não encontrado.");
            }
    
            System.out.println("\nEdição de gênero:");
            System.out.print("Digite o ID do gênero para editar: ");
            int idEditar = scanner.nextInt();
            scanner.nextLine();
            Genero generoEditar = null;
            for (Genero g : generos) {
                if (g.getId() == idEditar) {
                    generoEditar = g;
                    break;
                }
            }
    
            if (generoEditar != null) {
                System.out.print("Digite a nova descrição do gênero: ");
                String novoDescricao = scanner.nextLine();
                System.out.print("Digite o novo status do gênero: ");
                String novoStatus = scanner.nextLine();
    
                generoEditar.setDescricao(novoDescricao);
                generoEditar.setStatus(novoStatus);
    
                if (genero.editar(generoEditar)) {
                    System.out.println("Gênero editado com sucesso.");
                }
            } else {
                System.out.println("Gênero não encontrado.");
            }
    
            scanner.close();
        }
    }


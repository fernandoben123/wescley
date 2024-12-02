import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filme {
    private int idFilme;
    private String titulo;
    private int classificacao;
    private Genero genero; 
    private String status;

    public Filme(int idFilme, String titulo, int classificacao, Genero genero, String status) {
        this.idFilme = idFilme;
        this.titulo = titulo;
        this.classificacao = classificacao;
        this.genero = genero;
        this.status = status;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdFilme() {
        return this.idFilme;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public int getClassificacao() {
        return this.classificacao;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void exibirInfo() {
        System.out.println("ID Filme: " + this.idFilme + ", Título: " + this.titulo +
                ", Classificação: " + this.classificacao + ", Gênero: " + this.genero.getDescricao() + 
                ", Status: " + this.status);
    }

    public boolean cadastrar(Filme filme) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/filmes.txt", true))) {
            writer.write(filme.getIdFilme() + ";" + filme.getTitulo() + ";" + filme.getClassificacao() + ";" + 
                    filme.getGenero().getDescricao() + ";" + filme.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Filme filme) {
        List<Filme> filmes = listar();
        boolean encontrado = false;

        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getIdFilme() == filme.getIdFilme()) {
                filmes.set(i, filme);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/filmes.txt"))) {
                for (Filme f : filmes) {
                    writer.write(f.getIdFilme() + ";" + f.getTitulo() + ";" + f.getClassificacao() + ";" + 
                            f.getGenero().getDescricao() + ";" + f.getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Filme consultar(int idFilme) {
        List<Filme> filmes = listar();
        for (Filme filme : filmes) {
            if (filme.getIdFilme() == idFilme) {
                return filme;
            }
        }
        return null;
    }

    public List<Filme> listar() {
        List<Filme> filmes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/filmes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int idFilme = Integer.parseInt(dados[0]);
                String titulo = dados[1];
                int classificacao = Integer.parseInt(dados[2]);
                Genero genero = new Genero(0, dados[3], "Ativo");
                String status = dados[4];
                filmes.add(new Filme(idFilme, titulo, classificacao, genero, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    @Override
    public String toString() {
        return "ID Filme: " + this.idFilme + ", Título: " + this.titulo +
                ", Classificação: " + this.classificacao + ", Gênero: " + this.genero.getDescricao() +
                ", Status: " + this.status;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Filme filme = new Filme(0, "", 0, new Genero(0, "", ""), "");

    System.out.println("Cadastro de filme:");
    System.out.print("Digite o ID do filme: ");
    int idFilme = scanner.nextInt();
    scanner.nextLine();  
    System.out.print("Digite o título do filme: ");
    String titulo = scanner.nextLine();
    System.out.print("Digite a classificação do filme: ");
    int classificacao = scanner.nextInt();
    scanner.nextLine(); 
    System.out.print("Digite o gênero do filme: ");
    String generoDesc = scanner.nextLine();
    System.out.print("Digite o status do filme: ");
    String status = scanner.nextLine();

    Genero genero = new Genero(0, generoDesc, "Ativo");
    Filme novoFilme = new Filme(idFilme, titulo, classificacao, genero, status);

    if (filme.cadastrar(novoFilme)) {
        System.out.println("Filme cadastrado com sucesso.");
    }

    System.out.println("\nLista de filmes:");
    List<Filme> filmes = filme.listar();
    for (Filme f : filmes) {
        f.exibirInfo();
    }
        System.out.println("\nEdição de filme:");
        System.out.print("Digite o ID do filme para editar: ");
        int idFilmeEditar = scanner.nextInt();
        scanner.nextLine();
        Filme filmeConsultado = filme.consultar(idFilmeEditar);
        if (filmeConsultado != null) {
            System.out.print("Digite o novo título do filme: ");
            String novoTitulo = scanner.nextLine();
            System.out.print("Digite a nova classificação do filme: ");
            int novaClassificacao = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o novo gênero do filme: ");
            String novoGeneroDesc = scanner.nextLine();
            System.out.print("Digite o novo status do filme: ");
            String novoStatus = scanner.nextLine();
            
            Genero novoGenero = new Genero(0, novoGeneroDesc, "Ativo");
            filmeConsultado.setTitulo(novoTitulo);
            filmeConsultado.setClassificacao(novaClassificacao);
            filmeConsultado.setGenero(novoGenero);
            filmeConsultado.setStatus(novoStatus);

            if (filme.editar(filmeConsultado)) {
                System.out.println("Filme editado com sucesso.");
            }
        } else {
            System.out.println("Filme não encontrado.");
        }

        System.out.println("\nConsulta de filme:");
        System.out.print("Digite o ID do filme para consultar: ");
        int idFilmeConsultar = scanner.nextInt();
        Filme filmeConsulta = filme.consultar(idFilmeConsultar);
        if (filmeConsulta != null) {
            filmeConsulta.exibirInfo();
        } else {
            System.out.println("Filme não encontrado.");
        }

        scanner.close();
    }
}

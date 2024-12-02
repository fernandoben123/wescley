import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmeAtor {
    private int idFilmeAtor;
    private Ator ator;
    private Filme filme;
    private String personagem;
    private boolean principal;

    public FilmeAtor(int idFilmeAtor, Ator ator, Filme filme, String personagem, boolean principal) {
        this.idFilmeAtor = idFilmeAtor;
        this.ator = ator;
        this.filme = filme;
        this.personagem = personagem;
        this.principal = principal;
    }

    public void setIdFilmeAtor(int idFilmeAtor) {
        this.idFilmeAtor = idFilmeAtor;
    }

    public int getIdFilmeAtor() {
        return idFilmeAtor;
    }

    public void setAtor(Ator ator) {
        this.ator = ator;
    }

    public Ator getAtor() {
        return this.ator;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Filme getFilme() {
        return this.filme;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }
    
    public String getPersonagem() {
        return this.personagem;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
    
    public boolean isPrincipal() {
        return this.principal;
    }

    public void exibirInfo() {
        System.out.println("ID FilmeAtor: " + this.idFilmeAtor + 
                           ", Ator: " + ator.getNome() + 
                           ", Filme: " + filme.getTitulo() + 
                           ", Personagem: " + this.personagem + 
                           ", Principal: " + (this.principal ? "Sim" : "Não"));
    }

    public boolean cadastrar(FilmeAtor filmeAtor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/filmes_atores.txt", true))) {
            writer.write(filmeAtor.getIdFilmeAtor() + ";" + 
                         filmeAtor.getAtor().getRegistro() + ";" + 
                         filmeAtor.getFilme().getIdFilme() + ";" + 
                         filmeAtor.getPersonagem() + ";" + 
                         (filmeAtor.isPrincipal() ? "Sim" : "Não"));
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(FilmeAtor filmeAtor) {
        List<FilmeAtor> filmesAtores = listar();
        boolean encontrado = false;

        for (int i = 0; i < filmesAtores.size(); i++) {
            if (filmesAtores.get(i).getIdFilmeAtor() == filmeAtor.getIdFilmeAtor()) {
                filmesAtores.set(i, filmeAtor);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/filmes_atores.txt"))) {
                for (FilmeAtor fa : filmesAtores) {
                    writer.write(fa.getIdFilmeAtor() + ";" + 
                                 fa.getAtor().getRegistro() + ";" + 
                                 fa.getFilme().getIdFilme() + ";" + 
                                 fa.getPersonagem() + ";" + 
                                 (fa.isPrincipal() ? "Sim" : "Não"));
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public FilmeAtor consultar(int idFilmeAtor) {
        List<FilmeAtor> filmesAtores = listar();
        for (FilmeAtor fa : filmesAtores) {
            if (fa.getIdFilmeAtor() == idFilmeAtor) {
                return fa;
            }
        }
        return null;
    }

    public List<FilmeAtor> listar() {
        List<FilmeAtor> filmesAtores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/filmes_atores.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
    
                if (dados.length != 5) {
                    System.out.println("Erro: Dados inválidos no arquivo.");
                    continue;
                }
    
                try {
                    int idFilmeAtor = Integer.parseInt(dados[0]);
                    int registroAtor = Integer.parseInt(dados[1]);
                    int idFilme = Integer.parseInt(dados[2]);
                    String personagem = dados[3];
                    boolean principal = dados[4].equalsIgnoreCase("Sim");
    
                    Ator ator = new Ator("", "", "", registroAtor); 
                    Filme filme = new Filme(idFilme, "", 0, new Genero(0, "", ""), "");
    
                    filmesAtores.add(new FilmeAtor(idFilmeAtor, ator, filme, personagem, principal));
    
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato numérico no arquivo: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return filmesAtores;
    }
    

    @Override
    public String toString() {
        return "ID FilmeAtor: " + this.idFilmeAtor + ", Ator: " + ator.getNome() +
               ", Filme: " + filme.getTitulo() + ", Personagem: " + personagem + 
               ", Principal: " + (this.principal ? "Sim" : "Não");
    }

}
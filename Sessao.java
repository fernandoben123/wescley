import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sessao {
    private int idSessao;
    private Filme filme;
    private Sala sala;
    private Funcionario funcionario;
    private String dataHora;
    private String status;

    public Sessao(int idSessao, Filme filme, Sala sala, Funcionario funcionario, String dataHora, String status) {
        this.idSessao = idSessao;
        this.filme = filme;
        this.sala = sala;
        this.funcionario = funcionario;
        this.dataHora = dataHora;
        this.status = status;
    }

    public void setIdSessao(int idSessao) {
        this.idSessao = idSessao;
    }

    public int getIdSessao() {
        return idSessao;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Filme getFilme() {
        return this.filme;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Sala getSala() {
        return sala;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }


    public void detalhes() {
        System.out.println("ID Sessão: " + idSessao + ", Filme: " + filme.getTitulo() + 
                           ", Sala: " + sala.getIdSala() + ", Funcionario" + funcionario.getMatricula() + ", Data e Hora: " + dataHora + 
                           ", Status: " + status);
    }


    public boolean cadastrar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/sessoes.txt", true))) {
            writer.write(this.idSessao + ";" + this.filme.getIdFilme() + ";" + this.sala.getIdSala() + ";" + this.funcionario.getMatricula() + ";" + this.dataHora + ";" + this.status);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean editar() {
        List<Sessao> sessoes = listar();
        boolean encontrado = false;

        for (int i = 0; i < sessoes.size(); i++) {
            if (sessoes.get(i).getIdSessao() == this.idSessao) {
                sessoes.set(i, this);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/sessoes.txt"))) {
                for (Sessao s : sessoes) {
                    writer.write(s.getIdSessao() + ";" + s.getFilme().getIdFilme() + ";" +
                                 s.getSala().getIdSala() + ";"+
                                 s.getFuncionario().getMatricula() + ";" + s.getDataHora() + ";" + s.getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public Sessao consultar(int idSessao) {
        List<Sessao> sessoes = listar();
        for (Sessao sessao : sessoes) {
            if (sessao.getIdSessao() == idSessao) {
                return sessao;
            }
        }
        return null;
    }


    public List<Sessao> listar() {
        List<Sessao> sessoes = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/sessoes.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int idSessao = Integer.parseInt(dados[0]);
                int idFilme = Integer.parseInt(dados[1]);
                int idSala = Integer.parseInt(dados[2]);
                int matricula = Integer.parseInt(dados[3]);
                String dataHora = dados[4];
                String status = dados[5];

              Filme filme = new Filme(idFilme, "", 0, null, "");
              Sala sala = new Sala(idSala, 0, "", "");
              Funcionario funcionario = new Funcionario("", "", "", matricula, "");
                

                sessoes.add(new Sessao(idSessao, filme, sala, funcionario, dataHora, status));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sessoes;
    }

    @Override
    public String toString() {
        return "ID Sessão: " + idSessao + ", Filme: " + filme.getTitulo() + ", Sala: " + sala.getIdSala() +  ", Funcionario" + funcionario.getMatricula() + 
               ", Data e Hora: " + dataHora + ", Status: " + status;
    }


    
}

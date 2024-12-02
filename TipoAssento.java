import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TipoAssento {
    private int idTipoAssento;
    private String descricao;
    private String status;

    public TipoAssento(int idTipoAssento, String descricao, String status) {
        this.idTipoAssento = idTipoAssento;
        this.descricao = descricao;
        this.status = status;
    }

    public void setIdTipoAssento(int idTipoAssento) {
        this.idTipoAssento = idTipoAssento;
    }
    
    public int getIdTipoAssento() {
        return this.idTipoAssento;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void exibirInfo() {
        System.out.println("ID TipoAssento: " + this.idTipoAssento + 
                           ", Descrição: " + this.descricao + 
                           ", Status: " + this.status);
    }

    public boolean cadastrar(TipoAssento tipoAssento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/tipo_assentos.txt", true))) {
            writer.write(tipoAssento.getIdTipoAssento() + ";" + 
                         tipoAssento.getDescricao() + ";" + 
                         tipoAssento.getStatus());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(TipoAssento tipoAssento) {
        List<TipoAssento> tipoAssentos = listar();
        boolean encontrado = false;

        for (int i = 0; i < tipoAssentos.size(); i++) {
            if (tipoAssentos.get(i).getIdTipoAssento() == tipoAssento.getIdTipoAssento()) {
                tipoAssentos.set(i, tipoAssento);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/tipo_assentos.txt"))) {
                for (TipoAssento ta : tipoAssentos) {
                    writer.write(ta.getIdTipoAssento() + ";" + 
                                 ta.getDescricao() + ";" + 
                                 ta.getStatus());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public TipoAssento consultar(int idTipoAssento) {
        List<TipoAssento> tipoAssentos = listar();
        for (TipoAssento ta : tipoAssentos) {
            if (ta.getIdTipoAssento() == idTipoAssento) {
                return ta;
            }
        }
        return null;
    }

    public List<TipoAssento> listar() {
        List<TipoAssento> tipoAssentos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/tipo_assentos.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int idTipoAssento = Integer.parseInt(dados[0]);
                String descricao = dados[1];
                String status = dados[2];

                tipoAssentos.add(new TipoAssento(idTipoAssento, descricao, status));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tipoAssentos;
    }

    @Override
    public String toString() {
        return "ID TipoAssento: " + this.idTipoAssento + ", Descrição: " + this.descricao + ", Status: " + this.status;
    }

}

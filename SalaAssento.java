import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalaAssento {
    private int idSalaAssento;
    private Sala sala;
    private Assento assento;

    public SalaAssento(int idSalaAssento, Sala sala, Assento assento) {
        this.idSalaAssento = idSalaAssento;
        this.sala = sala;
        this.assento = assento;
    }

    public void setIdSalaAssento(int idSalaAssento) {
        this.idSalaAssento = idSalaAssento;
    }

    public int getIdSalaAssento() {
        return this.idSalaAssento;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Sala getSala() {
        return this.sala;
    }

    public Assento getAssento() {
        return assento;
    }

    public void setAssento(Assento assento) {
        this.assento = assento;
    }

    public void detalhes() {
        System.out.println("ID Relacionamento: " + idSalaAssento + ", Sala: " + sala.getIdSala() + 
                           ", Assento: " + assento.getIdAssento());
    }

    public boolean cadastrar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/sala_assento.txt", true))) {
            writer.write(this.idSalaAssento + ";" + this.sala.getIdSala() + ";" + this.assento.getIdAssento());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar() {
        List<SalaAssento> salaAssentos = listar();
        boolean encontrado = false;

        for (int i = 0; i < salaAssentos.size(); i++) {
            if (salaAssentos.get(i).getIdSalaAssento() == this.idSalaAssento) {
                salaAssentos.set(i, this);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/sala_assento.txt"))) {
                for (SalaAssento sa : salaAssentos) {
                    writer.write(sa.getIdSalaAssento() + ";" + sa.getSala().getIdSala() + ";" + sa.getAssento().getIdAssento());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public SalaAssento consultar(int idSalaAssento) {
        List<SalaAssento> salaAssentos = listar();
        for (SalaAssento sa : salaAssentos) {
            if (sa.getIdSalaAssento() == idSalaAssento) {
                return sa;
            }
        }
        return null;
    }

    public List<SalaAssento> listar() {
        List<SalaAssento> salaAssentos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/sala_assento.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int idSalaAssento = Integer.parseInt(dados[0]);
                Sala sala = new Sala(idSalaAssento, idSalaAssento, null, null);
                Assento assento = new Assento(idSalaAssento, null);
                salaAssentos.add(new SalaAssento(idSalaAssento, sala, assento));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return salaAssentos;
    }

    @Override
    public String toString() {
        return "ID SalaAssento: " + idSalaAssento + ", Sala: " + sala.getIdSala() + ", Assento: " + assento.getIdAssento();
    }


}

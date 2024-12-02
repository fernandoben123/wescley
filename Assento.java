import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assento {
    private int idAssento;
    private TipoAssento tipoAssento;

    public Assento(int idAssento, TipoAssento tipoAssento) {
        this.idAssento = idAssento;
        this.tipoAssento = tipoAssento;
    }

    public void setIdAssento(int idAssento) {
        this.idAssento = idAssento;
    }

    public int getIdAssento() {
        return this.idAssento;
    }

    public void setTipoAssento(TipoAssento tipoAssento) {
        this.tipoAssento = tipoAssento;
    }

    public TipoAssento getTipoAssento() {
        return this.tipoAssento;
    }

    public void exibirInfo() {
        System.out.println("ID Assento: " + this.idAssento);
        if (this.tipoAssento != null) {
            this.tipoAssento.exibirInfo();
        } else {
            System.out.println("Tipo de assento não encontrado.");
        }
    }

    public boolean cadastrar(Assento assento) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/assentos.txt", true))) {
            writer.write(assento.getIdAssento() + ";" + assento.getTipoAssento().getIdTipoAssento());
            writer.newLine();
            System.out.println("Assento cadastrado com sucesso!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Assento assento) {
        ArrayList<Assento> assentos = listar();
        boolean encontrado = false;

        for (int i = 0; i < assentos.size(); i++) {
            if (assentos.get(i).getIdAssento() == assento.getIdAssento()) {
                assentos.set(i, assento);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/assentos.txt"))) {
                for (Assento a : assentos) {
                    writer.write(a.getIdAssento() + ";" + a.getTipoAssento().getIdTipoAssento());
                    writer.newLine();
                }
                System.out.println("Assento editado com sucesso!");
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Assento não encontrado.");
        }
        return false;
    }

    public Assento consultar(int idAssento) {
        try (Scanner scanner = new Scanner(new File("bd/assentos.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                if (dados.length == 2) {
                    int id = Integer.parseInt(dados[0]);
                    if (id == idAssento) {
                        int idTipoAssento = Integer.parseInt(dados[1]);
                        TipoAssento tipoAssento = new TipoAssento(0, "", "").consultar(idTipoAssento);
                        return new Assento(id, tipoAssento);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Assento> listar() {
        ArrayList<Assento> assentos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/assentos.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                if (dados.length == 2) {
                    int idAssento = Integer.parseInt(dados[0]);
                    int idTipoAssento = Integer.parseInt(dados[1]);
                    TipoAssento tipoAssento = new TipoAssento(0, "", "").consultar(idTipoAssento);
                    assentos.add(new Assento(idAssento, tipoAssento));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return assentos;
    }

        @Override
        public String toString() {
            return "Assento { " +
                "idAssento=" + idAssento +
                ", tipoAssento=" + (tipoAssento != null ? tipoAssento.toString() : "Tipo de assento não definido") +
                " }";
        }



}

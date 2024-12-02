import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ingresso {
    private int idIngresso;
    private double valorPago;
    private SalaAssento salaAssento;
    private Sessao sessao;

    public Ingresso(int idIngresso, double valorPago, SalaAssento salaAssento, Sessao sessao) {
        this.idIngresso = idIngresso;
        this.valorPago = valorPago;
        this.salaAssento = salaAssento;
        this.sessao = sessao;
    }

    public int getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public SalaAssento getSalaAssento() {
        return salaAssento;
    }

    public void setSalaAssento(SalaAssento salaAssento) {
        this.salaAssento = salaAssento;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public boolean cadastrar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/ingressos.txt", true))) {
            writer.write(this.idIngresso + ";" + this.valorPago + ";" + this.salaAssento.getIdSalaAssento() +
                          ";" + this.sessao.getIdSessao());
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar() {
        List<Ingresso> ingressos = listar();
        boolean encontrado = false;

        for (int i = 0; i < ingressos.size(); i++) {
            if (ingressos.get(i).getIdIngresso() == this.idIngresso) {
                ingressos.set(i, this);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/ingressos.txt"))) {
                for (Ingresso ingresso : ingressos) {
                    writer.write(ingresso.getIdIngresso() + ";" + ingresso.getValorPago() + ";" +
                                 ingresso.getSalaAssento().getIdSalaAssento() + ";" + ingresso.getSessao().getIdSessao());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Ingresso consultar(int idIngresso) {
        List<Ingresso> ingressos = listar();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getIdIngresso() == idIngresso) {
                return ingresso;
            }
        }
        return null;
    }

    public List<Ingresso> listar() {
        List<Ingresso> ingressos = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/ingressos.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int idIngresso = Integer.parseInt(dados[0]);
                double valorPago = Double.parseDouble(dados[1]);
                int idSalaAssento = Integer.parseInt(dados[2]);
                int idSessao = Integer.parseInt(dados[3]);

                SalaAssento salaAssento = new SalaAssento(idSalaAssento, null, null); 
                Sessao sessao = new Sessao(idSessao, null, null, null, "", "");  

                ingressos.add(new Ingresso(idIngresso, valorPago, salaAssento, sessao));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingressos;
    }

    public void detalhes() {
        System.out.println("ID Ingresso: " + idIngresso + ", Valor Pago: " + valorPago +
                     ", idSalaAssento: " + salaAssento.getIdSalaAssento() +
                           ", Sessão ID: " + sessao.getIdSessao());
    }

    @Override
    public String toString() {
        return "ID Ingresso: " + idIngresso + ", Valor Pago: " + valorPago + 
               ", idSalaAssento ID: " + salaAssento.getIdSalaAssento() + ", Sessão ID: " + sessao.getIdSessao();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("Cadastro de Ingresso:");
        System.out.print("Digite o ID do Ingresso: ");
        int idIngresso = scanner.nextInt();
        System.out.print("Digite o valor pago: ");
        double valorPago = scanner.nextDouble();
        System.out.print("Digite o ID da SalaAssento: ");
        int idSalaAssento= scanner.nextInt();
        System.out.print("Digite o ID da Sessão: ");
        int idSessao = scanner.nextInt();

        SalaAssento salaAssento = new SalaAssento(idSalaAssento, null, null); 
        Sessao sessao = new Sessao(idSessao, null, null, null, "", ""); 

        Ingresso ingresso = new Ingresso(idIngresso, valorPago, salaAssento, sessao);
        if (ingresso.cadastrar()) {
            System.out.println("Ingresso cadastrado com sucesso.");
        }

        
        System.out.println("\nLista de ingressos:");
        List<Ingresso> ingressos = ingresso.listar();
        for (Ingresso ing : ingressos) {
            ing.detalhes();
        }

        
        System.out.println("\nEdição de Ingresso:");
        System.out.print("Digite o ID do Ingresso para editar: ");
        int idIngressoEditar = scanner.nextInt();
        Ingresso ingressoConsultado = ingresso.consultar(idIngressoEditar);
        if (ingressoConsultado != null) {
            System.out.print("Digite o novo valor pago: ");
            double novoValorPago = scanner.nextDouble();
            ingressoConsultado.setValorPago(novoValorPago);
            if (ingressoConsultado.editar()) {
                System.out.println("Ingresso editado com sucesso.");
            }
        } else {
            System.out.println("Ingresso não encontrado.");
        }

        
        System.out.println("\nConsulta de Ingresso:");
        System.out.print("Digite o ID do Ingresso para consultar: ");
        int idIngressoConsultar = scanner.nextInt();
        Ingresso ingressoConsulta = ingresso.consultar(idIngressoConsultar);
        if (ingressoConsulta != null) {
            ingressoConsulta.detalhes();
        } else {
            System.out.println("Ingresso não encontrado.");
        }

        scanner.close();
    }
}

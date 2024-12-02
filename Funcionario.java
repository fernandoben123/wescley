import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funcionario extends Pessoa {
    private int matricula;
    private String horarioTrabalho;

    public Funcionario(String nome, String cpf, String email, int matricula, String horarioTrabalho) {
        super(nome, cpf, email);
        this.matricula = matricula;
        this.horarioTrabalho = horarioTrabalho;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getMatricula() {
        return this.matricula;
    }

    public void setHorarioTrabalho(String horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }

    public String getHorarioTrabalho() {
        return this.horarioTrabalho;
    }

    public void detalhes() {
        System.out.println("Matrícula: " + matricula + ", Nome: " + getNome() + ", CPF: " + getCpf() +
                ", Email: " + getEmail() + ", Horário de Trabalho: " + horarioTrabalho);
    }

    public boolean cadastrar() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/funcionarios.txt", true))) {
            writer.write(this.matricula + ";" + this.getNome() + ";" + this.getCpf() + ";" + this.getEmail() + ";" + this.horarioTrabalho);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean editar() {
        List<Funcionario> funcionarios = listar();
        boolean encontrado = false;

        for (int i = 0; i < funcionarios.size(); i++) {
            if (funcionarios.get(i).getMatricula() == this.matricula) {
                funcionarios.set(i, this);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("bd/funcionarios.txt"))) {
                for (Funcionario f : funcionarios) {
                    writer.write(f.getMatricula() + ";" + f.getNome() + ";" +
                            f.getCpf() + ";" + f.getEmail() + ";" +
                            f.getHorarioTrabalho());
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public Funcionario consultar(int matricula) {
        List<Funcionario> funcionarios = listar();
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                return funcionario;
            }
        }
        return null;
    }


    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("bd/funcionarios.txt"))) {
            while (scanner.hasNextLine()) {
                String[] dados = scanner.nextLine().split(";");
                int matricula = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String cpf = dados[2];
                String email = dados[3];
                String horarioTrabalho = dados[4];
                funcionarios.add(new Funcionario(nome, cpf, email, matricula, horarioTrabalho));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return funcionarios;
    }

    @Override
    public String toString() {
        return "Matrícula: " + matricula + ", Nome: " + getNome() + ", CPF: " + getCpf() +
                ", Email: " + getEmail() + ", Horário de Trabalho: " + horarioTrabalho;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Cadastro de funcionário:");
        System.out.print("Digite o número de matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Digite o nome do funcionário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do funcionário: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o email do funcionário: ");
        String email = scanner.nextLine();
        System.out.print("Digite o horário de trabalho (HH:mm): ");
        String horario = scanner.nextLine();

        Funcionario funcionario = new Funcionario(nome, cpf, email, matricula, horario);
        if (funcionario.cadastrar()) {
            System.out.println("Funcionário cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar funcionário.");
        }

        System.out.println("\nLista de funcionários:");
        List<Funcionario> funcionarios = funcionario.listar();
        for (Funcionario f : funcionarios) {
            f.detalhes();
        }

    
        System.out.println("\nEdição de funcionário:");
        System.out.print("Digite o número de matrícula do funcionário para editar: ");
        int matriculaEditar = scanner.nextInt();
        scanner.nextLine();
        Funcionario funcionarioConsultado = funcionario.consultar(matriculaEditar);
        if (funcionarioConsultado != null) {
            System.out.print("Digite o novo nome do funcionário: ");
            String novoNome = scanner.nextLine();
            System.out.print("Digite o novo CPF do funcionário: ");
            String novoCpf = scanner.nextLine();
            System.out.print("Digite o novo email do funcionário: ");
            String novoEmail = scanner.nextLine();
            System.out.print("Digite o novo horário de trabalho: ");
            String novoHorario = scanner.nextLine();

            funcionarioConsultado.setNome(novoNome);
            funcionarioConsultado.setCpf(novoCpf);
            funcionarioConsultado.setEmail(novoEmail);
            funcionarioConsultado.setHorarioTrabalho(novoHorario);

            if (funcionarioConsultado.editar()) {
                System.out.println("Funcionário editado com sucesso.");
            } else {
                System.out.println("Erro ao editar funcionário.");
            }
        } else {
            System.out.println("Funcionário não encontrado.");
        }


        System.out.println("\nConsulta de funcionário:");
        System.out.print("Digite o número de matrícula para consultar: ");
        int matriculaConsultar = scanner.nextInt();
        Funcionario funcionarioConsulta = funcionario.consultar(matriculaConsultar);
        if (funcionarioConsulta != null) {
            funcionarioConsulta.detalhes();
        } else {
            System.out.println("Funcionário não encontrado.");
        }

        scanner.close();
    }
}

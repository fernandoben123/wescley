public abstract class Pessoa {
    private String cpf;
    private String nome;
    private String email;

    public Pessoa(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "Pessoa: " +
               "cpf ='" + this.cpf +
               ", nome ='" + this.nome + 
               ", email ='" + this.email;
    }
}
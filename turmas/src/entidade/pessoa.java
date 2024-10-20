package entidade;

public class pessoa {

    private int idPessoa;
    private String nome;
    private int idade;

    public pessoa() {
    }

    public pessoa(int idPessoa, String nome, int idade) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.idade = idade;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
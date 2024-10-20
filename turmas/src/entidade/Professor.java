package entidade;

public class Professor extends pessoa {
    private int idProfessor;
    private double salario;

    public Professor() {
        super();
    }

    public Professor(int idPessoa, String nome, int idade, int idProfessor, double salario) {
        super(idPessoa, nome, idade);
        this.idProfessor = idProfessor;
        this.salario = salario;
    }

    public Professor(int idProfessor, double salario) {
        this.idProfessor = idProfessor;
        this.salario = salario;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
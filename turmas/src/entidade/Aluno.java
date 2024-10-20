package entidade;

public class Aluno extends pessoa {

    private int idAluno;
    private double nota;
    private int Turma_idTurma;

    public Aluno() {
        super();
    }

    public Aluno(int idPessoa, String nome, int idade, int idAluno, double nota, int turma_idTurma) {
        super(idPessoa, nome, idade);
        this.idAluno = idAluno;
        this.nota = nota;
        Turma_idTurma = turma_idTurma;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public int getTurma_idTurma() {
        return Turma_idTurma;
    }

    public void setTurma_idTurma(int turma_idTurma) {
        Turma_idTurma = turma_idTurma;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}

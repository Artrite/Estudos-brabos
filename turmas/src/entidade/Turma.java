package entidade;
import java.util.List;

public class Turma {
    private int id_turma;
    private String nome;
    private int id_Professor;
    private List<Aluno> Alunos;

    public Turma(int id_turma, String nome, int id_Professor, List<Aluno> Alunos) {
        this.id_turma = id_turma;
        this.nome = nome;
        this.id_Professor = id_Professor;
        this.Alunos = Alunos;
    }
    public Turma() {
    }

    public static void setTurma_idTurma(int turmaIdtTurma) {

    }

    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        this.nome = nome;
    }

    public int getId_Professor() {
        return id_Professor;
    }

    public void setId_Professor(int id_Professor) {
        this.id_Professor = id_Professor;
    }

    public List<Aluno> getAlunos() {
        return Alunos;
    }

    public void setAlunos(List<Aluno> Alunos) {
        this.Alunos = Alunos;
    }

    public void setIdTurma(int idTurma) {
    }
}
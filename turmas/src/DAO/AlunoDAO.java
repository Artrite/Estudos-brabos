package DAO;
import entidade.Aluno;
import utilidade.ConnectionMYSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public Aluno createAluno(Aluno aluno) {
        try {
            Connection conn = ConnectionMYSQL.openConnection();
            //Inserir pessoa para posteriormente inserir o aluno
            String sql = "INSERT INTO pessoa (nome, idade) VALUES (?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, aluno.getNome());
            statement.setInt(2, aluno.getIdade());
            statement.executeUpdate();

            //Achar pessoa pelo ID
            String sql2 = "SELECT * FROM pessoa WHERE nome= ? and idade= ?  LIMIT 1";

            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, aluno.getNome());
            statement2.setInt(2, aluno.getIdade());
            ResultSet resultSet = statement2.executeQuery();

            int idPessoa = -1;
            while (resultSet.next()) {
                idPessoa = resultSet.getInt("idPessoa");
            }
            //Inserir o aluno

            String sqlAluno = "INSERT INTO aluno (Pessoa_idPessoa, Turma_idTurma) VALUES (?,?)";
            PreparedStatement statementAluno = conn.prepareStatement(sqlAluno);
            statementAluno.setInt(1, idPessoa);
            statementAluno.setInt(2, aluno.getTurma_idTurma());

            statementAluno.executeUpdate();

            ConnectionMYSQL.closeConnection();

            System.out.println("Aluno criado");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar aluno " + e.getMessage());
        }
        return aluno;
    }

    public List<Aluno> findall() {
        List<Aluno> objects = new ArrayList<Aluno>();

        try {
            Connection conn = ConnectionMYSQL.openConnection();

            String sqlfindall = "SELECT * FROM aluno join pessoa on pessoa_idPessoa = aluno_idPessoa";
            PreparedStatement statement = conn.prepareStatement(sqlfindall);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setTurma_idTurma(rs.getInt("Turma_idtTurma"));
                aluno.setNota(rs.getInt("nota"));
                aluno.setIdAluno(rs.getInt("idAluno"));
                objects.add(aluno);
            }
            ConnectionMYSQL.closeConnection();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos " + e.getMessage());
        }
        return objects;
    }

    public Aluno findById(int id) {
        Aluno aluno = new Aluno();
        try {
            Connection conn = ConnectionMYSQL.openConnection();

            String sqlfindById = "SELECT * FROM aluno join pessoa on aluno.pessoa_idPessoa = pessoa.idPessoa " +
                    "where aluno.pessoaidPessoa = ?";
            PreparedStatement statement = conn.prepareStatement(sqlfindById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                aluno.setNome(rs.getString("nome"));
                aluno.setIdade(rs.getInt("idade"));
                aluno.setTurma_idTurma(rs.getInt("Turma_idtTurma"));
                aluno.setNota(rs.getInt("nota"));
                aluno.setIdAluno(rs.getInt("idAluno"));
            }

            ConnectionMYSQL.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno " + e.getMessage());
        }
        return aluno;
    }
    public void deleteALuno(int id){
        try {
            Connection conn = ConnectionMYSQL.openConnection();
            String sqldelete = "DELETE FROM aluno WHERE pessoaidPessoa = ?";
            PreparedStatement statement = conn.prepareStatement(sqldelete);
            statement.setInt(1, id);
            int afetados = statement.executeUpdate();

            if (afetados > 0) {
                System.out.println("Aluno removido com sucesso");
            }
            ConnectionMYSQL.closeConnection();


        } catch (SQLException e) {
            System.out.println("Erro ao remover aluno " + e.getMessage());
        }
    }
    public void darNota(int id, double nota) {
        try {
            Connection conn = ConnectionMYSQL.openConnection();
            String Nota = "UPDATE aluno SET nota = ? WHERE idAluno = ?";

            PreparedStatement statement = conn.prepareStatement(Nota);
            statement.setDouble(1, nota);
            statement.setInt(2, id);
            int afetados = statement.executeUpdate();

            if (afetados > 0) {
                System.out.println("Aluno atualizado com sucesso");
            }
            ConnectionMYSQL.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno " + e.getMessage());
        }
    }
}
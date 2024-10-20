package DAO;

import entidade.Aluno;
import entidade.Professor;
import utilidade.ConnectionMYSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    public Professor createProfessor(Professor professor) {
        try {
            Connection conn = ConnectionMYSQL.openConnection();
            //Inserir pessoa para posteriormente inserir o professor
            String sql = "INSERT INTO pessoa (nome, idade) VALUES (?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, professor.getNome());
            statement.setInt(2, professor.getIdade());
            statement.executeUpdate();

            //Achar pessoa pelo ID
            String sql2 = "SELECT * FROM pessoa WHERE nome= ? and idade= ?  LIMIT 1";

            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, professor.getNome());
            statement2.setInt(2, professor.getIdade());
            ResultSet resultSet = statement2.executeQuery();

            int idPessoa = -1;
            while (resultSet.next()) {
                idPessoa = resultSet.getInt("idPessoa");
            }
            //Inserir o professor

            String sqlprofessor = "INSERT INTO aluno (Salario, Pessoa_idPessoa) VALUES (?,?)";
            PreparedStatement statement3 = conn.prepareStatement(sqlprofessor);
            statement3.setDouble(1, professor.getSalario());
            statement3.setInt(2, idPessoa);


            statement.executeUpdate();

            ConnectionMYSQL.closeConnection();

            System.out.println("Professor criado");

        } catch (SQLException e) {
            System.out.println("Erro ao salvar Professor " + e.getMessage());
        }
        return professor;
    }
    public List<Professor> findall() {
        List<Professor> objects = new ArrayList<Professor>();

        try {
            Connection conn = ConnectionMYSQL.openConnection();

            String sqlfindall = "SELECT * FROM professor join pessoa on pessoa_idPessoa = aluno_idPessoa";
            PreparedStatement statement = conn.prepareStatement(sqlfindall);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Professor professor = new Professor();
                professor.setNome(rs.getString("nome"));
                professor.setIdade(rs.getInt("idade"));
                professor.setSalario(rs.getDouble("Salario"));
                professor.setIdProfessor(rs.getInt("idProfessor"));
                objects.add(professor);
            }
            ConnectionMYSQL.closeConnection();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor " + e.getMessage());
        }
        return objects;
    }
    public Professor findById(int id) {
        Professor professor = new Professor();
        try {
            Connection conn = ConnectionMYSQL.openConnection();

            String sqlfindById = "SELECT * FROM professor join pessoa on professor.pessoa_idPessoa = pessoa.idPessoa " +
                    "where professor.pessoaidPessoa = ?";
            PreparedStatement statement = conn.prepareStatement(sqlfindById);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                professor.setNome(rs.getString("nome"));
                professor.setIdade(rs.getInt("idade"));
                professor.setSalario(rs.getDouble("Salario"));
                professor.setIdProfessor(rs.getInt("idProfessor"));
            }

            ConnectionMYSQL.closeConnection();

        } catch (SQLException e) {
            System.out.println("Erro ao buscar professor " + e.getMessage());
        }
        return professor;
    }
    public void deleteProfessor(int id){
        try {
            Connection conn = ConnectionMYSQL.openConnection();
            String sqldelete = "DELETE FROM professor WHERE idProfessor = ?";
            PreparedStatement statement = conn.prepareStatement(sqldelete);
            statement.setInt(1, id);
            int afetados = statement.executeUpdate();

            if (afetados > 0) {
                System.out.println("Professor removido com sucesso");
            }
            ConnectionMYSQL.closeConnection();


        } catch (SQLException e) {
            System.out.println("Erro ao remover professor " + e.getMessage());
        }
    }
}

package DAO;
import entidade.Turma;
import utilidade.ConnectionMYSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO {

    public Turma criarTurma() {
        Turma turma = new Turma();
        try {
            Connection conn = ConnectionMYSQL.openConnection();
            String sql =  "INSERT INTO turma (nome, idProfessor) VALUES (?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, turma.getNome());
            statement.setInt(2, turma.getId_Professor());
            statement.execute();

            ConnectionMYSQL.closeConnection();

            System.out.println("Turma criada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar Turma");
        }
        return turma;
    }
    public List<Turma> findall() {
        List<Turma> objects = new ArrayList<Turma>();

        try {
            Connection conn = ConnectionMYSQL.openConnection();

            String sqlfindall = "SELECT * FROM turma";
            PreparedStatement statement = conn.prepareStatement(sqlfindall);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Turma turma = new Turma();
                turma.setIdTurma(rs.getInt("IdTurma"));
                Turma.setNome(rs.getString("Nome"));
                Turma.setTurma_idTurma(rs.getInt("Turma_idtTurma"));
                turma.setId_Professor(rs.getInt("idProfessor"));
                objects.add(turma);
            }
            ConnectionMYSQL.closeConnection();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar alunos " + e.getMessage());
        }
        return objects;
    }
    public void buscarTurma(int id) {
        try {
            Connection conn = ConnectionMYSQL.openConnection();
            String sql = "SELECT turma.nome, pessoa.nome FROM turma join professor on turma.Professor_idProfessor = Professor_idProfessor" +
                    "join pessoa on pessoa_idPessoa = professor.Pessoa_idPessoa" +
                    "where turma_idturma = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                System.out.println("Turma " + (rs.getString("turma.Nome"));
                System.out.println("Professor:" +(rs.getString("pessoa.nome"));
            }

            String alunos = "SELECT pessoa.nome FROM aluno join pessoa on aluno.Pessoa_idPessoa = pessoa.idPessoa" +
                    "where aluno.turma_idTurma = ? ";

            PreparedStatement statement2 = conn.prepareStatement(alunos);
            statement2.setInt(1, id);
            ResultSet rs2 = statement2.executeQuery();

            while (rs2.next()) {
                System.out.println(rs2.getString("pessoa.nome"));
            }
                ConnectionMYSQL.closeConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletarTurma(int id) {
            try {
                Connection conn = ConnectionMYSQL.openConnection();
                String sqldelete = "DELETE FROM turma WHERE id_turma = ?";
                PreparedStatement statement = conn.prepareStatement(sqldelete);
                statement.setInt(1, id);
                int afetados = statement.executeUpdate();

                if (afetados > 0) {
                    System.out.println("Turma removido com sucesso");
                }
                ConnectionMYSQL.closeConnection();


            } catch (SQLException e) {
                System.out.println("Erro ao remover turma " + e.getMessage());
            }
        }
    }


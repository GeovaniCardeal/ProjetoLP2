package DAO;

import DATA.Chamado;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChamadoDAO {

    public void salvar(Chamado chamado) {
        String sql = "INSERT INTO chamados (nome, setor, descricao) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, chamado.getNome());
            stmt.setString(2, chamado.getSetor());
            stmt.setString(3, chamado.getDescricao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                chamado.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Chamado> listarTodos() {
        List<Chamado> lista = new ArrayList<>();
        String sql = "SELECT * FROM chamados ORDER BY data_abertura DESC";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Chamado chamado = new Chamado(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("setor"),
                        rs.getString("descricao"),
                        rs.getTimestamp("data_abertura").toString(),
                        rs.getString("status"),
                        rs.getString("resposta")
                );
                lista.add(chamado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Chamado buscarPorId(int id) {
        String sql = "SELECT * FROM chamados WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Chamado(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("setor"),
                        rs.getString("descricao"),
                        rs.getTimestamp("data_abertura").toString(),
                        rs.getString("status"),
                        rs.getString("resposta")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizarChamado(Chamado chamado) {
        String sql = "UPDATE chamados SET status = ?, resposta = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, chamado.getStatus());
            stmt.setString(2, chamado.getResposta());
            stmt.setInt(3, chamado.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
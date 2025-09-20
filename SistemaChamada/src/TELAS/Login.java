package TELAS;

import DAO.Conexao;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoLogin;

    public Login() {
        setTitle("Login");
        setSize(320, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        campoUsuario = new JTextField(15);
        campoSenha = new JPasswordField(15);
        botaoLogin = new JButton("Entrar");

        JPanel painel = new JPanel(new GridLayout(5, 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.add(new JLabel("Usuário:"));
        painel.add(campoUsuario);
        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);
        painel.add(botaoLogin);

        add(painel);

        botaoLogin.addActionListener(e -> fazerLogin());
    }

    private void fazerLogin() {
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (validarLogin(usuario, senha)) {
            new CadastroChamado().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos.");
        }
    }

    private boolean validarLogin(String usuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
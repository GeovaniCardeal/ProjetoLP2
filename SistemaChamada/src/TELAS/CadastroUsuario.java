package TELAS;

import BO.UsuarioBO;
import DATA.Usuario;

import javax.swing.*;
import java.awt.*;

public class CadastroUsuario extends JFrame {
    private JTextField campoNome, campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoSalvar;
    private UsuarioBO usuarioBO = new UsuarioBO();
    private Usuario usuario;

    public CadastroUsuario(Usuario usuario) {
        this.usuario = usuario;

        setTitle(usuario == null ? "Novo Usuário" : "Editar Usuário");
        setSize(350, 260);
        setLocationRelativeTo(null);

        campoNome = new JTextField(20);
        campoUsuario = new JTextField(20);
        campoSenha = new JPasswordField(20);
        botaoSalvar = new JButton("Salvar");

        if (usuario != null) {
            campoNome.setText(usuario.getNome());
            campoUsuario.setText(usuario.getUsuario());
            campoSenha.setText(usuario.getSenha());
        }

        JPanel painel = new JPanel(new GridLayout(7, 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("Usuário:"));
        painel.add(campoUsuario);
        painel.add(new JLabel("Senha:"));
        painel.add(campoSenha);
        painel.add(botaoSalvar);

        add(painel);

        botaoSalvar.addActionListener(e -> salvarUsuario());
    }

    private void salvarUsuario() {
        try {
            if (usuario == null) {
                usuarioBO.salvarUsuario(new Usuario(
                        campoNome.getText(),
                        campoUsuario.getText(),
                        new String(campoSenha.getPassword())
                ));
                JOptionPane.showMessageDialog(this, "Usuário cadastrado!");
            } else {
                usuario.setNome(campoNome.getText());
                usuario.setUsuario(campoUsuario.getText());
                usuario.setSenha(new String(campoSenha.getPassword()));
                usuarioBO.atualizarUsuario(usuario);
                JOptionPane.showMessageDialog(this, "Usuário atualizado!");
            }
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

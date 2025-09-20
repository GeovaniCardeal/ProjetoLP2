package TELAS;

import BO.UsuarioBO;
import DATA.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListaUsuarios extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private UsuarioBO usuarioBO = new UsuarioBO();

    public ListaUsuarios() {
        setTitle("Usuários");
        setSize(600, 350);
        setLocationRelativeTo(null);

        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Usuário"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabela = new JTable(modelo);
        atualizarTabela();

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int linha = tabela.getSelectedRow();
                    if (linha != -1) {
                        int id = (int) modelo.getValueAt(linha, 0);
                        Usuario u = usuarioBO.buscarPorId(id);
                        if (u != null) new CadastroUsuario(u).setVisible(true);
                    }
                }
            }
        });

        JButton botaoNovo = new JButton("Novo Usuário");
        botaoNovo.addActionListener(e -> new CadastroUsuario(null).setVisible(true));

        JPanel painel = new JPanel(new BorderLayout());
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);
        painel.add(botaoNovo, BorderLayout.SOUTH);

        add(painel);
    }

    private void atualizarTabela() {
        modelo.setRowCount(0);
        for (Usuario u : usuarioBO.listarUsuarios()) {
            modelo.addRow(new Object[]{u.getId(), u.getNome(), u.getUsuario()});
        }
    }
}
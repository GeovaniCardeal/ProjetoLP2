package TELAS;

import BO.ChamadoBO;
import DATA.Chamado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class CadastroChamado extends JFrame {
    private JTextField campoNome, campoSetor;
    private JTextArea campoDescricao;
    private JButton botaoSalvar, botaoListar;
    private JTable tabelaChamados;
    private DefaultTableModel modeloTabela;

    private ChamadoBO chamadoBO = new ChamadoBO();

    public CadastroChamado() {
        setTitle("Sistema de Chamados");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Menu superior
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opções");
        JMenuItem itemUsuarios = new JMenuItem("Gerenciar Usuários");
        itemUsuarios.addActionListener(e -> new ListaUsuarios().setVisible(true));
        menu.add(itemUsuarios);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Campos do formulário
        campoNome = new JTextField(20);
        campoSetor = new JTextField(20);
        campoDescricao = new JTextArea(3, 20);
        JScrollPane scrollDescricao = new JScrollPane(campoDescricao);

        botaoSalvar = new JButton("Salvar Chamado");
        botaoListar = new JButton("Listar Chamados");

        // Tabela de chamados
        modeloTabela = new DefaultTableModel(
                new String[]{"ID", "Nome", "Setor", "Descrição", "Status", "Data"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tabelaChamados = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaChamados);

        // Painel de formulário
        JPanel painelForm = new JPanel(new GridLayout(7, 1, 5, 5));
        painelForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelForm.add(new JLabel("Nome:"));
        painelForm.add(campoNome);
        painelForm.add(new JLabel("Setor:"));
        painelForm.add(campoSetor);
        painelForm.add(new JLabel("Descrição:"));
        painelForm.add(scrollDescricao);
        painelForm.add(botaoSalvar);

        // Painel principal
        JPanel painelMain = new JPanel(new BorderLayout(10, 10));
        painelMain.add(painelForm, BorderLayout.NORTH);
        painelMain.add(scrollTabela, BorderLayout.CENTER);

        // Rodapé com botão listar
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(botaoListar);
        painelMain.add(bottom, BorderLayout.SOUTH);

        add(painelMain);

        // Ações dos botões
        botaoSalvar.addActionListener(this::salvarChamado);
        botaoListar.addActionListener(e -> atualizarTabela());

        // Duplo clique na tabela abre atendimento
        tabelaChamados.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int linha = tabelaChamados.getSelectedRow();
                    if (linha != -1) {
                        int modelRow = tabelaChamados.convertRowIndexToModel(linha);
                        int id = (int) modeloTabela.getValueAt(modelRow, 0);
                        Chamado chamado = chamadoBO.buscarPorId(id);
                        if (chamado != null) {
                            new AtendimentoChamado(chamado).setVisible(true);
                            atualizarTabela();
                        }
                    }
                }
            }
        });

        // Carrega tabela logo ao abrir
        atualizarTabela();
    }

    // Salva novo chamado
    private void salvarChamado(ActionEvent e) {
        String nome = campoNome.getText().trim();
        String setor = campoSetor.getText().trim();
        String descricao = campoDescricao.getText().trim();

        Chamado chamado = new Chamado(nome, setor, descricao);
        try {
            chamadoBO.salvarChamado(chamado);
            JOptionPane.showMessageDialog(this, "Chamado salvo com sucesso!");
            campoNome.setText("");
            campoSetor.setText("");
            campoDescricao.setText("");
            atualizarTabela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Chamado c : chamadoBO.listarChamados()) {
            modeloTabela.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getSetor(),
                    c.getDescricao(),
                    c.getStatus(),
                    c.getDataAbertura()
            });
        }
    }
}
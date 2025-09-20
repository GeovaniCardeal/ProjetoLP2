package TELAS;

import BO.ChamadoBO;
import DATA.Chamado;

import javax.swing.*;
import java.awt.*;

public class AtendimentoChamado extends JFrame {

    private JTextField campoNome, campoSetor, campoStatus;
    private JTextArea campoDescricao, campoResposta;
    private JButton botaoSalvar;
    private Chamado chamado;
    private ChamadoBO chamadoBO = new ChamadoBO();

    public AtendimentoChamado(Chamado chamado) {
        this.chamado = chamado;

        setTitle("Atendimento do Chamado - ID " + chamado.getId());
        setSize(450, 550);
        setLocationRelativeTo(null);

        campoNome = new JTextField(chamado.getNome());
        campoSetor = new JTextField(chamado.getSetor());
        campoDescricao = new JTextArea(chamado.getDescricao());
        campoStatus = new JTextField(chamado.getStatus() != null ? chamado.getStatus() : "Aberto");
        campoResposta = new JTextArea(chamado.getResposta() != null ? chamado.getResposta() : "");

        campoNome.setEditable(false);
        campoSetor.setEditable(false);
        campoDescricao.setEditable(false);

        botaoSalvar = new JButton("Atualizar Chamado");

        JPanel painel = new JPanel(new GridLayout(11, 1, 5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);
        painel.add(new JLabel("Setor:"));
        painel.add(campoSetor);
        painel.add(new JLabel("Descrição:"));
        painel.add(new JScrollPane(campoDescricao));
        painel.add(new JLabel("Status:"));
        painel.add(campoStatus);
        painel.add(new JLabel("Resposta:"));
        painel.add(new JScrollPane(campoResposta));
        painel.add(botaoSalvar);

        add(painel);

        botaoSalvar.addActionListener(e -> salvar());
    }

    private void salvar() {
        chamado.setStatus(campoStatus.getText().trim());
        chamado.setResposta(campoResposta.getText().trim());
        try {
            chamadoBO.atualizarChamado(chamado);
            JOptionPane.showMessageDialog(this, "Chamado atualizado com sucesso!");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

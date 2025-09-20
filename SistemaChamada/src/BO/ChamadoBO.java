package BO;
import DAO.ChamadoDAO;
import DATA.Chamado;
import java.util.List;

public class ChamadoBO {
    private ChamadoDAO chamadoDAO = new ChamadoDAO();

    public void salvarChamado(Chamado chamado) throws Exception {
        if (chamado.getNome() == null || chamado.getNome().isBlank())
            throw new Exception("Nome do solicitante é obrigatório!");
        if (chamado.getSetor() == null || chamado.getSetor().isBlank())
            throw new Exception("Setor de origem é obrigatório!");
        if (chamado.getDescricao() == null || chamado.getDescricao().isBlank())
            throw new Exception("Descrição do problema é obrigatória!");
        chamadoDAO.salvar(chamado);
    }

    public List<Chamado> listarChamados() {
        return chamadoDAO.listarTodos();
    }

    public Chamado buscarPorId(int id) {
        return chamadoDAO.buscarPorId(id);
    }

    public void atualizarChamado(Chamado chamado) throws Exception {
        if (chamado.getStatus() == null || chamado.getStatus().isBlank())
            throw new Exception("Status não pode ser vazio!");
        chamadoDAO.atualizarChamado(chamado);
    }
}
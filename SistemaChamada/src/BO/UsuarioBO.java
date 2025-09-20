package BO;
import DAO.UsuarioDAO;
import DATA.Usuario;
import java.util.List;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void salvarUsuario(Usuario usuario) throws Exception {
        if (usuario.getNome() == null || usuario.getNome().isBlank())
            throw new Exception("Nome é obrigatório!");
        if (usuario.getUsuario() == null || usuario.getUsuario().isBlank())
            throw new Exception("Usuário é obrigatório!");
        if (usuario.getSenha() == null || usuario.getSenha().isBlank())
            throw new Exception("Senha é obrigatória!");
        usuarioDAO.salvar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarTodos();
    }

    public Usuario buscarPorId(int id) {
        return usuarioDAO.buscarPorId(id);
    }

    public void atualizarUsuario(Usuario usuario) throws Exception {
        if (usuario.getNome() == null || usuario.getNome().isBlank())
            throw new Exception("Nome é obrigatório!");
        if (usuario.getUsuario() == null || usuario.getUsuario().isBlank())
            throw new Exception("Usuário é obrigatório!");
        usuarioDAO.atualizar(usuario);
    }
}
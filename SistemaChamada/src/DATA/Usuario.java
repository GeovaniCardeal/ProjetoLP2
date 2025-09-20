package DATA;


public class Usuario {
    private int id;
    private String nome;
    private String usuario;
    private String senha;

    public Usuario(int id, String nome, String usuario, String senha) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getUsuario() { return usuario; }
    public String getSenha() { return senha; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", usuario='" + usuario + '\'' +
                '}';
    }
}
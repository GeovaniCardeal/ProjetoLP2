package DATA;


public class Chamado {
    private int id;
    private String nome;
    private String setor;
    private String descricao;
    private String dataAbertura;
    private String status;
    private String resposta;

    // Construtores
    public Chamado(int id, String nome, String setor, String descricao, String dataAbertura, String status, String resposta) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
        this.descricao = descricao;
        this.dataAbertura = dataAbertura;
        this.status = status;
        this.resposta = resposta;
    }

    public Chamado(String nome, String setor, String descricao) {
        this.nome = nome;
        this.setor = setor;
        this.descricao = descricao;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getSetor() { return setor; }
    public String getDescricao() { return descricao; }
    public String getDataAbertura() { return dataAbertura; }
    public String getStatus() { return status; }
    public String getResposta() { return resposta; }

    public void setId(int id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }
    public void setResposta(String resposta) { this.resposta = resposta; }

    @Override
    public String toString() {
        return "Chamado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", setor='" + setor + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataAbertura='" + dataAbertura + '\'' +
                ", status='" + status + '\'' +
                ", resposta='" + resposta + '\'' +
                '}';
    }
}
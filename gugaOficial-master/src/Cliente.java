public class Cliente {
    private int id;
    private String nome;
    private String telefone;

    // Polimorfismo em Cliente do tipo sobrecarga de construtores
    // Construtor para buscar cliente no banco
    public Cliente(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    // Construtor para criar um novo cliente
    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
}
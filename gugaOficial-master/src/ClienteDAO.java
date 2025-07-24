import java.sql.*;

public class ClienteDAO {

    public Cliente criarCliente(Cliente cliente) {

        // Inserindo novo cliente no banco
        String sqlInsert = "INSERT INTO gugapd.clientes (nome, telefone) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                cliente.setId(generatedKeys.getInt(1)); // Pega o ID gerado pelo banco e atualiza o objeto
            }
            return cliente;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar novo cliente.", e);
        }
    }
}
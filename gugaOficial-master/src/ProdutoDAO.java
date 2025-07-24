
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    // Este método busca todos os produtos da tabela 'produtos'
    public List<Produto> listarProdutos() {
        List<Produto> cardapio = new ArrayList<>();
        String sql = "SELECT id, nome, preco FROM gugapd.produtos ORDER BY nome ASC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Loop para pegar os produtos da tabela produtos
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double preco = rs.getDouble("preco");
                cardapio.add(new Produto(id, nome, preco));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar produtos do banco de dados.", e);
        }
        return cardapio;
    }
}


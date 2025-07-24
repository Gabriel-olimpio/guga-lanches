import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    //  Verificar se a URL, usuário e senha estão corretos
    private static final String URL = "jdbc:postgresql://localhost:5432/guga";
    private static final String USER = "postgres";
    private static final String PASSWORD = "backend"; // <-- Trocar pela senha do banco

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexão com o banco de dados: " + e.getMessage(), e);
        }
    }
}
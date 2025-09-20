package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String ENDERECO = "jdbc:mysql://localhost:3306/sistema_chamados?allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo";
    private static final String USUARIO = "geovani";
    private static final String SENHA = "12345678";

public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(ENDERECO, USUARIO, SENHA);
    }
}

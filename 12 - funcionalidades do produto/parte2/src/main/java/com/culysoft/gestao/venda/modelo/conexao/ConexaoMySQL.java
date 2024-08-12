package com.culysoft.gestao.venda.modelo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {
    
    private static final String URL = "jdbc:mysql://localhost/gestaovenda?useTimezone=true&serverTimezone=Africa/Luanda";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    private ConexaoMySQL() {}
    
    public static Connection obterConexao() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
    
    public static void fecharConexao() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
    
}

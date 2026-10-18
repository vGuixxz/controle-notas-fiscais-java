package com.guilherme.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

    private static final String URL = "jdbc:mysql://localhost:3306/Sistema_nf";
    private static final String USUARIO = "root";
    private static final String SENHA = "@gGui01020304";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            return null;
        }
    }
}
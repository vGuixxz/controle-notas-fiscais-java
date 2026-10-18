package com.guilherme;

import com.guilherme.conexao.ConexaoMySQL;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.out.println("Testando a conexão com o banco...");

        // Usando a nossa ponte para tentar conectar
        Connection conexao = ConexaoMySQL.conectar();

        if (conexao != null) {
            System.out.println("SUCESSO! O Java conectou com o MySQL perfeitamente!");
        } else {
            System.out.println("FALHA! Deu ruim na conexão.");
        }
    }
}
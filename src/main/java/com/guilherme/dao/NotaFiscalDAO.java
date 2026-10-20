package com.guilherme.dao;

import com.guilherme.conexao.ConexaoMySQL;
import com.guilherme.model.NotaFiscal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class NotaFiscalDAO {

    public void inserir(NotaFiscal nota) {
        String sql = "INSERT INTO nota_fiscal (numero, fornecedor, cnpj_fornecedor, data_emissao, valor_total, status) VALUES (?, ?, ?, ?, ?, ?)";

        // O try-with-resources fecha as conexões sozinho para não vazar memória
        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nota.getNumero());
            stmt.setString(2, nota.getFornecedor());
            stmt.setString(3, nota.getCnpjFornecedor());
            stmt.setObject(4, nota.getDataEmissao());
            stmt.setDouble(5, nota.getValorTotal());
            stmt.setString(6, nota.getStatus());

            stmt.executeUpdate();
            System.out.println("Nota Fiscal cadastrada com sucesso no banco!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar nota fiscal: " + e.getMessage());
        }
    }
}
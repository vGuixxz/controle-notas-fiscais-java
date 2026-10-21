package com.guilherme.dao;

import com.guilherme.conexao.ConexaoMySQL;
import com.guilherme.model.NotaFiscal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NotaFiscalDAO {

    public int inserir(NotaFiscal nota) {
        String sql = "INSERT INTO nota_fiscal (numero, fornecedor, cnpj_fornecedor, data_emissao, valor_total, status) VALUES (?, ?, ?, ?, ?, ?)";
        int idGerado = -1;

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, nota.getNumero());
            stmt.setString(2, nota.getFornecedor());
            stmt.setString(3, nota.getCnpjFornecedor());
            stmt.setObject(4, nota.getDataEmissao());
            stmt.setDouble(5, nota.getValorTotal());
            stmt.setString(6, nota.getStatus());

            stmt.executeUpdate();

            // Pega o ID que o MySQL acabou de gerar no AUTO_INCREMENT
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idGerado = rs.getInt(1);
                    nota.setId(idGerado);
                }
            }

            System.out.println("Nota Fiscal cadastrada com sucesso! (ID gerado: " + idGerado + ")");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar nota fiscal: " + e.getMessage());
        }

        return idGerado; // Devolve o ID para o Main usar!
    }

    public void atualizarStatus(int idNota, String novoStatus) {
        String sql = "UPDATE nota_fiscal SET status = ? WHERE id = ?";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, idNota);

            stmt.executeUpdate();
            System.out.println("Status da Nota Fiscal (ID: " + idNota + ") atualizado para: " + novoStatus);

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status da nota: " + e.getMessage());
        }
    }
}
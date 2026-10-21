package com.guilherme.dao;

import com.guilherme.conexao.ConexaoMySQL;
import com.guilherme.model.ItemNota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ItemNotaDAO {

    public void inserirItem(ItemNota item) {
        String sql = "INSERT INTO item_nota (id_nota, produto, quantidade_esperada, quantidade_recebida) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoMySQL.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, item.getIdNota());
            stmt.setString(2, item.getProduto());
            stmt.setInt(3, item.getQuantidadeEsperada());
            stmt.setInt(4, item.getQuantidadeRecebida());

            stmt.executeUpdate();
            System.out.println("Item cadastrado com sucesso na nota fiscal!");

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar item da nota: " + e.getMessage());
        }
    }
}
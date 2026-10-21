package com.guilherme;

import com.guilherme.dao.ItemNotaDAO;
import com.guilherme.model.ItemNota;

public class Main {
    public static void main(String[] args) {
        System.out.println("Cadastrando item da nota fiscal...");

        // Criamos o item já amarrando na nota de ID 1 (Autopeças do Zé)
        // Parâmetros: (idNota, produto, quantidadeEsperada, quantidadeRecebida)
        ItemNota novoItem = new ItemNota(1, "Retrovisor Astra 2000", 2, 2);

        // Chamamos o operário (DAO) do item para fazer o INSERT no banco
        ItemNotaDAO itemDao = new ItemNotaDAO();
        itemDao.inserirItem(novoItem);
    }
}
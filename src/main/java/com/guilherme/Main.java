package com.guilherme;

import com.guilherme.dao.NotaFiscalDAO;
import com.guilherme.model.NotaFiscal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Criando objeto da Nota Fiscal...");

        // 1. Criamos a caixinha com os dados da nota usando o construtor completo
        NotaFiscal novaNota = new NotaFiscal(
                "NF-9988",
                "Autopeças do Zé",
                "12345678000199",
                LocalDate.now(),
                1500.50
        );

        // 2. Chamamos o nosso operário (DAO) para fazer o INSERT no banco
        NotaFiscalDAO dao = new NotaFiscalDAO();
        dao.inserir(novaNota);
    }
}
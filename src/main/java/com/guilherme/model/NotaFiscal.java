package com.guilherme.model;

import java.time.LocalDate;

public class NotaFiscal {
    private int id;
    private String numero;
    private String fornecedor;
    private String cnpjFornecedor;
    private LocalDate dataEmissao;
    private double valorTotal;
    private String status;

    // Construtor vazio
    public NotaFiscal() {
    }

    // Construtor com os campos principais
    public NotaFiscal(String numero, String fornecedor, String cnpjFornecedor, LocalDate dataEmissao, double valorTotal) {
        this.numero = numero;
        this.fornecedor = fornecedor;
        this.cnpjFornecedor = cnpjFornecedor;
        this.dataEmissao = dataEmissao;
        this.valorTotal = valorTotal;
        this.status = "PENDENTE"; // Valor padrão igual no banco
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public String getCnpjFornecedor() { return cnpjFornecedor; }
    public void setCnpjFornecedor(String cnpjFornecedor) { this.cnpjFornecedor = cnpjFornecedor; }

    public LocalDate getDataEmissao() { return dataEmissao; }
    public void setDataEmissao(LocalDate dataEmissao) { this.dataEmissao = dataEmissao; }

    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
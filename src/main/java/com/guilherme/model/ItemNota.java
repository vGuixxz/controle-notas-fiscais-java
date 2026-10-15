package com.guilherme.model;

public class ItemNota {

        private int id;
        private int idNota;
        private String produto;
        private int quantidadeEsperada;
        private int quantidadeRecebida;

        public ItemNota() {
        }

        public ItemNota(int idNota, String produto, int quantidadeEsperada, int quantidadeRecebida) {
            this.idNota = idNota;
            this.produto = produto;
            this.quantidadeEsperada = quantidadeEsperada;
            this.quantidadeRecebida = quantidadeRecebida;
        }

    public int getid() { return id; }
    public void setId(int id) { this.id = id;}

    public int getIdNota() { return idNota; }
    public void setIdNota(int idNota) {this.idNota = idNota;}

    public String getProduto() { return produto; }
    public void setProduto(String produto) {this.produto = produto;}

    public int getQuantidadeEsperada() { return quantidadeEsperada; }
    public void setQuantidadeEsperada(int quantidadeEsperada) {this.quantidadeEsperada = quantidadeEsperada;}

    public int getQuantidadeRecebida() { return quantidadeRecebida; }
    public void setQuantidadeRecebida(int quantidadeRecebida) {this.quantidadeRecebida = quantidadeRecebida;}
}

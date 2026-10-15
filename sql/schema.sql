CREATE DATABASE IF NOT EXISTS Sistema_nf;
USE Sistema_nf;

CREATE TABLE nota_fiscal (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             numero VARCHAR(20) NOT NULL,
                             fornecedor VARCHAR(100) NOT NULL,
                             cnpj_fornecedor VARCHAR(14) NOT NULL,
                             data_emissao DATE NOT NULL,
                             valor_total DECIMAL(10,2) NOT NULL,
                             status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE'
);

CREATE TABLE item_nota (
                           id INT PRIMARY KEY AUTO_INCREMENT,
                           id_nota INT NOT NULL,
                           produto VARCHAR(100) NOT NULL,
                           quantidade_esperada INT NOT NULL,
                           quantidade_recebida INT,
                           FOREIGN KEY (id_nota) REFERENCES nota_fiscal(id)
);
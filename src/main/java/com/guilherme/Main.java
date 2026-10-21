package com.guilherme;

import com.guilherme.dao.ItemNotaDAO;
import com.guilherme.dao.NotaFiscalDAO;
import com.guilherme.model.ItemNota;
import com.guilherme.model.NotaFiscal;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NotaFiscalDAO notaDao = new NotaFiscalDAO();
        ItemNotaDAO itemDao = new ItemNotaDAO();

        System.out.println("==========================================");
        System.out.println("  SISTEMA DE RECEBIMENTO - NOTAS FISCAIS  ");
        System.out.println("==========================================");

        boolean dadosCorretos = false;
        String numero = "", fornecedor = "", cnpj = "";
        double valorTotal = 0.0;

        // LOOP DE VALIDAÇÃO DOS DADOS DA NOTA (Permite voltar e corrigir se errar)
        while (!dadosCorretos) {
            System.out.print("\nDigite o número da Nota Fiscal (ex: NF-2026): ");
            numero = scanner.nextLine();

            System.out.print("Digite o nome do Fornecedor: ");
            fornecedor = scanner.nextLine();

            System.out.print("Digite o CNPJ do Fornecedor: ");
            cnpj = scanner.nextLine();

            System.out.print("Digite o Valor Total da Nota (ex: 1500.50): ");
            valorTotal = scanner.nextDouble();
            scanner.nextLine(); // Limpa o enter

            // Mostra o resumo para conferência
            System.out.println("\n--- CONFIRA OS DADOS DIGITADOS ---");
            System.out.println("Número: " + numero);
            System.out.println("Fornecedor: " + fornecedor);
            System.out.println("CNPJ: " + cnpj);
            System.out.println("Valor Total: R$ " + valorTotal);

            System.out.print("\nOs dados estão corretos? (S - Continuar / N - Refazer digitação): ");
            String confirmacao = scanner.nextLine();

            if (confirmacao.equalsIgnoreCase("S")) {
                dadosCorretos = true;
            } else {
                System.out.println("🔄 Certo! Vamos redigitar os dados da nota.");
            }
        }

        // Pergunta se deseja prosseguir ou ANULAR o cadastro da nota inteira
        System.out.print("\nDeseja salvar a nota e iniciar a conferência dos itens? (S - Salvar / ANULAR - Desistir da Nota): ");
        String decisaoNota = scanner.nextLine();

        if (decisaoNota.equalsIgnoreCase("ANULAR")) {
            System.out.println("\n❌ Nota Fiscal ANULADA pelo operador. Nenhum registro foi salvo.");
            scanner.close();
            return; // Encerra o programa na hora
        }

        // Criamos e salvamos a nota no banco, pegando o ID gerado
        NotaFiscal novaNota = new NotaFiscal(numero, fornecedor, cnpj, LocalDate.now(), valorTotal);
        int idNotaGerado = notaDao.inserir(novaNota);

        if (idNotaGerado != -1) {
            System.out.println("\n--- CADASTRANDO E CONFERINDO OS ITENS ---");

            boolean teveDivergencia = false;
            String continuar = "S";

            while (continuar.equalsIgnoreCase("S")) {
                System.out.print("\nNome do Produto: ");
                String produto = scanner.nextLine();

                System.out.print("Quantidade Esperada (conforme Nota): ");
                int qtdEsperada = scanner.nextInt();

                int qtdRecebida = 0;
                boolean itemValidado = false;

                // LOOP PARA TRATAR A QUANTIDADE QUANDO HÁ ERRO OU DIVERGÊNCIA
                while (!itemValidado) {
                    System.out.print("Quantidade Recebida (física na caixa): ");
                    qtdRecebida = scanner.nextInt();
                    scanner.nextLine(); // Limpa o enter

                    if (qtdRecebida != qtdEsperada) {
                        int diferenca = qtdRecebida - qtdEsperada;
                        System.out.println("\n⚠️ ATENÇÃO: Divergência detectada no produto '" + produto + "'!");
                        if (diferenca < 0) {
                            System.out.println("   FALTARAM " + Math.abs(diferenca) + " unidade(s)!");
                        } else {
                            System.out.println("   VIERAM " + diferenca + " unidade(s) A MAIS!");
                        }

                        System.out.println("\nO que você deseja fazer?");
                        System.out.println("1 - Tentar redigitar a contagem (Errei o número que digitei)");
                        System.out.println("2 - Manter divergência e continuar");
                        System.out.print("Escolha uma opção (1 ou 2): ");
                        int opcaoErro = scanner.nextInt();
                        scanner.nextLine(); // Limpa o enter

                        if (opcaoErro == 1) {
                            System.out.println("🔄 Ok, redigite a quantidade recebida:");
                            // Volta no loop para redigitar
                        } else {
                            teveDivergencia = true;
                            itemValidado = true; // Aceita a divergência
                        }
                    } else {
                        System.out.println("✅ Produto '" + produto + "' conferido 100% OK!");
                        itemValidado = true;
                    }
                }

                // Salva o item no banco
                ItemNota novoItem = new ItemNota(idNotaGerado, produto, qtdEsperada, qtdRecebida);
                itemDao.inserirItem(novoItem);

                System.out.print("\nDeseja cadastrar mais um item nesta nota? (S/N): ");
                continuar = scanner.nextLine();
            }

            // ATUALIZAÇÃO DO STATUS FINAL DA NOTA
            System.out.println("\n==========================================");
            if (teveDivergencia) {
                notaDao.atualizarStatus(idNotaGerado, "DIVERGENTE");
                System.out.println("❌ CONFERÊNCIA FINALIZADA COM STATUS: DIVERGENTE");
            } else {
                notaDao.atualizarStatus(idNotaGerado, "CONCLUIDA");
                System.out.println("🎉 CONFERÊNCIA FINALIZADA COM STATUS: CONCLUIDA");
            }
            System.out.println("==========================================");

        } else {
            System.out.println("Falha técnica ao cadastrar a Nota Fiscal.");
        }

        scanner.close();
    }
}
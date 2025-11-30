package controller;

import model.Aluno;
import model.Mensalidade;
import service.AlunoService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class FinanceiroController {

    private AlunoService alunoService = new AlunoService();

    public void gerarMensalidade(Scanner sc) {
        System.out.println("\n=== GERAR MENSALIDADE ===");
        System.out.print("Digite o nome do aluno: ");
        String nome = sc.nextLine().trim();
        Aluno aluno = alunoService.buscar(nome);

        if (aluno != null) {
            System.out.print("Descrição (ex: Mensalidade Jan/2025): ");
            String desc = sc.nextLine();

            System.out.print("Valor (ex: 500.00): ");
            double valor = Double.parseDouble(sc.nextLine());

            System.out.print("Data de Vencimento (formato AAAA-MM-DD, ex: 2025-12-20): ");
            String dataStr = sc.nextLine();

            try {
                LocalDate vencimento = LocalDate.parse(dataStr);
                Mensalidade novaMensalidade = new Mensalidade(desc, valor, vencimento);
                aluno.adicionarMensalidade(novaMensalidade);
                System.out.println("Mensalidade gerada com sucesso!");
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato Ano-Mês-Dia (2025-01-01).");
            }
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    public void realizarPagamento(Scanner sc) {
        System.out.println("\n=== PAGAMENTO DE MENSALIDADE ===");
        System.out.print("Digite o nome do aluno: ");
        String nome = sc.nextLine();
        Aluno aluno = alunoService.buscar(nome);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }


        System.out.println("--- Mensalidades Pendentes ---");
        boolean temPendente = false;
        int index = 0;

        for (Mensalidade m : aluno.getMensalidades()) {
            if (!m.isPaga()) {
                System.out.println("[" + index + "] " + m.toString());
                temPendente = true;
            }
            index++;
        }

        if (!temPendente) {
            System.out.println("Este aluno não tem débitos pendentes.");
            return;
        }

        System.out.print("Digite o número (índice) da mensalidade para pagar: ");
        int opcao = Integer.parseInt(sc.nextLine());

        if (opcao >= 0 && opcao < aluno.getMensalidades().size()) {
            Mensalidade mens = aluno.getMensalidades().get(opcao);

            if (mens.isPaga()) {
                System.out.println("Essa mensalidade já foi paga.");
                return;
            }


            LocalDate dataPagamento = LocalDate.now();



            double valorFinal = mens.calcularValorComJuros(dataPagamento);

            System.out.println("Data do Pagamento: " + dataPagamento);
            if (valorFinal > mens.getValorOriginal()) {
                System.out.println("ATENÇÃO: Pagamento em atraso!");
                System.out.println("Valor Original: R$ " + mens.getValorOriginal());
                System.out.println("Valor com Juros: R$ " + valorFinal);
            } else {
                System.out.println("Pagamento em dia. Valor: R$ " + valorFinal);
            }

            System.out.print("Confirmar pagamento? (S/N): ");
            String confirm = sc.nextLine();

            if (confirm.equalsIgnoreCase("S")) {
                mens.registrarPagamento(valorFinal, dataPagamento);
                System.out.println("Pagamento registrado com sucesso!");
            } else {
                System.out.println("Pagamento cancelado.");
            }

        } else {
            System.out.println("Opção inválida.");
        }
    }

    public void consultarHistorico(Scanner sc) {
        System.out.println("\n=== HISTÓRICO FINANCEIRO ===");
        System.out.print("Digite o nome do aluno: ");
        String nome = sc.nextLine();
        Aluno aluno = alunoService.buscar(nome);

        if (aluno != null) {
            if (aluno.getMensalidades().isEmpty()) {
                System.out.println("Nenhum registro financeiro para este aluno.");
            } else {
                for (Mensalidade m : aluno.getMensalidades()) {
                    System.out.println(m);
                }
            }
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }
}
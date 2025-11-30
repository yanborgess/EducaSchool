import controller.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        AlunoController alunoController = new AlunoController();
        ProfessorController professorController = new ProfessorController();
        DisciplinaController disciplinaController = new DisciplinaController();
        FinanceiroController financeiroController = new FinanceiroController();
        BoletimController boletimController = new BoletimController();

        boolean executando = true;

        while (executando) {
            System.out.println("\n======= 🏫 SISTEMA EDUCASCHOOL =======");
            System.out.println("1. Gestão de Alunos");
            System.out.println("2. Gestão de Professores");
            System.out.println("3. Gestão de Disciplinas");
            System.out.println("4. Financeiro");
            System.out.println("5. Acadêmico (Boletim/Notas)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = lerInteiro(sc);

            switch (opcao) {
                case 1:
                    menuAlunos(sc, alunoController);
                    break;
                case 2:
                    menuProfessores(sc, professorController);
                    break;
                case 3:
                    menuDisciplinas(sc, disciplinaController);
                    break;
                case 4:
                    menuFinanceiro(sc, financeiroController);
                    break;
                case 5:
                    menuBoletim(sc, boletimController, alunoController, disciplinaController);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }



    private static void menuAlunos(Scanner sc, AlunoController ac) {
        System.out.println("\n--- 🎓 GESTÃO DE ALUNOS ---");
        System.out.println("1. Cadastrar Novo Aluno");
        System.out.println("2. Listar Alunos");
        System.out.println("3. Matricular Aluno em Disciplina Extra");
        System.out.println("4. Deletar Aluno");
        System.out.println("0. Voltar");
        System.out.print("Opção: ");

        int op = lerInteiro(sc);
        switch (op) {
            case 1:
                ac.cadastrarAluno(sc);
                break;
            case 2:
                ac.listarAlunos();
                break;
            case 3:
                ac.matricularAluno(sc);
                break;
            case 4:
                System.out.print("Digite o nome do aluno para deletar: ");
                String nome = sc.nextLine();
                ac.deletarAluno(nome);
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuProfessores(Scanner sc, ProfessorController pc) {
        System.out.println("\n--- 👨‍🏫 GESTÃO DE PROFESSORES ---");
        System.out.println("1. Cadastrar Professor");
        System.out.println("2. Listar Professores");
        System.out.println("3. Deletar Professor");
        System.out.println("0. Voltar");
        System.out.print("Opção: ");

        int op = lerInteiro(sc);
        switch (op) {
            case 1:
                pc.cadastrarProfessor(sc);
                break;
            case 2:
                pc.listarProfessores();
                break;
            case 3:
                System.out.print("Digite o nome do professor para deletar: ");
                String nome = sc.nextLine();
                pc.deletarProfessor(nome);
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuDisciplinas(Scanner sc, DisciplinaController dc) {
        System.out.println("\n--- 📚 GESTÃO DE DISCIPLINAS ---");
        System.out.println("1. Cadastrar Disciplina");
        System.out.println("2. Listar Disciplinas");
        System.out.println("0. Voltar");
        System.out.print("Opção: ");

        int op = lerInteiro(sc);
        switch (op) {
            case 1:
                dc.cadastrarDisciplina(sc);
                break;
            case 2:
                dc.listarDisciplinas();
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuFinanceiro(Scanner sc, FinanceiroController fc) {
        System.out.println("\n--- 💰 FINANCEIRO ---");
        System.out.println("1. Gerar Mensalidade para Aluno");
        System.out.println("2. Realizar Pagamento");
        System.out.println("3. Consultar Histórico Financeiro");
        System.out.println("0. Voltar");
        System.out.print("Opção: ");

        int op = lerInteiro(sc);
        switch (op) {
            case 1:
                fc.gerarMensalidade(sc);
                break;
            case 2:
                fc.realizarPagamento(sc);
                break;
            case 3:
                fc.consultarHistorico(sc);
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static void menuBoletim(Scanner sc, BoletimController bc, AlunoController ac, DisciplinaController dc) {
        System.out.println("\n--- 📝 ACADÊMICO / BOLETIM ---");
        System.out.println("1. Lançar Notas e Faltas");
        System.out.println("2. Consultar Boletim do Aluno");
        System.out.println("0. Voltar");
        System.out.print("Opção: ");

        int op = lerInteiro(sc);
        switch (op) {
            case 1:
                bc.registrar(sc, ac, dc);
                break;
            case 2:
                bc.consultarBoletim(sc, ac);
                break;
            case 0:
                return;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private static int lerInteiro(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
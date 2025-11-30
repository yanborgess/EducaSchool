package controller;

import model.Aluno;
import model.Disciplina;
import service.AlunoService;
import service.DisciplinaService;

import java.util.List;
import java.util.Scanner;

public class AlunoController {
    private AlunoService alunoService = new AlunoService();
    // Precisamos do serviço de disciplina para mostrar as opções disponíveis
    private DisciplinaService disciplinaService = new DisciplinaService();

    public void cadastrarAluno(Scanner sc) {
        Aluno a = new Aluno();
        System.out.println("\n=== CADASTRO DE ALUNO ===");


        String nomeInput = "";
        while (nomeInput.trim().isEmpty()) {
            System.out.print("Digite o nome do aluno: ");
            nomeInput = sc.nextLine();
            if (nomeInput.trim().isEmpty()) {
                System.out.println("ERRO: O nome é obrigatório.");
            }
        }
        a.setNome(nomeInput);

        // --- VALIDAÇÃO: CURSO NÃO PODE SER VAZIO ---
        String cursoInput = "";
        while (cursoInput.trim().isEmpty()) {
            System.out.print("Digite o curso do aluno (Tecnólogo ou bacharelado): ");
            cursoInput = sc.nextLine();
            if (cursoInput.trim().isEmpty()) {
                System.out.println("ERRO: O curso é obrigatório.");
            }
        }
        a.setCurso(cursoInput);

        // --- VINCULAR DISCIPLINA ---
        System.out.println("\n--- Escolha uma Disciplina para vincular ---");
        List<Disciplina> disponiveis = disciplinaService.listarDisciplinas();

        if (disponiveis.isEmpty()) {
            System.out.println("AVISO: Nenhuma disciplina cadastrada ainda. O aluno ficará sem matérias.");
        } else {
            // Mostra a lista para o usuário escolher
            for (Disciplina d : disponiveis) {
                System.out.println("ID: " + d.getIdDisciplina() + " | Nome: " + d.getNomeDisciplina());
            }

            System.out.print("Digite o ID da disciplina (ou 0 para pular): ");
            int idEscolhido = -1;

            // Tratamento simples para garantir que seja número
            try {
                idEscolhido = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Nenhuma disciplina vinculada.");
            }

            if (idEscolhido > 0) {
                Disciplina disciplinaEncontrada = disciplinaService.buscarDisciplinaPorId(idEscolhido);
                if (disciplinaEncontrada != null) {
                    a.adicionarDisciplina(disciplinaEncontrada);
                    System.out.println("Sucesso! Disciplina '" + disciplinaEncontrada.getNomeDisciplina() + "' vinculada.");
                } else {
                    System.out.println("ID não encontrado.");
                }
            }
        }

        alunoService.cadastrarAluno(a);
        System.out.println("Aluno cadastrado com sucesso!");
    }

    public void listarAlunos() {
        List<Aluno> alunos = alunoService.listarAluno();
        System.out.println("\n--- Lista de Alunos ---");
        for (Aluno a : alunos) {
            System.out.println(a); // Usa o toString do Aluno
            // Se tiver disciplinas, mostra detalhado
            if (!a.getDisciplinasMatriculadas().isEmpty()) {
                System.out.print("   -> Cursando: ");
                for (Disciplina d : a.getDisciplinasMatriculadas()) {
                    System.out.print("[" + d.getNomeDisciplina() + "] ");
                }
                System.out.println(); // Pula linha
            }
        }
    }


    public void deletarAluno(String nome) {
        alunoService.deletarAluno(nome);
    }

    public Aluno buscarAluno(String nome) {
        return alunoService.buscarAluno(nome);
    }


    public void matricularAluno(Scanner sc) {
        System.out.println("\n=== MATRICULAR ALUNO EM NOVA DISCIPLINA ===");

        // 1. Buscar o Aluno
        System.out.print("Digite o nome do aluno: ");
        String nomeAluno = sc.nextLine();

        Aluno alunoEncontrado = alunoService.buscarAluno(nomeAluno);

        if (alunoEncontrado == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        System.out.println("Aluno selecionado: " + alunoEncontrado.getNome());

        // 2. Listar Disciplinas Disponíveis
        System.out.println("--- Disciplinas Disponíveis ---");
        List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();

        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas cadastradas.");
            return;
        }

        for (Disciplina d : disciplinas) {
            System.out.println("ID: " + d.getIdDisciplina() + " - " + d.getNomeDisciplina());
        }

        // 3. Escolher e Vincular
        System.out.print("Digite o ID da disciplina para adicionar: ");
        int idDisciplina = -1;
        try {
            idDisciplina = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
            return;
        }

        Disciplina disciplinaParaAdicionar = disciplinaService.buscarDisciplinaPorId(idDisciplina);

        if (disciplinaParaAdicionar != null) {
            alunoEncontrado.adicionarDisciplina(disciplinaParaAdicionar);
            System.out.println("Sucesso! Disciplina '" + disciplinaParaAdicionar.getNomeDisciplina() +
                    "' adicionada ao aluno " + alunoEncontrado.getNome());
        } else {
            System.out.println("Disciplina com ID " + idDisciplina + " não encontrada.");
        }
    }
    public Aluno getAlunoById(int id) {
        return alunoService.getAlunoById(id);
    }
}

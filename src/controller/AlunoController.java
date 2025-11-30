package controller;

import model.Aluno;
import model.Disciplina;
import enums.TipoCurso;
import service.AlunoService;
import service.DisciplinaService;

import java.util.List;
import java.util.Scanner;

public class AlunoController {

    private AlunoService alunoService = new AlunoService();
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

        System.out.println("\n--- Selecione o Curso ---");
        for (TipoCurso tipo : TipoCurso.values()) {
            System.out.println(tipo.ordinal() + " - " + tipo.getDescricao());
        }

        int opcaoCurso = -1;
        while (opcaoCurso < 0 || opcaoCurso >= TipoCurso.values().length) {
            System.out.print("Digite o número correspondente ao curso: ");
            try {
                opcaoCurso = Integer.parseInt(sc.nextLine());
                if (opcaoCurso < 0 || opcaoCurso >= TipoCurso.values().length) {
                    System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
        a.setCurso(TipoCurso.values()[opcaoCurso]);

        System.out.println("\n--- Escolha uma Disciplina para vincular ---");
        List<Disciplina> disponiveis = disciplinaService.listarDisciplinas();

        if (disponiveis.isEmpty()) {
            System.out.println("AVISO: Nenhuma disciplina cadastrada ainda. O aluno ficará sem matérias.");
        } else {
            for (Disciplina d : disponiveis) {
                System.out.println("ID: " + d.getIdDisciplina() + " | Nome: " + d.getNomeDisciplina());
            }

            System.out.print("Digite o ID da disciplina (ou 0 para pular): ");
            int idEscolhido = -1;
            try {
                idEscolhido = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                idEscolhido = 0;
            }

            if (idEscolhido > 0) {
                Disciplina disciplinaEncontrada = disciplinaService.buscarDisciplinaPorId(idEscolhido);
                if (disciplinaEncontrada != null) {
                    a.adicionarDisciplina(disciplinaEncontrada);
                    System.out.println("Disciplina '" + disciplinaEncontrada.getNomeDisciplina() + "' vinculada.");
                } else {
                    System.out.println("ID não encontrado. Nenhuma disciplina vinculada.");
                }
            }
        }

        alunoService.cadastrar(a);
        System.out.println("✅ Aluno cadastrado com sucesso! Matrícula: " + a.getMatricula());
    }

    public void listarAlunos() {
        List<Aluno> alunos = alunoService.listar();

        System.out.println("\n--- Lista de Alunos ---");
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (Aluno a : alunos) {
            System.out.println(a);

            if (!a.getDisciplinasMatriculadas().isEmpty()) {
                System.out.print("   -> Cursando: ");
                for (Disciplina d : a.getDisciplinasMatriculadas()) {
                    System.out.print("[" + d.getNomeDisciplina() + "] ");
                }
                System.out.println();
            }
        }
    }

    public void deletarAluno(String nome) {
        alunoService.deletar(nome);
    }

    public Aluno buscarAluno(String nome) {
        return alunoService.buscar(nome);
    }

    public Aluno getAlunoById(int id) {
        return alunoService.getAlunoById(id);
    }

    public void matricularAluno(Scanner sc) {
        System.out.println("\n=== MATRICULAR ALUNO EM NOVA DISCIPLINA ===");

        Aluno alunoEncontrado = null;

        while (alunoEncontrado == null) {
            System.out.print("Digite o Nome do aluno ou a Matrícula (ou '0' para sair): ");
            String entrada = sc.nextLine().trim();

            if (entrada.equals("0")) {
                return;
            }

            try {
                int id = Integer.parseInt(entrada);
                alunoEncontrado = alunoService.getAlunoById(id);
            } catch (NumberFormatException e) {
                alunoEncontrado = alunoService.buscar(entrada);
            }

            if (alunoEncontrado == null) {
                System.out.println("❌ Aluno não encontrado! Tente novamente.");
            }
        }

        System.out.println("Aluno selecionado: " + alunoEncontrado.getNome());

        System.out.println("--- Disciplinas Disponíveis ---");
        List<Disciplina> disciplinas = disciplinaService.listarDisciplinas();

        if (disciplinas.isEmpty()) {
            System.out.println("Não há disciplinas cadastradas no sistema.");
            return;
        }

        for (Disciplina d : disciplinas) {
            System.out.println("ID: " + d.getIdDisciplina() + " - " + d.getNomeDisciplina());
        }

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
            if(alunoEncontrado.getDisciplinasMatriculadas().contains(disciplinaParaAdicionar)){
                System.out.println("⚠ Este aluno já cursa essa matéria.");
            } else {
                alunoEncontrado.adicionarDisciplina(disciplinaParaAdicionar);
                System.out.println("✅ Sucesso! Disciplina '" + disciplinaParaAdicionar.getNomeDisciplina() +
                        "' adicionada ao aluno " + alunoEncontrado.getNome());
            }
        } else {
            System.out.println("Disciplina com ID " + idDisciplina + " não encontrada.");
        }
    }
}
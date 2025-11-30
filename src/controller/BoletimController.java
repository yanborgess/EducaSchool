package controller;

import model.Aluno;
import model.Disciplina;
import service.BoletimService;

import java.util.Scanner;

public class BoletimController {

    private BoletimService boletimService = new BoletimService();

    public void registrar(Scanner sc, AlunoController alunoController, DisciplinaController disciplinaController) {
        System.out.println("Digite o ID do aluno: ");
        int idAluno = Integer.parseInt(sc.nextLine());
        Aluno aluno = alunoController.getAlunoById(idAluno);

        if (aluno == null) {
            System.out.println("❗ ERRO: Aluno não encontrado!");
            return;
        }

        System.out.println("Digite o ID da disciplina: ");
        int idDisciplina = Integer.parseInt(sc.nextLine());
        Disciplina disciplina = disciplinaController.getDisciplinaById(idDisciplina);

        if (disciplina == null) {
            System.out.println("❗ ERRO: Disciplina não encontrada!");
            return;
        }

        System.out.println("Informe a nota: ");
        double nota = Double.parseDouble(sc.nextLine());

        System.out.println("Informe as faltas: ");
        int faltas = Integer.parseInt(sc.nextLine());

        boletimService.registrarNota(aluno, disciplina, nota, faltas);
        System.out.println("Registro gravado com sucesso!");
    }


    public void consultarBoletim(Scanner sc, AlunoController alunoController) {
        System.out.println("Digite o ID do aluno: ");
        int idAluno = Integer.parseInt(sc.nextLine());
        Aluno aluno = alunoController.getAlunoById(idAluno);

        if (aluno == null) {
            System.out.println("❗ ERRO: Aluno não encontrado!");
            return;
        }

        boletimService.imprimirBoletim(aluno);
    }
    }


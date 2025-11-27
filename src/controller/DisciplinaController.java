package controller;

import model.Disciplina;
import model.Professor;
import service.DisciplinaService;
import service.ProfessorService;

import java.util.List;
import java.util.Scanner;

public class DisciplinaController {
    private DisciplinaService disciplinaService = new DisciplinaService();
    private ProfessorService professorService = new ProfessorService();

    public void  cadastrarDisciplina(Disciplina disciplina){
        disciplinaService.cadastraDisciplina(disciplina);
    }

    public void cadastrarDisciplina(Scanner sc){
        Disciplina d = new Disciplina();
        System.out.println("\n=== CADASTRO DE DISCIPLINA ===");
        System.out.print("Digite o nome da disciplina: ");
        d.setNomeDisciplina(sc.nextLine());
        System.out.print("Digite a carga horaria: ");
        d.setCargaHoraria(sc.nextInt());
        sc.nextLine();
        System.out.println("Quem é o professor ministrante:");
        String nomeProf = sc.nextLine();

        Professor profEncontrado = professorService.buscarProfessor(nomeProf);
        if (profEncontrado != null){
            d.setProfessor(profEncontrado);
            disciplinaService.cadastraDisciplina(d);
            System.out.println("Disciplina cadastrada com sucesso! ID: " + d.getIdDisciplina());
        } else {
            System.out.println("ERRO: Professor não encontrado! Cadastre um professor antes.");
        }
    }
    public List<Disciplina> listarDisciplinas(){
        List<Disciplina> lista = disciplinaService.listarDisciplinas();
        for(Disciplina disc : lista) {
            System.out.println("ID: " + disc.getIdDisciplina() +
                    " | Matéria: " + disc.getNomeDisciplina() +
                    " | Prof: " + disc.getProfessor().getNome());
        }
        return lista;
    }

    public Disciplina buscarDisciplina(String nomeDiscplina){
        return  disciplinaService.buscarDisciplina(nomeDiscplina);
    }





}

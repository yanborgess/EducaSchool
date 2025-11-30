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


        String nome = "";
        while(nome.trim().isEmpty()){
            System.out.print("Digite o nome da disciplina: ");
            nome = sc.nextLine();
        }
        d.setNomeDisciplina(nome);


        int carga = 0;
        while (carga <= 0) {
            System.out.print("Digite a carga horaria: ");
            try {
                carga = Integer.parseInt(sc.nextLine());
                if (carga <= 0) System.out.println("A carga horária deve ser positiva.");
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        }
        d.setCargaHoraria(carga);


        System.out.println("Quem é o professor ministrante:");
        String nomeProf = "";
        while (nomeProf.trim().isEmpty()){
            nomeProf = sc.nextLine().trim();
        }


        Professor profEncontrado = professorService.buscarProfessor(nomeProf);
        if (profEncontrado != null){
            d.setProfessor(profEncontrado);
            disciplinaService.cadastraDisciplina(d);
            System.out.println("Disciplina cadastrada com sucesso! ID: " + d.getIdDisciplina());
            System.out.println("Vinculado ao Prof : " + profEncontrado.getNome());
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

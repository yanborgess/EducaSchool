package service;

import model.Aluno;

import java.util.ArrayList;
import java.util.List;


public class AlunoService {
    private  static List<Aluno> alunos = new ArrayList<>();

    private static  int contMatricula = 2025001;

    public void cadastrarAluno(Aluno aluno) {
        aluno.setMatricula(contMatricula);
        contMatricula++;
        alunos.add(aluno);
    }
    public List<Aluno> listarAluno() {
        return alunos;
    }

    public void deletarAluno(String nome) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNome().equalsIgnoreCase(nome)) {
                alunos.remove(i);
                System.out.println("Deletado com sucesso!");
                return;
            }
        }
        System.out.println("Aluno não encontrado.");
    }

    public Aluno buscarAluno(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return aluno;
            }
        }
        return null;
    }
    public Aluno getAlunoById(int id){
        for(Aluno a : alunos){
            if(a.getId() == id){
                return a;
            }
        }
        return null;
    }



}


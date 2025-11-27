package service;

import model.Aluno;

import java.util.ArrayList;
import java.util.List;


public class AlunoService {
    private List<Aluno> alunos = new ArrayList<>();

    public void cadastrarAluno(Aluno aluno) {
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
            if (aluno.getNome().equalsIgnoreCase(nome)) {
                return aluno;
            }
        }
        return null;
    }



}


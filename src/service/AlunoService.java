package service;

import model.Aluno;
import model.interfaces.ICrudService;

import java.util.ArrayList;
import java.util.List;

public class AlunoService implements ICrudService<Aluno> {

    private static List<Aluno> alunos = new ArrayList<>();
    private static int contMatricula = 2025001;

    @Override
    public void cadastrar(Aluno aluno) {
        aluno.setMatricula(contMatricula);
        contMatricula++;
        alunos.add(aluno);
    }

    @Override
    public List<Aluno> listar() {
        return alunos;
    }

    @Override
    public void deletar(String nome) {
        for (int i = 0; i < alunos.size(); i++) {
            if (alunos.get(i).getNome().equalsIgnoreCase(nome)) {
                alunos.remove(i);
                System.out.println("Deletado com sucesso!");
                return;
            }
        }
        System.out.println("Aluno não encontrado.");
    }

    @Override
    public Aluno buscar(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return aluno;
            }
        }
        return null;
    }

    public Aluno getAlunoById(int id) {
        for (Aluno a : alunos) {
            if (a.getMatricula() == id) {
                return a;
            }
        }
        return null;
    }
}
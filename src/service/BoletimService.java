package service;
import model.*;
import java.util.ArrayList;
import java.util.List;

public class BoletimService {

    private List<Boletim> boletins = new ArrayList<>();

    public void registrarNota(Aluno aluno, Disciplina disciplina, double nota, int faltas) {
        Boletim boletim = new Boletim(aluno, disciplina, nota, faltas);
        boletins.add(boletim);
    }

    public List<Boletim> getBoletimAluno(Aluno aluno) {
        List<Boletim> lista = new ArrayList<>();
        for (Boletim b : boletins) {
            if (b.getAluno().equals(aluno)) {
                lista.add(b);
            }
        }
        return lista;
    }

    public void imprimirBoletim(Aluno aluno) {
        System.out.println("===== BOLETIM DO ALUNO =====");
        System.out.println("Aluno: " + aluno.getNome());
        for (Boletim b : boletins) {
            if (b.getAluno().equals(aluno)) {
                System.out.println(b);
            }
        }
    }
}

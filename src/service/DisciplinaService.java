package service;

import model.Disciplina;

import java.util.ArrayList;
import java.util.List;

public class DisciplinaService {
    private static List<Disciplina> disciplinas = new ArrayList<>();

    private static int contadorId = 1;

    public void cadastraDisciplina(Disciplina disciplina) {
        disciplina.setIdDisciplina(contadorId);
        contadorId++;
        disciplinas.add(disciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }

    public Disciplina buscarDisciplina(String nomeDisciplina) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getNomeDisciplina().equalsIgnoreCase(nomeDisciplina)) {
                return disciplina;
            }
        }
        return null;
    }


}

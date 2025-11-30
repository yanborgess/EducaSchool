package model;

public class Boletim {

    private Aluno aluno;
    private Disciplina disciplina;
    private double nota;
    private int faltas;

    public Boletim(Aluno aluno, Disciplina disciplina, double nota, int faltas) {
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.nota = nota;
        this.faltas = faltas;
    }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Disciplina getDisciplina() { return disciplina; }
    public void setDisciplina(Disciplina disciplina) { this.disciplina = disciplina; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public int getFaltas() { return faltas; }
    public void setFaltas(int faltas) { this.faltas = faltas; }

    public boolean isAprovado() {
        return nota >= 6 && faltas <= disciplina.getCargaHoraria() * 0.25;
    }

    @Override
    public String toString() {
        return "Aluno: " + aluno.getNome() +
                " | Disciplina: " + disciplina.getNomeDisciplina() +
                " | Nota: " + nota +
                " | Faltas: " + faltas +
                " | Situação: " + (isAprovado() ? "APROVADO" : "REPROVADO");
    }
}

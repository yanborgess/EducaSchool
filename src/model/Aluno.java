package model;

import enums.TipoCurso;

import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private int matricula;
    private String nome;
    private TipoCurso curso;
    private double media;

    private List<Disciplina> disciplinasMatriculadas = new ArrayList<>();
    private  List<Mensalidade> mensalidades = new ArrayList<>();

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoCurso getCurso() { return curso; }
    public void setCurso(TipoCurso curso) { this.curso = curso; }

    public double getMedia() { return media; }
    public void setMedia(double media) { this.media = media; }


    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }
    public void adicionarDisciplina(Disciplina disciplina) {
        this.disciplinasMatriculadas.add(disciplina);
    }
    public List<Mensalidade> getMensalidades(){
        return mensalidades;
    }
    public void adicionarMensalidade(Mensalidade mensalidade){
        this.mensalidades.add(mensalidade);
    }

    @Override
    public String toString() {
        return "Nome: " + getNome() + " | Matrícula: " + getMatricula() +
                " | Curso: " + (curso != null ? curso.getDescricao() : "N/A") +
                " | Matérias: " + disciplinasMatriculadas.size();
    }


    public int getId() {
        return 0;
    }
}
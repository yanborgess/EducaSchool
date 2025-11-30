package model;

public class Professor extends Pessoa {
        private int idDiscplina;
        private String formacao;

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }
    @Override
    public String toString() {
        return "Nome: " + getNome() + " | Área: " + getArea() + " | Formação: " + getFormacao();
    }
}

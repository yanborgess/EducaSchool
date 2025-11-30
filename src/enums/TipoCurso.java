package enums;

public enum TipoCurso {
    BACHARELADO("Bacharelado"),
    TECNOLOGO("Tecnólogo"),
    LICENCIATURA("Licenciatura");

    private final String descricao;

    TipoCurso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
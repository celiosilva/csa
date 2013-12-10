package br.com.delogic.showcase.entity.enums;

public enum UnidadeFederativa {

    SP("São Paulo"), MG("Minas Gerais"), RJ("Rio de Janeiro"), PR("Paraná");

    private final String descricao;

    private UnidadeFederativa(String desc) {
        this.descricao = desc;
    }

    public String getDescricao() {
        return descricao;
    }

}

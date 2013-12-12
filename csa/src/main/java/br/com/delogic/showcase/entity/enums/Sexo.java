package br.com.delogic.showcase.entity.enums;

public enum Sexo {

	MASCULINO("Masculino"), FEMININO("Feminino");

	private final String descricao;

	private Sexo(String desc) {
		this.descricao = desc;
	}

	public String getDescricao() {
		return descricao;
	}

}

package br.com.delogic.showcase.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.delogic.showcase.entity.enums.Sexo;
import br.com.delogic.showcase.repository.LongEntityId;

public class User extends LongEntityId {

	@NotEmpty
	@Length(min = 5, max = 50)
	private String nome;

	@NotNull
	private Sexo sexo;

	@NotNull
	private Date dataNascimento;

	@NotEmpty
	private String imagem;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

}
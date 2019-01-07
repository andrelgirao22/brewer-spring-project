package br.com.brewer.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Cerveja {

	@NotBlank
	private String sku;
	
	@NotBlank
	private String nome;
	
	@Size(min=1,max=50, message="A descrição deve estar entre 1 e 50.")
	private String descricao;
	
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
	@Override
	public String toString() {
		return "sku " + sku + " nome " + nome; 
	}
}

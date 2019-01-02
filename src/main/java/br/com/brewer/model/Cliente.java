package br.com.brewer.model;

import org.hibernate.validator.constraints.NotBlank;

public class Cliente {

	@NotBlank
	private String nome;
	
	private String cpfCnpj;
	
	private String telefone;
	private String email;
	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	
}

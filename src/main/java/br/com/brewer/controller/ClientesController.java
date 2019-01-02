package br.com.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientesController {

	@RequestMapping(value="/clientes/novo")
	public String novo() {
		return "cliente/CadastroCliente";
	}
	
	
}

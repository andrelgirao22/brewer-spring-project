package br.com.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.brewer.model.Cerveja;
import br.com.brewer.model.Estilo;
import br.com.brewer.repository.Estilos;
import br.com.brewer.service.CadastroCervejaService;
import br.com.brewer.service.CadastroEstiloService;
import br.com.brewer.service.exception.NomeEstiloJaCadastradoException;

@Controller
public class EstilosController {

	
	@Autowired
	private CadastroEstiloService cadastroEstiloService;
	
	@RequestMapping("estilos/novo")
	public ModelAndView novo(Estilo estilo) {
		ModelAndView mv = new ModelAndView("estilo/CadastroEstilo");
		return mv;
	}
	
	@RequestMapping(value="/estilos/novo", method=RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Estilo estilo, BindingResult result, Model model, RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			return this.novo(estilo);
		}
		
		try {
			cadastroEstiloService.salvar(estilo);
		} catch(NomeEstiloJaCadastradoException ex) {
			result.rejectValue("nome", ex.getMessage(), ex.getMessage());
			return this.novo(estilo);
		}
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso");
		return new ModelAndView("redirect:/estilos/novo");
	}
	
}

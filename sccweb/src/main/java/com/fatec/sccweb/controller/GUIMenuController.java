package com.fatec.sccweb.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * Essa classe recebe as requisições do cliente e retorna a view
 */
@Controller
public class GUIMenuController {
	
	/**
	 * processa a requisicao localhost:8080/login
	 * @return view html
	 */
	@GetMapping("/login")
	public ModelAndView autenticacao() {
		return new ModelAndView("paginaLogin");
	}
	/**
	 * processa a requisicao localhost:8080/
	 * @return view html
	 */
	@GetMapping("/")
	public ModelAndView home() {
		return new ModelAndView ("paginaMenu");
	}
}
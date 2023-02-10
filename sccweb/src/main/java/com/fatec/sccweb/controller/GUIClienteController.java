package com.fatec.sccweb.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.sccweb.model.Cliente;

@Controller
@RequestMapping(path = "/sig")
public class GUIClienteController {
	Logger logger = LogManager.getLogger(GUIClienteController.class);

	@GetMapping("/clientes")
	public ModelAndView retornaFormDeConsultaTodosClientes() {
		ModelAndView mv = new ModelAndView("consultarCliente");
		mv.addObject("clientes", consultaTodos());
		return mv;
	}

	public List<Cliente> consultaTodos() {
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/api/v1/clientes";
		logger.info(">>>>>> consulTodos clientes chamado");

		try {
			ListaDeClientes resposta = template.getForObject(url, ListaDeClientes.class);
			List<Cliente> lista = resposta.getLista();

			return lista;

		} catch (ResourceAccessException e) {
			logger.info(">>>>>> consulta CEP erro nao esperado ");
			return null;

		} catch (HttpClientErrorException e) {
			logger.info(">>>>>> consultaTodos clientes erro HttpClientErrorException =>" + e.getMessage());
			return null;

		}
		
	}
}

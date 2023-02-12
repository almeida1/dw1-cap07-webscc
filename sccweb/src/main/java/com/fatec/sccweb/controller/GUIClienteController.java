package com.fatec.sccweb.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.sccweb.model.Cliente;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/v1/clientes";
		logger.info(">>>>>> consulTodos clientes chamado");

		try {
			ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);
			logger.info(">>>>>> consulTodos clientes chamou api retorno =>" + resposta.getBody());
			/*
			 * converte json array para java object 
			 */
			Gson gson = new Gson();
			Type tipoLista = new TypeToken<ArrayList<Cliente>>() {	}.getType();
			ArrayList<Cliente> lista = gson.fromJson(resposta.getBody(), tipoLista);
			return lista;
		} catch (ResourceAccessException e) {
			logger.info(">>>>>> consultaTodos clientes erro Resource Exception => " + e.getMessage());
			return null;

		} catch (HttpClientErrorException e) {
			logger.info(">>>>>> consultaTodos clientes erro HttpClientErrorException =>" + e.getMessage());
			return null;

		} catch (Exception e) {
			logger.info(">>>>>> consultaTodos clientes erro não esperado =>" + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
}

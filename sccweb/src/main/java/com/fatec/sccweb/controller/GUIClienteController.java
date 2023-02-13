package com.fatec.sccweb.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fatec.sccweb.model.Cliente;
import com.fatec.sccweb.model.ClienteDTO;
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
	@GetMapping("/cliente")
	public ModelAndView retornaFormDeCadastroDe(Cliente cliente) {
		ModelAndView mv = new ModelAndView("cadastrarCliente");
		
		
		mv.addObject("cliente", cliente);
		return mv;
	}
	@PostMapping("/clientes")
	public ModelAndView save(@Valid Cliente cliente, BindingResult result) {
		ModelAndView mv = new ModelAndView("consultarCliente");
		if (result.hasErrors()) {
			
			
			mv.setViewName("cadastrarCliente");
		} else {
			try {
				logger.info(">>>>>> controller save chamado");
				save(cliente);
				mv.addObject("clientes", consultaTodos());
			}  catch (ResourceAccessException e) {
				logger.info(">>>>>> controller save erro Resource Exception => " + e.getMessage());
				mv.setViewName("cadastrarCliente");
				mv.addObject("message", "Dados invalidos" + e.getMessage());

			}catch (HttpClientErrorException e) {
				logger.info(">>>>>> controller save erro HttpClientErrorException =>" + e.getMessage());
				mv.setViewName("cadastrarCliente");
				mv.addObject("message", "Dados invalidos" + e.getMessage());

			} catch(Exception e) {
				logger.info(">>>>>> controller cadastrar com dados invalidos =>" + e.getMessage());
				mv.setViewName("cadastrarCliente");
				mv.addObject("message", "Dados invalidos" + e.getMessage());
			}
			
		}
		return mv;
	}
	
	public String save(Cliente cliente) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/v1/clientes";
		logger.info(">>>>>> save clientes chamado");
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Cliente> entity = new HttpEntity<Cliente>(cliente,headers);
	    return restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		
	}

	public List<ClienteDTO> consultaTodos() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/api/v1/clientes";
		logger.info(">>>>>> consultaTodos clientes chamado");

		try {
			ResponseEntity<String> resposta = restTemplate.getForEntity(url, String.class);
			logger.info(">>>>>> consultaTodos clientes chamou api retorno =>" + resposta.getBody());
			/*
			 * converte json array para java object 
			 */
			Gson gson = new Gson();
			Type tipoLista = new TypeToken<ArrayList<ClienteDTO>>() {	}.getType();
			ArrayList<ClienteDTO> lista = gson.fromJson(resposta.getBody(), tipoLista);
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

package com.fatec.sccweb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fatec.sccweb.controller.GUIClienteController;

@WebMvcTest(GUIClienteController.class)
class REQ01ConsultarCliente {
	@Autowired
	private MockMvc mvc;

	@Test
	void ct01_consultar_todos_clientes_com_sucesso() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/sig/clientes").contentType("application/json")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("consultarCliente"));
	}

	@Test
	void ct02_consultar_todos_clientes_com_sucesso_retorna_pagina_html() throws Exception {
		// when
		MockHttpServletResponse response = mvc.perform(get("/sig/clientes").accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse();

		// then

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}
}

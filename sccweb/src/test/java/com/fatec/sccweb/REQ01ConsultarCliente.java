package com.fatec.sccweb;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fatec.sccweb.controller.GUIClienteController;

@WebMvcTest(GUIClienteController.class)
class REQ01ConsultarCliente {
	@Autowired
	  private MockMvc mvc;

	@Test
	void ct01_consultar_todos_clientes_com_sucesso() throws Exception  {
		mvc.perform(MockMvcRequestBuilders
				.get("/sig/clientes")
				.contentType("application/json")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		 
	}

}

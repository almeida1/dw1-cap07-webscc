package com.fatec.sccweb.controller;

import java.util.ArrayList;
import java.util.List;

import com.fatec.sccweb.model.ClienteDTO;

public class ListaDeClientes {
	private List<ClienteDTO> lista;

    public ListaDeClientes() {
        lista= new ArrayList<>();
    }

	public List<ClienteDTO> getLista() {
		return lista;
	}

	public void setLista(List<ClienteDTO> lista) {
		this.lista = lista;
	}
    
}
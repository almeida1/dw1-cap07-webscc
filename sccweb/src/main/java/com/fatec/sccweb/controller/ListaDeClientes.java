package com.fatec.sccweb.controller;

import java.util.ArrayList;
import java.util.List;

import com.fatec.sccweb.model.Cliente;

public class ListaDeClientes {
	private List<Cliente> lista;

    public ListaDeClientes() {
        lista= new ArrayList<>();
    }

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
    
}
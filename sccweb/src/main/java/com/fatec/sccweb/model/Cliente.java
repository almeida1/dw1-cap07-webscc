package com.fatec.sccweb.model;


public class Cliente {
	
	private String id;
	
	private String nome;
	
	private String dataNascimento;
	
	private String dataCadastro;
	
	private String sexo;
	
	private String cpf;
	
	private String cep;
	
	private String endereco;
	
	private String complemento;
	public Cliente(String id, String nome, String dataNascimento, String sexo, String cpf,  String cep, String complemento) {
		this.id = id;
		this.nome = nome;
		setDataNascimento(dataNascimento);
		this.sexo = sexo;
		this.cpf = cpf;
		this.cep = cep;
		this.complemento = complemento;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Cliente retornaUmCliente(){
	    return new Cliente(id,nome, dataNascimento, sexo, cpf, cep, complemento);
	}
}



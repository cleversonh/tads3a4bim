package br.univel.rest;

import java.io.Serializable;

public class Aluno implements Serializable{
	
	private long matricula;
	private String nome;
	public long getMatricula() {
		return matricula;
	}
	public void setMatricula(long matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}

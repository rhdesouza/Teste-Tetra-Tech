package com.tetraTech.model;

public class Populacao {

	private String localidade;
	private String horario;
	private Projecao projecao;
	
	
	public Populacao() {
		super();
	}

	public Populacao(String localidade, String horario, Projecao projecao) {
		super();
		this.localidade = localidade;
		this.horario = horario;
		this.projecao = projecao;
	}
	

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Projecao getProjecao() {
		return projecao;
	}

	public void setProjecao(Projecao projecao) {
		this.projecao = projecao;
	}

	
	
}

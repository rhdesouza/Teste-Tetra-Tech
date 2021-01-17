package com.tetraTech.model;

public class Projecao {

	private long populacao;
	private PeriodoMedio periodoMedio;
	
	public Projecao() {
		super();
	}
	
	public Projecao(long populacao, PeriodoMedio periodoMedio) {
		this.populacao = populacao;
		this.periodoMedio = periodoMedio;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	public PeriodoMedio getPeriodoMedio() {
		return periodoMedio;
	}

	public void setPeriodoMedio(PeriodoMedio periodoMedio) {
		this.periodoMedio = periodoMedio;
	}
	
}

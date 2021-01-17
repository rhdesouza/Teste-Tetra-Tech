package com.tetraTech.model;

public class PeriodoMedio {

	private long incrementoPopulacional;
	private long nascimento;
	private long obito;
	
	public PeriodoMedio() {
		super();
	}

	public PeriodoMedio(long incrementoPopulacional, long nascimento, long obito) {
		this.incrementoPopulacional = incrementoPopulacional;
		this.nascimento = nascimento;
		this.obito = obito;
	}

	public long getIncrementoPopulacional() {
		return incrementoPopulacional;
	}

	public void setIncrementoPopulacional(long incrementoPopulacional) {
		this.incrementoPopulacional = incrementoPopulacional;
	}

	public long getNascimento() {
		return nascimento;
	}

	public void setNascimento(long nascimento) {
		this.nascimento = nascimento;
	}

	public long getObito() {
		return obito;
	}

	public void setObito(long obito) {
		this.obito = obito;
	}
	
	
	
}

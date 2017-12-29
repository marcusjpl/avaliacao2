package br.com.prova.models;

public class RetornoEstatistica {
	
	private Integer numeroPalavras;
	private Integer tamanhoPalavraMaisCurta;
	private Integer tamanhoPalavraMaisLonga;
	private Double mediaTamanhoPalavras;
	
	
	public RetornoEstatistica(Integer numeroPalavras, Integer tamanhoPalavraMaisCurta, Integer tamanhoPalavraMaisLonga,
			Double mediaTamanhoPalavras) {
		super();
		this.numeroPalavras = numeroPalavras;
		this.tamanhoPalavraMaisCurta = tamanhoPalavraMaisCurta;
		this.tamanhoPalavraMaisLonga = tamanhoPalavraMaisLonga;
		this.mediaTamanhoPalavras = mediaTamanhoPalavras;
	}

	public Integer getNumeroPalavras() {
		return numeroPalavras;
	}

	public void setNumeroPalavras(Integer numeroPalavras) {
		this.numeroPalavras = numeroPalavras;
	}

	public Integer getTamanhoPalavraMaisCurta() {
		return tamanhoPalavraMaisCurta;
	}

	public void setTamanhoPalavraMaisCurta(Integer tamanhoPalavraMaisCurta) {
		this.tamanhoPalavraMaisCurta = tamanhoPalavraMaisCurta;
	}

	public Integer getTamanhoPalavraMaisLonga() {
		return tamanhoPalavraMaisLonga;
	}

	public void setTamanhoPalavraMaisLonga(Integer tamanhoPalavraMaisLonga) {
		this.tamanhoPalavraMaisLonga = tamanhoPalavraMaisLonga;
	}

	public Double getMediaTamanhoPalavras() {
		return mediaTamanhoPalavras;
	}

	public void setMediaTamanhoPalavras(Double mediaTamanhoPalavras) {
		this.mediaTamanhoPalavras = mediaTamanhoPalavras;
	}
	
	
	

}

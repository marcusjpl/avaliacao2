package br.com.prova.models;

public class ResultadoProcurar {
	
	private Long id;
	private String conteudo;
	private String autor;
	
	public ResultadoProcurar(Long id, String conteudo, String autor) {
		super();
		this.id = id;
		this.conteudo = conteudo;
		this.autor = autor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	

}

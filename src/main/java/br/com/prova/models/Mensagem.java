package br.com.prova.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mensagem")
public class Mensagem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long idTwitter;

	private String conteudo;
	private String fonte;
	private String usuario;
	
	private String maiores;
	private String menores;
	private String maisComum;
	
	private Double media;
	private Integer numeroPalavras;
	private Integer tamanhoMaisCurta;
	private Integer tamanhoMaisLonga;
	
	
	@OneToMany(mappedBy = "mensagem", targetEntity = Palavra.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Palavra> palavras;

	public Mensagem() {
		
	}

	public Mensagem(Long idTwitter, String conteudo, String fonte, String usuario) {
		super();
		this.idTwitter = idTwitter;
		this.conteudo = conteudo;
		this.fonte = fonte;
		this.usuario = usuario;
	}
	

	public String getMaiores() {
		return maiores;
	}

	public void setMaiores(String maiores) {
		this.maiores = maiores;
	}

	public String getMenores() {
		return menores;
	}

	public void setMenores(String menores) {
		this.menores = menores;
	}

	public Double getMedia() {
		return media;
	}

	public void setMedia(Double media) {
		this.media = media;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public List<Palavra> getPalavras() {
		return palavras;
	}

	public void setPalavras(List<Palavra> palavras) {
		this.palavras = palavras;
	}

	public Long getIdTwitter() {
		return idTwitter;
	}

	public void setIdTwitter(Long idTwitter) {
		this.idTwitter = idTwitter;
	}

	public Integer getNumeroPalavras() {
		return numeroPalavras;
	}

	public void setNumeroPalavras(Integer numeroPalavras) {
		this.numeroPalavras = numeroPalavras;
	}

	public Integer getTamanhoMaisCurta() {
		return tamanhoMaisCurta;
	}

	public void setTamanhoMaisCurta(Integer tamanhoMaisCurta) {
		this.tamanhoMaisCurta = tamanhoMaisCurta;
	}

	public Integer getTamanhoMaisLonga() {
		return tamanhoMaisLonga;
	}

	public void setTamanhoMaisLonga(Integer tamanhoMaisLonga) {
		this.tamanhoMaisLonga = tamanhoMaisLonga;
	}

	public String getMaisComum() {
		return maisComum;
	}

	public void setMaisComum(String maisComum) {
		this.maisComum = maisComum;
	}
	
}

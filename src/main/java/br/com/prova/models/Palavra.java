package br.com.prova.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "palavra")
public class Palavra {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String valor;
	
	@ManyToOne
	@JoinColumn(name="mensagem_id")
	private Mensagem mensagem;
	
	public Palavra() {
		
	}
	
	public Palavra(String valor,Mensagem m) {
		this.valor = valor;
		this.mensagem = m;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Palavra [valor=" + valor + "]";
	}

}

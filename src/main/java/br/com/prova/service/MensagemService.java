package br.com.prova.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.prova.daos.MensagemDao;
import br.com.prova.models.Mensagem;
import br.com.prova.models.Palavra;
import br.com.prova.models.ResultadoProcurar;
import br.com.prova.models.RetornoEstatistica;

public class MensagemService {

	@Inject
	private MensagemDao mensagemDao;

	public void salvarLista(List<Mensagem> listaTwitters) {

		for (Mensagem m : listaTwitters) {
			m.setPalavras(new ArrayList<Palavra>());
			
			String[] listaPalavras = StringUtils.split(m.getConteudo(), " ");

			m.setMaiores(getMaioresPalavras(listaPalavras));
			m.setMenores(getMenoresPalavras(listaPalavras));
			m.setMedia(getMediaTamanho(listaPalavras));
			m.setTamanhoMaisCurta(getTamanhoDaMaior(listaPalavras));
			m.setTamanhoMaisLonga(getTamanhoDaMenor(listaPalavras));
			m.setNumeroPalavras(listaPalavras.length);

			for (String palavra : listaPalavras) {
				m.getPalavras().add(new Palavra(palavra,m));
			}
		}

		mensagemDao.salvarLista(listaTwitters);
	}

	public List<ResultadoProcurar> consultarPorConteudo(String valor) {
		
		List<Mensagem> msgs = mensagemDao.consultarPorConteudo(valor);
		
		List<ResultadoProcurar> resultado = new ArrayList<ResultadoProcurar>();
		for (Mensagem m : msgs) {
			resultado.add(new ResultadoProcurar(m.getIdTwitter(), m.getConteudo(), m.getUsuario()));
		}

		return resultado;
	}
	
	public List<RetornoEstatistica> consultarPorTwitterId(Long id) {
		List<Mensagem> msgs = mensagemDao.consultarPorTwitterId(id);
		List<RetornoEstatistica> retorno = new ArrayList<RetornoEstatistica>();
		for (Mensagem m : msgs) {
			retorno.add(new RetornoEstatistica(
					m.getNumeroPalavras(), 
					m.getTamanhoMaisCurta(), 
					m.getTamanhoMaisLonga(), 
					m.getMedia()));
		}
		
		return retorno;
	}

	public Double getMediaTamanho(String[] listaPalavras) {
		Integer soma = 0;
		Double media = 0.0;
		Integer qtd = 0;
		for (String palavra : listaPalavras) {
			soma = soma + palavra.length();
			qtd++;
		}
		media = soma * 1.0 / qtd;
		return media;
	}

	public String getMenoresPalavras(String[] listaPalavras) {
		Integer maxLength = 1000;
		String menoresString = null;
		for (String s : listaPalavras) {
			if (s.length() <= maxLength) {
				if (s.length() == maxLength) {
					menoresString = menoresString + " " + s;
				} else {
					maxLength = s.length();
					menoresString = s;
				}
			}
		}

		String[] lista = StringUtils.split(menoresString, " ");
		String ordenadas = ordenarArray(lista);
		return ordenadas;
	}

	public String getMaioresPalavras(String[] listaPalavras) {
		Integer maxLength = 0;
		String maioresString = null;
		for (String s : listaPalavras) {
			if (s.length() >= maxLength) {
				if (s.length() == maxLength) {
					maioresString = maioresString + " " + s;
				} else {
					maxLength = s.length();
					maioresString = s;
				}
			}
		}

		String[] lista = StringUtils.split(maioresString, " ");
		String ordenadas = ordenarArray(lista);

		return ordenadas;
	}

	public Integer getTamanhoDaMaior(String[] array) {
		int maxLength = 0;
		String maior = null;
		for (String s : array) {
			if (s.length() > maxLength) {
				maxLength = s.length();
				maior = s;
			}
		}
		return maior.length();
	}
	
	public Integer getTamanhoDaMenor(String[] array) {
		int maxLength = 1000;
		String maior = null;
		for (String s : array) {
			if (s.length() < maxLength) {
				maxLength = s.length();
				maior = s;
			}
		}
		return maior.length();
	}
	

	private String ordenarArray(String[] ordenadas) {
		Arrays.sort(ordenadas, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1 == null && o1 == null)
					return 0;
				if (o1 == null)
					return 1; // 1
				if (o2 == null)
					return -1; // 2
				return o1.compareTo(o2);
			}
		});

		String str = String.join(" ", ordenadas);
		return str;
	}

	public String consultaPalavraMaisLonga() {
		return mensagemDao.consultaPalavraMaisLonga();
	}

	

}

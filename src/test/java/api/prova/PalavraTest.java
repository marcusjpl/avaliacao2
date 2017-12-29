package api.prova;


import org.junit.Assert;
import org.junit.Test;

import br.com.prova.service.MensagemService;

public class PalavraTest {
	
	private MensagemService mensagemService= new MensagemService();

	@Test
	public void maiorPalavra() {
		String[] palavras = {"teste", "tst", "palavrao"};
		String retorno = mensagemService.getMaioresPalavras(palavras);
		
		Assert.assertEquals("palavrao", retorno);
	}
	
	@Test
	public void maioresPalavras() {
		String[] palavras = {"teste", "tst", "palavrao", "qualquer"};
		String retorno = mensagemService.getMaioresPalavras(palavras);
		
		Assert.assertEquals("palavrao qualquer", retorno);
	}
	
	@Test
	public void menorPalavra() {
		String[] palavras = {"teste", "tst", "palavrao"};
		String retorno = mensagemService.getMenoresPalavras(palavras);
		
		Assert.assertEquals("tst", retorno);
	}
	
	@Test
	public void menoresPalavras() {
		String[] palavras = {"teste", "tst", "palavrao", "qualquer", "ab", "ac"};
		String retorno = mensagemService.getMenoresPalavras(palavras);
		
		Assert.assertEquals("ab ac", retorno);
	}
	
	
	@Test
	public void mediaPalavras() {
		String[] palavras = {"teste", "tst", "palavrao", "qualquer", "ab", "ac"};
		Double retorno = mensagemService.getMediaTamanho(palavras);
		
		Assert.assertEquals(new Double(4.666666666666667), retorno);
	}
	
	@Test
	public void tamanhoDaMenor() {
		String[] palavras = {"teste", "tst", "palavrao", "qualquer", "ab", "ac"};
		Integer retorno = mensagemService.getTamanhoDaMenor(palavras);
		
		Assert.assertEquals(new Integer(2), retorno);
	}
	
	@Test
	public void tamanhoDaMaior() {
		String[] palavras = {"teste", "tst", "palavrao", "qualquer", "ab", "ac"};
		Integer retorno = mensagemService.getTamanhoDaMaior(palavras);
		
		Assert.assertEquals(new Integer(8), retorno);
	}
	

}

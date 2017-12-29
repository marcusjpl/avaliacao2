package br.com.prova.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.google.gson.Gson;

import br.com.prova.models.ConsultaRest;
import br.com.prova.models.Mensagem;
import br.com.prova.models.ResultadoProcurar;
import br.com.prova.models.RetornoEstatistica;
import br.com.prova.service.MensagemService;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Path("/tweet")
public class MensagemBean {

	@Inject
	private MensagemService mensagemService;
	
	private static Logger log = Logger.getLogger(MensagemBean.class);

	static String consumerKeyStr = "642QJxQzOevvin5678vVXrZeV";
	static String consumerSecretStr = "BiOHlQJIA6diJaxuBKyuhWsyvAdsBS1mm9tFqewFf2PRTikEzg";
	static String accessTokenStr = "1546932464-C4ZQ8DA9yVZERUpK9JAMHfXVouyiugowFyw9b2R";
	static String accessTokenSecretStr = "9LtHzbA4pBdbeehTru35Fj64jFj1gy8rtQJxMA9L7O5Tn";

	@POST
	@Path("/captar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response captar(String json) {
		
		try {
			ConsultaRest consulta = jsonToObject(json);
			List<Mensagem> listaTwitters = new ArrayList<Mensagem>();
			
			Twitter twitter = new TwitterFactory().getInstance();

			twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
			AccessToken accessToken = new AccessToken(accessTokenStr, accessTokenSecretStr);

			twitter.setOAuthAccessToken(accessToken);

			twitter.updateStatus("Post using Twitter4J Again"+ new Date().toString());

			log.info("Successfully updated the status in Twitter.");

			QueryResult qr = twitter.search(new Query(consulta.getBusca()));

			for (Status msg : qr.getTweets()) {
				listaTwitters.add(new Mensagem(msg.getId(),msg.getText(), msg.getSource(), msg.getUser().getName()));
			}
			
			mensagemService.salvarLista(listaTwitters);

		} catch (TwitterException te) {
			log.info(te.getMessage());
			log.info(te);
			Response.status(Response.Status.BAD_REQUEST).entity(te.getMessage()).build(); 
		} catch (Exception e) {
			log.info(e);
			Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build(); 
		}
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}

	@POST
	@Path("/procurar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response procurar(String json) {
		ConsultaRest consulta = jsonToObject(json);
		
		List<ResultadoProcurar> retorno = mensagemService.consultarPorConteudo(consulta.getBusca());
		
		Gson gson = new Gson();
		return Response.ok(gson.toJson(retorno), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/{idTweet}/estatisticas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response printMessage(@PathParam("idTweet") String valor) {
		
		List<RetornoEstatistica> retorno = mensagemService.consultarPorTwitterId(new Long(valor));
		
		Gson gson = new Gson();
		return Response.ok(gson.toJson(retorno), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/{idTweet}/mais_comum")
	@Produces(MediaType.APPLICATION_JSON)
	public Response maisComum(@PathParam("idTweet") String json) {
		
		// TODO Nao deu tempo
		return Response.ok("TO DO", MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/mais_longa")
	@Produces(MediaType.TEXT_HTML)
	public Response maisLonga() {
		
		String valor = mensagemService.consultaPalavraMaisLonga();
		
		return Response.ok(valor, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("/exatamente_em/{numeroRepeticoes}")
	@Produces(MediaType.TEXT_HTML)
	public Response exatamanteEm(@PathParam("numeroRepeticoes") String json) {
		
		// TODO Nao deu tempo
		return Response.ok("TO DO", MediaType.APPLICATION_JSON).build();
	}
	
	private ConsultaRest jsonToObject(String json) {
		Gson gson = new Gson();
		ConsultaRest consulta = gson.fromJson(json, ConsultaRest.class);
		return consulta;
	}

}

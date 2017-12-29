package br.com.prova.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;

import br.com.prova.exception.ApplicationException;
import br.com.prova.models.Mensagem;
import br.com.prova.models.Palavra;

public class MensagemDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void salvar(Mensagem msg) throws ApplicationException {
		try {
			em.persist(msg);
		} catch (Exception ae) {
			throw new ApplicationException(ae.getMessage());
		}
	}

	@Transactional
	public void salvarLista(List<Mensagem> listaTwitters) {
		for (Mensagem mensagem : listaTwitters) {
			em.persist(mensagem);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mensagem> consultarPorConteudo(String valor) {
		StringBuffer sb = new StringBuffer("select m from Mensagem m ");
		sb.append(" where 1=1 ");

		if (StringUtils.isNotBlank(valor)) {
			sb.append(" and upper(m.conteudo) like upper(:valor)");
		}

		Query query = em.createQuery(sb.toString());

		if (StringUtils.isNotBlank(valor)) {
			query.setParameter("valor", "%" + valor + "%");
		}
		return query.getResultList();
	}

	public List<Mensagem> consultarPorTwitterId(Long id) {
		StringBuffer sb = new StringBuffer("select m from Mensagem m ");
		sb.append(" where 1=1 ");

		if (id != null) {
			sb.append(" and m.idTwitter like upper(:id)");
		}

		Query query = em.createQuery(sb.toString());

		if (id != null) {
			query.setParameter("id", id);
		}

		return query.getResultList();
	}

	public String consultaPalavraMaisLonga() {
		StringBuffer sb = new StringBuffer("select p from Palavra p order by length(valor) desc ");

		Query query = em.createQuery(sb.toString());

		List<Palavra> palavras = query.getResultList();

		if (palavras != null && !palavras.isEmpty()) {
			return palavras.get(0).getValor();
		}

		return "";
	}
}

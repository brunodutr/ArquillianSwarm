package br.com.bdutra;

import static org.apache.log4j.Logger.getLogger;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.apache.log4j.Logger;

import br.com.bdutra.entidade.AbstractEntity;
import br.com.bdutra.entidade.Pessoa;

public class BaseIT {

	private static final Logger LOG = getLogger(BaseIT.class);

	@PersistenceContext(unitName = "ArquillianPU")
	public EntityManager em;

	@Inject
	private UserTransaction utx;

	public void beforeTest() {
		
		assertNotNull(em);
		assertNotNull(utx);

		limparBaseDeDados(Pessoa.class);

	}

	private void limparBaseDeDados(Class<? extends AbstractEntity> entityClass) {
		
		LOG.info("Limpando a Base de Dados");
		
		try {
			
			utx.begin();
			Query query = em.createQuery(String.format("delete from %s", entityClass.getSimpleName()));
			query.executeUpdate();
			utx.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

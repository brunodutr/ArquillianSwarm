package br.com.bdutra;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.bdutra.entidade.Pessoa;

@Transactional
public class PessoaDAO {

	@PersistenceContext(unitName = "ArquillianPU")
	public EntityManager em;

	public void salvar(Pessoa pessoa) {
		em.persist(pessoa);
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findAll() {
		Query query = em.createQuery("select p from Pessoa p", Pessoa.class);
		return query.getResultList();
	}
	
	public void deletar(String nome, String sobrenome) {
		
		Query query = em.createQuery("delete from Pessoa p where p.nome = :nome and p.sobrenome = :sobrenome");
		query.setParameter("nome", nome);
		query.setParameter("sobrenome", sobrenome);
		
		query.executeUpdate();
	}
}

package br.com.bdutra.it.suite;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.bdutra.dao.impl.PessoaDAO;
import br.com.bdutra.entidade.Pessoa;
import br.com.bdutra.it.BaseIT;

@RunWith(Arquillian.class)
public class PessoaDAOIT extends BaseIT {

	@Inject
	private PessoaDAO pessoaDAO;

	@Test
	@InSequence(1)
	public void testInject() {

		super.beforeTest();

		Assert.assertNotNull(pessoaDAO);
	}

	@Test
	@InSequence(2)
	public void testDAO() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Bruno");
		pessoa.setSobrenome("Dutra");

		pessoaDAO.salvar(pessoa);

		List<Pessoa> lista = pessoaDAO.findAll();
		Assert.assertTrue(lista.size() == 1);
	}
}

package br.com.bdutra.it.suite;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.bdutra.dao.impl.PessoaDAO;
import br.com.bdutra.entidade.Pessoa;
import br.com.bdutra.it.BaseIT;
import br.com.bdutra.jms.ServicoProducer;
import br.com.bdutra.jmx.ServicoJMX;

@RunWith(Arquillian.class)
public class MessageDrivenBeanIT extends BaseIT {

	private static final Logger LOG = Logger.getLogger(MessageDrivenBeanIT.class);

	@Inject
	private PessoaDAO pessoaDAO;

	@Inject
	private ServicoProducer producer;

	@Test
	@InSequence(1)
	public void testInject() {

		super.beforeTest();

		assertNotNull(pessoaDAO);
		assertNotNull(producer);
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

	@Test
	@InSequence(3)
	public void enviarMensagensParaFila() throws Exception {

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Breno");
		pessoa.setSobrenome("Dutra");

		Pessoa pessoa2 = new Pessoa();
		pessoa2.setNome("Suelem");
		pessoa2.setSobrenome("Mayo");

		Pessoa pessoa3 = new Pessoa();
		pessoa3.setNome("Bruno");
		pessoa3.setSobrenome("Dutra");

		List<Pessoa> pessoas = Arrays.asList(pessoa, pessoa2, pessoa3, pessoa3, pessoa, pessoa2);

		for (Pessoa p : pessoas) {
			producer.enviar(p);
		}

		LOG.info("Mensagens enviadas");
	}

	@Test
	@RunAsClient
	@InSequence(4)
	public void esperaFilaSerConsumida() throws Exception {

		LOG.info("Esperando fila ser consumida");

		ServicoJMX.esperarFila("arquillianQueue");

	}

	@Test
	@InSequence(5)
	public void verificaMDB() {

		LOG.info("Verificando MDB");

		List<Pessoa> lista = pessoaDAO.findAll();

		LOG.info("Tamanho da Lista: " + lista.size());

	}

}

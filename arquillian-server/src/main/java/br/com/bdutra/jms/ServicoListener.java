package br.com.bdutra.jms;

import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import br.com.bdutra.dao.impl.PessoaDAO;
import br.com.bdutra.entidade.Pessoa;

public class ServicoListener implements MessageListener {

	private static final Logger LOG = Logger.getLogger(ServicoListener.class);

	@Inject
	private PessoaDAO pessoaDAO;

	public void onMessage(Message message) {
		try {

			ObjectMessage objeto = (ObjectMessage) message;

			Pessoa pessoa = (Pessoa) objeto.getObject();

			LOG.info("Redelivered : " + message.getJMSRedelivered());
			LOG.info(pessoa.toString());

		
			pessoaDAO.deletar(pessoa.getNome(), pessoa.getSobrenome());
		
			pessoaDAO.salvar(pessoa);

		} catch (Exception e) {
			throw new RuntimeException();
		}

	}

}

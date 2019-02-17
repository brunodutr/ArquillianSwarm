package br.com.bdutra.jms;

import static javax.transaction.Transactional.TxType.REQUIRES_NEW;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import br.com.bdutra.entidade.Pessoa;

public class ServicoProducer {
	
	private static final Logger LOG = Logger.getLogger(ServicoProducer.class);
	
	public static final String QUEUE = "java:/jms/queue/arquillianQueue";

	@Resource
	private ConnectionFactory connectionFactory;

	@Resource(lookup = QUEUE)
	private Queue queue;

	@Transactional(value = REQUIRES_NEW)
	public void enviar(Pessoa pessoa) throws InterruptedException {
		try (JMSContext context = connectionFactory.createContext()) {
			context.createProducer().send(queue, pessoa);
		}
	}

	
}

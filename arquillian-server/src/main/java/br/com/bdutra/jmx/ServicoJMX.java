package br.com.bdutra.jmx;

import static javax.management.MBeanServerInvocationHandler.newProxyInstance;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.artemis.api.core.management.QueueControl;

public class ServicoJMX {

	private static final String URL_JMX = "service:jmx:remote+http://localhost:9990";
	private static final String OBJECT_NAME = "org.apache.activemq.artemis:brokerName=\"default\",module=JMS,name=\"%s\",serviceType=Queue,type=Broker";

	public static void esperarFila(String nomeFila) throws MalformedObjectNameException, IOException {

		JMXServiceURL serviceURL = new JMXServiceURL(URL_JMX);
		JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceURL, null);
		MBeanServerConnection connection = jmxConnector.getMBeanServerConnection();

		String cannonicalName = String.format(OBJECT_NAME, nomeFila);
		ObjectName objectName = new ObjectName(cannonicalName);
		
		QueueControl queueControl = newProxyInstance(connection, objectName, QueueControl.class, false);
		
		while(queueControl.getMessageCount() > 0);

		jmxConnector.close();
	}
}

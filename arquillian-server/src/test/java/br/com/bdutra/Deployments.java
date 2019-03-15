package br.com.bdutra;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

@ArquillianSuiteDeployment
public class Deployments {

	@SuppressWarnings("rawtypes")
	@Deployment
	public static Archive createDeployment() {

		JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
				.addPackages(true, "br.com.bdutra")
				.addAsResource("project-it.yml", "project-defaults.yml")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsResource("META-INF/beans.xml")
				.addAsResource("META-INF/arquillian.xml")
				.addAsResource("META-INF/ejb-jar.xml");

		return jar;
	}
}

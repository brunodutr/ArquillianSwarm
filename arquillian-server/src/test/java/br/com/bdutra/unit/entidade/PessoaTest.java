package br.com.bdutra.unit.entidade;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.bdutra.entidade.Pessoa;

public class PessoaTest {

	@Test
	public void test() {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("Teste");
		
		assertEquals(pessoa.getNome(), "Teste");
	}

}

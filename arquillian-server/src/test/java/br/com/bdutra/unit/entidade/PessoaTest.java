package br.com.bdutra.unit.entidade;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.bdutra.entidade.Pessoa;

public class PessoaTest {

	private Pessoa pessoa = new Pessoa();

	@DisplayName("Deve atribuir um nome com sucesso")
	@ParameterizedTest
	@ValueSource(strings = { "Teste", "Teste 2" })
	public void test(String nome) {

		pessoa.setNome(nome);

		assertEquals(pessoa.getNome(), nome);
	}

}

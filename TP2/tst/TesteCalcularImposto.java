package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.IRPF;
@RunWith(Parameterized.class)
public class TesteCalcularImposto {

	private record TestParam(IRPF irpf, float if1, float if2, float if3, float if4, float if5, float imposto) {
	};

	private final TestParam param;

	public TesteCalcularImposto(TestParam param) {
		this.param = param;
	}

	@Parameters
	public static List<TestParam> getParams() {
		// Teste 1
		var irpf1 = new IRPF();

		irpf1.criarRendimento("Salário", true, 11000);
		irpf1.cadastrarContribuicaoPrevidenciaria(1000);

		// Teste 2
		var irpf2 = new IRPF();

		irpf2.criarRendimento("Salário", true, 1000);
		
		// Teste 3
		var irpf3 = new IRPF();

		irpf3.criarRendimento("Salário", true, 10000);
		irpf3.cadastrarContribuicaoPrevidenciaria(1000);
		irpf3.cadastrarDependente("Lorenzo", "Filho");
		irpf3.cadastrarDependente("maria", "Filha");
		irpf3.cadastrarContribuicaoPrevidenciaria(500);
		
		// Teste 4
		var irpf4 = new IRPF();

		irpf4.criarRendimento("Salário", true, 3000);
		irpf4.cadastrarContribuicaoPrevidenciaria(500);

		// Teste 5
		var irpf5 = new IRPF();
		irpf5.criarRendimento("Salário", true, 7000);
		irpf5.cadastrarContribuicaoPrevidenciaria(500);
		irpf5.cadastrarDeducaoIntegral("Carnê-leão", 1500);
		irpf5.cadastrarDeducaoIntegral("Pensão Alimentícia", 1500);



		// Junta os testes
		return List.of(
				new TestParam(irpf1, 0,42.55f,138.66f,205.56f,1467.21f, 1853.99f),
				new TestParam(irpf2, 0,0,0,0,0, 0),
				new TestParam(irpf3, 0,42.55f,138.66f,205.56f,950.43f, 1337.22f),
				new TestParam(irpf4, 0,13.20f,0,0,0, 13.20f),
				new TestParam(irpf5, 0,42.55f,101.00f,0,0, 143.55f));
	}

	@Test
	public void testCalculaImpostoPorFaixa1() {
		assertEquals(param.if1, param.irpf().getImpostoDevidoPorFaixa(0), 0.01);
	}
	
	@Test
	public void testCalculaImpostoPorFaixa2() {
		assertEquals(param.if2, param.irpf().getImpostoDevidoPorFaixa(1), 0.01);
	}
	
	@Test
	public void testCalculaImpostoPorFaixa3() {
		assertEquals(param.if3, param.irpf().getImpostoDevidoPorFaixa(2), 0.01);
	}

	@Test
	public void testCalculaImpostoPorFaixa4() {
		assertEquals(param.if4, param.irpf().getImpostoDevidoPorFaixa(3), 0.01);
	}

	@Test
	public void testCalculaImpostoPorFaixa5() {
		assertEquals(param.if5, param.irpf().getImpostoDevidoPorFaixa(4), 0.01);
	}
	
	@Test
	public void testCalculaImpostoTotalS() {
		assertEquals(param.imposto, param.irpf().getImpostoDevido(), 0.01);
	}

}

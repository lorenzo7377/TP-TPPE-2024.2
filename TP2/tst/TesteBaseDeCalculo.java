package tst;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.IRPF;

@RunWith(Parameterized.class)
public class TesteBaseDeCalculo {

	private record TestParam(IRPF irpf, float baseCalculoEsperado) {
	};

	private final TestParam param;

	public TesteBaseDeCalculo(TestParam param) {
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

		irpf2.criarRendimento("Salário", true, 10000);
		irpf2.cadastrarDependente("Lorenzo", "Filho");
		irpf2.cadastrarContribuicaoPrevidenciaria(500);
		irpf2.cadastrarDeducaoIntegral("Previdência Privada", 1000);
		irpf2.cadastrarDeducaoIntegral("Pensão Alimentícia", 1500);
		
		// Teste 3
		var irpf3 = new IRPF();

		irpf3.criarRendimento("Salário", true, 10000);
		irpf3.cadastrarContribuicaoPrevidenciaria(1000);
		irpf3.cadastrarDependente("Lorenzo", "Filho");
		irpf3.cadastrarDependente("maria", "Filha");
		irpf3.cadastrarContribuicaoPrevidenciaria(500);
		
		// Teste 4
		var irpf4 = new IRPF();

		irpf4.criarRendimento("Salário", true, 12000);
		irpf4.cadastrarContribuicaoPrevidenciaria(2500);
		irpf4.cadastrarDependente("Lorenzo", "Filho");
		irpf4.cadastrarDependente("maria", "Filha");
		irpf4.cadastrarDependente("eduardo", "Filho");
		irpf4.cadastrarDeducaoIntegral("Previdência Privada", 1000);
		irpf4.cadastrarContribuicaoPrevidenciaria(3000);
		
		// Teste 5
		var irpf5 = new IRPF();
		irpf5.criarRendimento("Salário", true, 1000);




		// Junta os testes
		return List.of(
				new TestParam(irpf1, 10000),
				new TestParam(irpf2, 6810.41f),
				new TestParam(irpf3, 8120.82f),
				new TestParam(irpf4, 4931.23f),
				new TestParam(irpf5, 435.20f));
	}

	@Test
	public void testaCalcularBase() {
		assertEquals(param.baseCalculoEsperado, param.irpf().getBaseDeCalculo(), 0.01);
	}
}

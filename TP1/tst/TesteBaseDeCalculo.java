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

		// Junta os testes
		return List.of(
				new TestParam(irpf1, 10000),
				new TestParam(irpf2, 6810.41f));
	}

	@Test
	public void test() {
		assertEquals(param.baseCalculoEsperado, param.irpf().getBaseDeCalculo(), 0.01);
	}
}

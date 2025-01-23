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

public class TesteCalcularAliquotaEfetiva {

	private record TestParam(IRPF irpf, float aliquota) {
	};

	private final TestParam param;

	public TesteCalcularAliquotaEfetiva(TestParam param) {
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
				new TestParam(irpf1,0.1685f),
				new TestParam(irpf2, 0),
				new TestParam(irpf3, 0.1337f),
				new TestParam(irpf4, 0.0043f),
				new TestParam(irpf5, 0.0205f));
	}
	@Test
	public void testaAliquotaEfetiva() {
		assertEquals(param.aliquota, param.irpf().getAliquotaEfetiva(), 0.01);
	}

}

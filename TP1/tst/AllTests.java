package tst;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		TesteCadastrarDependente.class,
		TesteRendimentos.class,
		TesteCalculosDeducoesDependentes.class,
		TesteCadastroContribuicaoPrevidenciaria.class,
		TesteCadastroPensaoAlimenticia.class,
		TesteCadastroOutrasDeducoes.class,
		TesteCalcularAliquotaEfetiva.class,
		TesteBaseDeCalculo.class,
		TesteCalcularTotalImposto.class,
})
public class AllTests {

}

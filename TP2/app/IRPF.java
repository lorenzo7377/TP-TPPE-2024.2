package app;

import java.util.ArrayList;
import java.util.List;

public class IRPF {

	public static final boolean TRIBUTAVEL = true;
	public static final boolean NAOTRIBUTAVEL = false;
	private List<Rendimento> rendimentos;
	private List<Deducao> deducoes;
	private float totalRendimentos;

	private Dependentes dependentes = new Dependentes();

	public IRPF() {
		rendimentos = new ArrayList<>();
		deducoes = new ArrayList<>();
	}

	/**
	 * Cadastra um rendimento na base do contribuinte, informando o nome do
	 * rendimento, seu valor e se ele é tributável ou não.
	 * 
	 * @param nome       nome do rendimento a ser cadastrado
	 * @param tributavel true caso seja tributável, false caso contrário
	 * @param valor      valor do rendimento a ser cadastrado
	 */
	public void criarRendimento(String nome, boolean tributavel, float valor) {
		rendimentos.add(new Rendimento(nome, tributavel, valor));
		totalRendimentos += valor;
	}

	/**
	 * Retorna o número de rendimentos já cadastrados para o contribuinte
	 * 
	 * @return numero de rendimentos
	 */
	public int getNumRendimentos() {
		return rendimentos.size();
	}

	/**
	 * Retorna o valor total de rendimentos cadastrados para o contribuinte
	 * 
	 * @return valor total dos rendimentos
	 */
	public float getTotalRendimentos() {
		return totalRendimentos;
	}

	/**
	 * Retorna o valor total de rendimentos tributáveis do contribuinte
	 * 
	 * @return valor total dos rendimentos tributáveis
	 */
	public float getTotalRendimentosTributaveis() {
		float totalRendimentosTributaveis = 0;
		for (Rendimento rendimento : rendimentos) {
			if (rendimento.isTributavel()) {
				totalRendimentosTributaveis += rendimento.getValor();
			}
		}
		return totalRendimentosTributaveis;
	}

	/**
	 * Método para realizar o cadastro de um dependente, informando seu grau
	 * de parentesco
	 * 
	 * @param nome       Nome do dependente
	 * @param parentesco Grau de parentesco
	 */
	public void cadastrarDependente(String nome, String parentesco) {
		dependentes.cadastrarDependente(nome, parentesco);
	}

	/**
	 * Método que retorna o numero de dependentes do contribuinte
	 * 
	 * @return numero de dependentes
	 */
	public int getNumDependentes() {
		return dependentes.getNumDependentes();
	}

	/**
	 * Return o valor do total de deduções para o contribuinte
	 * 
	 * @return valor total de deducoes
	 */
	public float getDeducao() {
		float total = dependentes.getNumDependentes() * 189.59f;
		for (Deducao deducao : deducoes) {
			if (deducao.getTipo() == Deducao.Tipo.CONTRIBUICAO_PREVIDENCIARIA) {
				total += deducao.getValor();
			}
		}
		return total;
	}

	/**
	 * Cadastra um valor de contribuição previdenciária oficial
	 * 
	 * @param contribuicao valor da contribuição previdenciária oficial
	 */
	public void cadastrarContribuicaoPrevidenciaria(float contribuicao) {
		deducoes.add(
				new Deducao("Contribuição Previdenciária", contribuicao, Deducao.Tipo.CONTRIBUICAO_PREVIDENCIARIA));
	}

	/**
	 * Realiza busca do dependente no cadastro do contribuinte
	 * 
	 * @param nome nome do dependente que está sendo pesquisado
	 * @return nome do dependente ou null, caso nao conste na lista de dependentes
	 */
	public String getDependente(String nome) {
		return dependentes.getDependente(nome);
	}

	/**
	 * Método que retorna o grau de parentesco para um dado dependente, caso ele
	 * conste na lista de dependentes
	 * 
	 * @param dependente nome do dependente
	 * @return grau de parentesco, nulo caso nao exista o dependente
	 */
	public String getParentesco(String dependente) {
		return dependentes.getParentesco(dependente);
	}

	/**
	 * Realiza o cadastro de uma pensao alimenticia para um dos dependentes do
	 * contribuinte, caso ele seja um filho ou alimentando.
	 * 
	 * @param dependente nome do dependente
	 * @param valor      valor da pensao alimenticia
	 */
	public void cadastrarPensaoAlimenticia(String dependente, float valor) {
		String parentesco = getParentesco(dependente);
		if (parentesco != null
				&& (parentesco.toLowerCase().contains("filh") || parentesco.toLowerCase().contains("alimentand"))) {
			deducoes.add(new Deducao("Pensão Alimentícia", valor, Deducao.Tipo.PENSAO_ALIMENTICIA));
		}
	}

	/**
	 * Retorna o valor total pago em pensões alimentícias pelo contribuinte.
	 * 
	 * @return valor total de pensoes alimenticias
	 */
	public float getTotalPensaoAlimenticia() {
		float total = 0;
		for (Deducao deducao : deducoes) {
			if (deducao.getTipo() == Deducao.Tipo.PENSAO_ALIMENTICIA) {
				total += deducao.getValor();
			}
		}
		return total;
	}

	/**
	 * Metodo para cadastrar deduções integrais para o contribuinte. Para cada
	 * dedução é informado seu nome e valor.
	 * 
	 * @param nome         nome da deducao
	 * @param valorDeducao valor da deducao
	 */
	public void cadastrarDeducaoIntegral(String nome, float valorDeducao) {
		deducoes.add(new Deducao(nome, valorDeducao, Deducao.Tipo.OUTRA));
	}

	/**
	 * Método para pesquisar uma deducao pelo seu nome.
	 * 
	 * @param substring do nome da deducao a ser pesquisada
	 * @return nome da deducao, ou null caso na esteja cadastrada
	 */
	public String getOutrasDeducoes(String nome) {
		for (Deducao deducao : deducoes) {
			if (deducao.getNome().toLowerCase().contains(nome.toLowerCase())
					&& deducao.getTipo() == Deducao.Tipo.OUTRA) {
				return deducao.getNome();
			}
		}
		return null;
	}

	/**
	 * Obtem o valor da deducao à partir de seu nome
	 * 
	 * @param nome nome da deducao para a qual se busca seu valor
	 * @return valor da deducao
	 */
	public float getDeducao(String nome) {
		for (Deducao deducao : deducoes) {
			if (deducao.getNome().toLowerCase().contains(nome.toLowerCase())) {
				return deducao.getValor();
			}
		}
		return 0;
	}

	/**
	 * Obtem o valor total de todas as deduções que nao sao do tipo
	 * contribuicoes previdenciarias ou por dependentes
	 * 
	 * @return valor total das outras deducoes
	 */
	public float getTotalOutrasDeducoes() {
		float soma = 0;
		for (Deducao deducao : deducoes) {
			if (deducao.getTipo() == Deducao.Tipo.OUTRA) {
				soma += deducao.getValor();
			}
		}
		return soma;
	}

	public float getBaseDeCalculo() {
		float deducoes = getDeducao() + getTotalPensaoAlimenticia() + getTotalOutrasDeducoes();
		if (deducoes < 564.80f) {
			deducoes = 564.80f;
		}
		float base = getTotalRendimentosTributaveis() - deducoes;
		return Math.max(base, 0);
	}

	public float getImpostoDevidoPorFaixa(int faixa) {
		return new ImpostoDevidoPorFaixaObjectMethod(this, faixa).computar();
	}

	public float getImpostoDevido() {
		float total = 0f;
		for (int faixa = 1; faixa < 5; faixa++) {
			total += getImpostoDevidoPorFaixa(faixa);
		}
		return total;
	}

	public float getAliquotaEfetiva() {
		return getImpostoDevido() / getTotalRendimentosTributaveis();
	}
}

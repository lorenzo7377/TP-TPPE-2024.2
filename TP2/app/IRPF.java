package app;

public class IRPF {

	public static final boolean TRIBUTAVEL = true;
	public static final boolean NAOTRIBUTAVEL = false;
	private String[] nomeRendimento;
	private boolean[] rendimentoTributavel;
	private float[] valorRendimento;
	private int numRendimentos;
	private float totalRendimentos;

	private Dependentes dependentes = new Dependentes();
	private int numContribuicaoPrevidenciaria;
	private float totalContribuicaoPrevidenciaria;

	private float totalPensaoAlimenticia;

	private String[] nomesDeducoes;
	private float[] valoresDeducoes;

	public IRPF() {
		nomeRendimento = new String[0];
		rendimentoTributavel = new boolean[0];
		valorRendimento = new float[0];
		

		numContribuicaoPrevidenciaria = 0;
		totalContribuicaoPrevidenciaria = 0f;

		totalPensaoAlimenticia = 0f;

		nomesDeducoes = new String[0];
		valoresDeducoes = new float[0];
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
		// Adicionar o nome do novo rendimento
		String[] temp = new String[nomeRendimento.length + 1];
		for (int i = 0; i < nomeRendimento.length; i++)
			temp[i] = nomeRendimento[i];
		temp[nomeRendimento.length] = nome;
		nomeRendimento = temp;

		// adicionar tributavel ou nao no vetor
		boolean[] temp2 = new boolean[rendimentoTributavel.length + 1];
		for (int i = 0; i < rendimentoTributavel.length; i++)
			temp2[i] = rendimentoTributavel[i];
		temp2[rendimentoTributavel.length] = tributavel;
		rendimentoTributavel = temp2;

		// adicionar valor rendimento ao vetor
		float[] temp3 = new float[valorRendimento.length + 1];
		for (int i = 0; i < valorRendimento.length; i++) {
			temp3[i] = valorRendimento[i];
		}
		temp3[valorRendimento.length] = valor;
		valorRendimento = temp3;

		this.numRendimentos += 1;
		this.totalRendimentos += valor;

	}

	/**
	 * Retorna o número de rendimentos já cadastrados para o contribuinte
	 * 
	 * @return numero de rendimentos
	 */
	public int getNumRendimentos() {
		return numRendimentos;
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
		for (int i = 0; i < rendimentoTributavel.length; i++) {
			if (rendimentoTributavel[i]) {
				totalRendimentosTributaveis += valorRendimento[i];
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
		float total = 0;
		for (String d : dependentes.getNomesDependentes()) {
			total += 189.59f;
		}
		total += totalContribuicaoPrevidenciaria;

		return total;
	}

	/**
	 * Cadastra um valor de contribuição previdenciária oficial
	 * 
	 * @param contribuicao valor da contribuição previdenciária oficial
	 */
	public void cadastrarContribuicaoPrevidenciaria(float contribuicao) {
		numContribuicaoPrevidenciaria++;
		totalContribuicaoPrevidenciaria += contribuicao;
	}

	/**
	 * Retorna o numero total de contribuições realizadas como contribuicao
	 * previdenciaria oficial
	 * 
	 * @return numero de contribuições realizadas
	 */
	public int getNumContribuicoesPrevidenciarias() {
		return numContribuicaoPrevidenciaria;
	}

	/**
	 * Retorna o valor total de contribuições oficiais realizadas
	 * 
	 * @return valor total de contribuições oficiais
	 */
	public float getTotalContribuicoesPrevidenciarias() {
		return totalContribuicaoPrevidenciaria;
	}

	/**
	 * Realiza busca do dependente no cadastro do contribuinte
	 * 
	 * @param nome nome do dependente que está sendo pesquisado
	 * @return nome do dependente ou null, caso nao conste na lista de dependentes
	 */
	public String getDependente(String nome) {
		
		return dependentes.getdependente(nome);
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
		if (parentesco.toLowerCase().contains("filh") ||
				parentesco.toLowerCase().contains("alimentand")) {
			totalPensaoAlimenticia += valor;
		}
	}

	/**
	 * Retorna o valor total pago em pensões alimentícias pelo contribuinte.
	 * 
	 * @return valor total de pensoes alimenticias
	 */
	public float getTotalPensaoAlimenticia() {
		return totalPensaoAlimenticia;
	}

	/**
	 * Metodo para cadastrar deduções integrais para o contribuinte. Para cada
	 * dedução é informado seu nome e valor.
	 * 
	 * @param nome         nome da deducao
	 * @param valorDeducao valor da deducao
	 */
	public void cadastrarDeducaoIntegral(String nome, float valorDeducao) {
		String temp[] = new String[nomesDeducoes.length + 1];
		for (int i = 0; i < nomesDeducoes.length; i++) {
			temp[i] = nomesDeducoes[i];
		}
		temp[nomesDeducoes.length] = nome;
		nomesDeducoes = temp;

		float temp2[] = new float[valoresDeducoes.length + 1];
		for (int i = 0; i < valoresDeducoes.length; i++) {
			temp2[i] = valoresDeducoes[i];
		}
		temp2[valoresDeducoes.length] = valorDeducao;
		valoresDeducoes = temp2;
	}

	/**
	 * Método para pesquisar uma deducao pelo seu nome.
	 * 
	 * @param substring do nome da deducao a ser pesquisada
	 * @return nome da deducao, ou null caso na esteja cadastrada
	 */
	public String getOutrasDeducoes(String nome) {
		for (String d : nomesDeducoes) {
			if (d.toLowerCase().contains(nome.toLowerCase()))
				return d;
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
		for (int i = 0; i < nomesDeducoes.length; i++) {
			if (nomesDeducoes[i].toLowerCase().contains(nome.toLowerCase()))
				return valoresDeducoes[i];
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
		for (float f : valoresDeducoes) {
			soma += f;
		}
		return soma;
	}

	public float getBaseDeCalculo() {
		var deducoes = getDeducao() + getTotalPensaoAlimenticia() + getTotalOutrasDeducoes();

		if (deducoes < 564.80f)
			deducoes = 564.80f;

		var base = getTotalRendimentosTributaveis() - deducoes;

		return (base < 0) ? 0 : base;
	}

	
	

	public float getImpostoDevidoPorFaixa(int faixa) {
		return new ImpostoDevidoPorFaixaObjectMethod(this, faixa).computar();
	}

	public float getImpostoDevido() {
		var total = 0f;

		for (int faixa = 1; faixa < 5; faixa++) {
			var IR = getImpostoDevidoPorFaixa(faixa);
			total += IR;
		}

		return total;
	}
	

	public float getAliquotaEfetiva() {
		return getImpostoDevido() / getTotalRendimentosTributaveis();
	}
}

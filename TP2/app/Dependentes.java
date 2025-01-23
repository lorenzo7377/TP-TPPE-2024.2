package app;

public class Dependentes {
	private String[] nomesDependentes;
	private String[] parentescosDependentes;
	private int numDependentes;

	public Dependentes() {
		nomesDependentes = new String[0];
		parentescosDependentes = new String[0];
		numDependentes = 0;
	}

	public String[] getNomesDependentes() {
		return nomesDependentes;
	}

	public void setNomesDependentes(String[] nomesDependentes) {
		this.nomesDependentes = nomesDependentes;
	}

	public String[] getParentescosDependentes() {
		return parentescosDependentes;
	}

	public void setParentescosDependentes(String[] parentescosDependentes) {
		this.parentescosDependentes = parentescosDependentes;
	}

	public int getNumDependentes() {
		return numDependentes;
	}

	public void setNumDependentes(int numDependentes) {
		this.numDependentes = numDependentes;
	}

	public String getParentesco(String dependente) {
		for (int i = 0; i < nomesDependentes.length; i++) {
			if (nomesDependentes[i].equalsIgnoreCase(dependente))
				return parentescosDependentes[i];
		}
		return null;
	}

	public Object cadastrarDependente(String nome, String parentesco) {
		// adicionar dependente
				String[] temp = new String[nomesDependentes.length + 1];
				for (int i = 0; i < nomesDependentes.length; i++) {
					temp[i] = nomesDependentes[i];
				}
				temp[nomesDependentes.length] = nome;
				nomesDependentes = temp;

				String[] temp2 = new String[parentescosDependentes.length + 1];
				for (int i = 0; i < parentescosDependentes.length; i++) {
					temp2[i] = parentescosDependentes[i];
				}
				temp2[parentescosDependentes.length] = parentesco;
				parentescosDependentes = temp2;

				numDependentes ++;
		return null;
	}

	public String getdependente(String nome) {
		for (String d : nomesDependentes) {
			if (d.contains(nome))
				return d;
		}
		return null;
	}
}
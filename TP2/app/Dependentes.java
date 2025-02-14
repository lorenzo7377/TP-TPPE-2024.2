package app;

import java.util.ArrayList;
import java.util.List;

public class Dependentes {
	private List<String> nomesDependentes;
	private List<String> parentescosDependentes;

	public Dependentes() {
		nomesDependentes = new ArrayList<>();
		parentescosDependentes = new ArrayList<>();
	}

	public List<String> getNomesDependentes() {
		return new ArrayList<>(nomesDependentes);
	}

	public List<String> getParentescosDependentes() {
		return new ArrayList<>(parentescosDependentes);
	}

	public int getNumDependentes() {
		return nomesDependentes.size();
	}

	public String getParentesco(String dependente) {
		int index = nomesDependentes.indexOf(dependente);
		return index != -1 ? parentescosDependentes.get(index) : null;
	}

	public void cadastrarDependente(String nome, String parentesco) {
		nomesDependentes.add(nome);
		parentescosDependentes.add(parentesco);
	}

	public String getDependente(String nome) {
		for (String d : nomesDependentes) {
			if (d.contains(nome)) {
				return d;
			}
		}
		return null;
	}
}
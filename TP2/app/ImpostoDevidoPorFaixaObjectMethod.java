package app;

public record ImpostoDevidoPorFaixaObjectMethod(IRPF irpf, int faixa) {

	private static final float[][] TABELA = {
			{ 4664.68f, 0.275f, 4 },
			{ 3751.06f, 0.225f, 3 },
			{ 2826.66f, 0.150f, 2 },
			{ 2259.21f, 0.075f, 1 }
	};

	private float[] getImpostoPorFaixa() {

		var B = irpf.getBaseDeCalculo();

		var IR = new float[] { 0, 0, 0, 0, 0 };

		for (var entry : TABELA) {
			var limite = entry[0];
			var aliquota = entry[1];
			var indice = (int) entry[2];

			if (B > limite) {
				var diff = B - limite;
				IR[indice] = diff * aliquota;
				B -= diff;
			}
		}

		return IR;
	}

	public float computar() {
		if (faixa < 0 || faixa > 4) {
			throw new IllegalArgumentException("Faixa de imposto inválida");
		}

		var IR = getImpostoPorFaixa();
		return IR[faixa];
	}
}

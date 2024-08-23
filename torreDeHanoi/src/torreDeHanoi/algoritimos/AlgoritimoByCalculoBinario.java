package torreDeHanoi.algoritimos;

import torreDeHanoi.abstrato.AlgoritimoDinamico;
import torreDeHanoi.abstrato.LogSaida;
import torreDeHanoi.model.Movimento;
import torreDeHanoi.model.PosicaoCalc;
import torreDeHanoi.util.TipoCalculo;
import torreDeHanoi.util.Util;

public class AlgoritimoByCalculoBinario extends AlgoritimoDinamico {
	
	public PosicaoCalc posicaoInicial;
	public PosicaoCalc posicaoFinal;

	public boolean inverterDirecao;
	
	public int qtdDisco = 0;
	public int testador = 0;
	public long proximaInvercao = 1;
	
	public AlgoritimoByCalculoBinario(String notacaoPosicaoInicial, String notacaoPosicaoFinal) {
		super(notacaoPosicaoInicial, notacaoPosicaoFinal);
		
		posicaoInicial = new PosicaoCalc(notacaoPosicaoInicial);
		posicaoFinal = new PosicaoCalc(notacaoPosicaoFinal);
		qtdDisco = posicaoInicial.qtdDiscos;
		testador = posicaoInicial.testador;
	}

	@Override
	public AlgoritimoByCalculoBinario calcular() {
		posicaoInicial.calcValuePosicao(posicaoFinal, TipoCalculo.INICIAL);
		posicaoFinal.calcValuePosicao(posicaoInicial, TipoCalculo.FINAL);

		proximaInvercao = proximaInvercao << qtdDisco-1;
		while((long) posicaoInicial.referencia > proximaInvercao) proximaInvercao += proximaInvercao/2;
		
		return this;
	}

	
	@Override
	public void imprimir(LogSaida log) {
		System.out.println("Operação: "+posicaoInicial.getNotacao()+" -> "+posicaoFinal.getNotacao());
		for(long m = posicaoInicial.referencia; m <= posicaoFinal.referencia; m++) log.imprimirMovimento(buildMovimento(m));
		System.out.println();
	}
	
	private Movimento buildMovimento(long m) {

		int disco = Util.calcDisco(m);
		
		boolean direcao = disco % 2 != testador;
		int indexOrientacao = qtdDisco - disco;
		
		if(posicaoInicial.orientacao[indexOrientacao] != null && posicaoInicial.orientacao[indexOrientacao]) {
			direcao = !direcao;
			for(int c = indexOrientacao; c < qtdDisco; c++) posicaoInicial.orientacao[c] = !posicaoInicial.orientacao[c];
		}
		
		if(m == proximaInvercao && indexOrientacao+1 < posicaoFinal.orientacao.length) {
			for(int c = indexOrientacao; c < qtdDisco; c++) posicaoInicial.orientacao[c] = posicaoFinal.orientacao[indexOrientacao+1];
			proximaInvercao += (long) (Math.pow(2.0, indexOrientacao+1));
		}
		
		return new Movimento(disco, direcao);
	}
	
}

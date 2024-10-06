package torreDeHanoi.algoritimos;

import java.util.Arrays;

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
		System.out.println("Operação: "+posicaoInicial.getNotacao()+" -> "+posicaoFinal.getNotacao()+"\n");
		System.out.println("posicaoInicial: "+Arrays.toString(posicaoInicial.orientacao));
		System.out.println("posicaoFinal: "+Arrays.toString(posicaoFinal.orientacao)+"\n");
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
			int i = indexOrientacao;
			Boolean b = null;
			int d = disco + 1;
			
			do {
				i++;
				d--;
				b = posicaoFinal.orientacao[i];
			} while(b == null && i+1 < posicaoFinal.orientacao.length);
			
			if(b != null) {
				for(int c = indexOrientacao; c < qtdDisco; c++) posicaoInicial.orientacao[c] = posicaoFinal.orientacao[i];
				proximaInvercao += (long) (Math.pow(2.0, d-2));
			}
		}
		
		return new Movimento(disco, direcao);
	}
	
}

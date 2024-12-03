package torredehanoi.algoritimos;

import java.util.Arrays;

import torredehanoi.abstrato.AlgoritimoDinamico;
import torredehanoi.abstrato.LogSaida;
import torredehanoi.model.Movimento;
import torredehanoi.model.PosicaoCalc;
import torredehanoi.util.TipoCalculo;
import torredehanoi.util.Util;


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
//		System.out.println("posicaoInicial O: "+Arrays.toString(posicaoInicial.orientacao));
//		System.out.println("posicaoInicial A: "+Arrays.toString(posicaoInicial.base)+"\n");
//		System.out.println("posicaoFinal O: "+Arrays.toString(posicaoFinal.orientacao));
//		System.out.println("posicaoFinal A: "+Arrays.toString(posicaoFinal.base)+"\n");
		for(long m = posicaoInicial.referencia; m <= posicaoFinal.referencia; m++) log.imprimirMovimento(buildMovimento(m));
		System.out.println();
	}

	@Override
	public String getResult() {
		StringBuffer stringBuffer = new StringBuffer();
		for(long m = posicaoInicial.referencia; m <= posicaoFinal.referencia; m++) stringBuffer.append(" "+buildMovimento(m).getNotacao());
		return stringBuffer.toString();
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

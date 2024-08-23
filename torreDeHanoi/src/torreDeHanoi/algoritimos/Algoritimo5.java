package torreDeHanoi.algoritimos;

import torreDeHanoi.model.Movimento;
import torreDeHanoi.model.PosicaoCalc;
import torreDeHanoi.util.LogMovimentos;
import torreDeHanoi.util.TipoCalculo;
import torreDeHanoi.util.Util;

public class Algoritimo5 {

	static boolean imprimirMovimentos = true;

	public static boolean inverterDirecao;
	
	public static int qtdDisco = 0;
	public static int testador = 0;
	public static int contMovimentos = 0;
	public static long proximaInvercao = 1;
	
	public static Boolean[] orintacaoInicio;
	public static Boolean[] valueInicio;
	public static Boolean[] orintacaoFim;
	public static Boolean[] valueFim;

	public static void main(String[] args) {
		
		String notacaoInicial = "1:2:3|0|0|";
		PosicaoCalc posiInicial = new PosicaoCalc(notacaoInicial);
		
		String notacaoFinal = "1|2|3|";
		PosicaoCalc posiFinal = new PosicaoCalc(notacaoFinal);
		
		Long inicio = System.currentTimeMillis();
		
		Boolean[][] analiseInicio = posiInicial.calcValuePosicao(posiFinal, TipoCalculo.INICIAL);
		orintacaoInicio = analiseInicio[0];
		valueInicio = analiseInicio[1];
		
		int init = 0;
		for (int i = 0; i < valueInicio.length; ++i) init = (init << 1) + (valueInicio[i] ? 1 : 0);
		init++;

		qtdDisco = posiInicial.qtdDiscos;
		testador = posiInicial.testador;
		
		Boolean[][] analiseFim = posiFinal.calcValuePosicao(posiInicial, TipoCalculo.FINAL);
		orintacaoFim = analiseFim[0];
		valueFim = analiseFim[1];
		
		int fim = 0;
		for (int i = 0; i < valueFim.length; ++i) fim = (fim << 1) + (valueFim[i] ? 1 : 0);

		proximaInvercao = proximaInvercao << qtdDisco-1;
		while((long) init > proximaInvercao) proximaInvercao += proximaInvercao/2;
		
		LogMovimentos logSaida = new LogMovimentos();
		
		System.out.println("Operação: "+notacaoInicial+" -> "+notacaoFinal);
		for(long m = init; m <= fim; m++) logSaida.imprimirMovimento(buildMovimento(m));

		System.out.printf("\n\nTempo Calculo : %,d ml",System.currentTimeMillis()-inicio);
	}
	
	private static Movimento buildMovimento(long m) {

		int disco = Util.calcDisco(m);
		
		boolean direcao = disco % 2 != testador;
		int indexOrientacao = qtdDisco - disco;
		
		if(orintacaoInicio[indexOrientacao] != null && orintacaoInicio[indexOrientacao]) {
			direcao = !direcao;
			for(int c = indexOrientacao; c < qtdDisco; c++) orintacaoInicio[c] = !orintacaoInicio[c];
		}
		
		if(m == proximaInvercao && indexOrientacao+1 < orintacaoFim.length) {
			for(int c = indexOrientacao; c < qtdDisco; c++) orintacaoInicio[c] = orintacaoFim[indexOrientacao+1];
			proximaInvercao += (long) (Math.pow(2.0, indexOrientacao+1));
		}
		
		return new Movimento(disco, direcao);
	}
	
}

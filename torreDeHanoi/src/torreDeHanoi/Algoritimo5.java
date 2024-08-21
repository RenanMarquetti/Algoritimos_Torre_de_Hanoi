package torreDeHanoi;

import torreDeHanoi.util.Posicao;

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
		Posicao posiInicial = new Posicao(notacaoInicial);
		
		String notacaoFinal = "1|2|3|";
		Posicao posiFinal = new Posicao(notacaoFinal);
		
		System.out.println("Operação: "+notacaoInicial+" -> "+notacaoFinal+"\n");
		
		Long inicio = System.currentTimeMillis();
		Boolean[][] analiseInicio = posiInicial.calcValuePosicaoInicioByPosicaoFinal(posiFinal);
		orintacaoInicio = analiseInicio[0];
		valueInicio = analiseInicio[1];
		int init = 0;
		for (int i = 0; i < valueInicio.length; ++i) {
			init = (init << 1) + (valueInicio[i] ? 1 : 0);
		}
		init++;

		qtdDisco = posiInicial.getQtdDiscos();
		inverterDirecao = qtdDisco % 2 == 0;
		
		if(inverterDirecao) testador = 1;
		
		Boolean[][] analiseFim = posiFinal.calcValuePosicaoFinalByPosicaoInicial(posiInicial);
		orintacaoFim = analiseFim[0];
		valueFim = analiseFim[1];
		
		int fim = 0;
		for (int i = 0; i < valueFim.length; ++i) {
			fim = (fim << 1) + (valueFim[i] ? 1 : 0);
		}

		proximaInvercao = proximaInvercao << qtdDisco-1;
		while((long) init > proximaInvercao) proximaInvercao += proximaInvercao/2;
		
		for(long m = init; m <= fim; m++) imprimirMovimento(m);

		System.out.printf("\n\nTempo Calculo : %,d ml",System.currentTimeMillis()-inicio);
	}

	private static void imprimirMovimento(long m) {

		long comparador = 1L;
		long result = m & comparador;

		int disco = 1;
		while(result == 0) {
			
			comparador = comparador<<1;

			result = m & comparador;
			
			disco++;

		}
		
		boolean direcao = disco % 2 == testador;
		int indexOrientacao = qtdDisco - disco;
		

		
		if(orintacaoInicio[indexOrientacao] != null && orintacaoInicio[indexOrientacao]) {
			direcao = !direcao;
			for(int c = indexOrientacao; c < qtdDisco; c++) orintacaoInicio[c] = !orintacaoInicio[c];
		}
		
		if(imprimirMovimentos && ++contMovimentos%10==1) System.out.printf("\nLinha %,5d: ",m/10 +1);

		if(imprimirMovimentos) System.out.printf("%.0f%s ",log(2.0, result) + 1.0, direcao ? 'D' : 'E');		

		if(m == proximaInvercao && indexOrientacao+1 < orintacaoFim.length) {
			for(int c = indexOrientacao; c < qtdDisco; c++) orintacaoInicio[c] = orintacaoFim[indexOrientacao+1];
			proximaInvercao += (long) (Math.pow(2.0, indexOrientacao+1));
		}

	}

	private static double log(double base, double valor) {
		return Math.log(valor) / Math.log(base);
	}
	
}

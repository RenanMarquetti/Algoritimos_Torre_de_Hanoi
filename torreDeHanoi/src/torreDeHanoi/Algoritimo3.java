package torreDeHanoi;

import java.util.Date;

import torreDeHanoi.util.LogMovimentos;
import torreDeHanoi.util.Movimento;
import torreDeHanoi.util.Util;

public class Algoritimo3 {
	
	static boolean imprimirMovimentos = true;

	public static boolean inverterDirecao;
	
	public static int testador = 0;

	public static void main(String[] args) {
		
		Date inicio = new Date();

		int qtdDiscos = 5;

		inverterDirecao = qtdDiscos % 2 == 0;
		
		if(qtdDiscos % 2 == 0) testador = 1;

		long qtdMovimentos = (long) (Math.pow(2.0, qtdDiscos) - 1.0);

		System.out.println("Qtd discos: "+qtdDiscos);
		
		LogMovimentos logSaida = new LogMovimentos();
		for(long m = 1L; m <= qtdMovimentos; m++) logSaida.imprimirMovimento(buildMovimento(m));
		
		Date fim = new Date();
		
		System.out.printf("\n\nDemorou : %,d ml",fim.getTime()-inicio.getTime());
	}

	private static Movimento buildMovimento(long m) {
		int disco = Util.calcDisco(m);
		return new Movimento(disco, disco % 2 == testador);
	}

}

package torreDeHanoi;

import java.util.Date;

public class Algoritimo3 {
	
	static boolean imprimirMovimentos = true;

	public static boolean inverterDirecao;
	
	public static int testador = 0;
	
	// discos: 20
	// imprimindo:  3.602 ml
	// sem imprimir: 25 ml
	// limite: finito 29 sem imprimir: 9.642 ml

	public static void main(String[] args) {
		
		Date inicio = new Date();

		int discos = 20;

		inverterDirecao = discos % 2 == 0;
		
		if(discos % 2 == 0) testador = 1;

		long qtdMovimentos = (long) (Math.pow(2.0, discos) - 1.0);

		for(long m = 1L; m <= qtdMovimentos; m++) imprimirMovimento(m);
		
		Date fim = new Date();
		
		System.out.printf("\n\nDemorou : %,d ml",fim.getTime()-inicio.getTime());
	}

	private static void imprimirMovimento(long m) {

		long comparador = 1L;
		
		int disco = 1;

		long result = m & comparador;

		while (result == 0) {
			
			comparador = comparador<<1;

			result = m & comparador;
			
			disco++;

		}	
		

		char direcao = disco % 2 == testador ? 'D' : 'E';
		
		if(imprimirMovimentos && m%10==1) System.out.printf("\nLinha %,5d: ",m/10 +1);

		if(imprimirMovimentos) System.out.printf("%.0f%s ",log(2.0, result) + 1.0, direcao);

	}

	public static double log(double base, double valor) {
		return Math.log(valor) / Math.log(base);
	}
}

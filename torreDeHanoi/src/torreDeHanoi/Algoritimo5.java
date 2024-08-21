package torreDeHanoi;

import torreDeHanoi.util.Posicao;

public class Algoritimo5 {
	
	static boolean imprimirMovimentos = true;

	public static boolean inverterDirecao;
	
	public static int testador = 0;
	public static int contMovimentos = 0;
	
	public static Boolean[] orintacao;
	public static Boolean[] value;
	
	// discos: 20
	// imprimindo:  3.602 ml
	// sem imprimir: 25 ml
	// limite: finito 29 sem imprimir: 9.642 ml

	public static void main(String[] args) {
		
		Long inicio = System.currentTimeMillis();
		String notacao = "3|1:2|0|";
		Posicao posi = new Posicao(notacao);
//		System.out.print("Orintaçao:  ");
//		for(Boolean b : posi.calcValuePosicao()[0]) System.out.print((b != null && b ? 1 : 0) + " ");
//		System.out.println();
		System.out.print("Valor Mov:  ");
		for(Boolean b : posi.calcValuePosicao()[1]) System.out.print((b ? 1 : 0) + " ");
		System.out.println();

		orintacao = posi.calcValuePosicao()[0];
		value = posi.calcValuePosicao()[1];
		int init = 0;
		int l = value.length;
		for (int i = 0; i < l; ++i) {
			init = (init << 1) + (value[i] ? 1 : 0);
		}
		init++;
		
		int discos = orintacao.length;

		inverterDirecao = discos % 2 == 0;
		
		if(inverterDirecao) testador = 1;

		System.out.println('\n'+posi.getNotacao()+'\n');
		
		long qtdMovimentos = (long) (Math.pow(2.0, discos) - 1.0);

		for(long m = init; m <= qtdMovimentos; m++) imprimirMovimento(m);

		System.out.printf("\n\nDemorou : %,d ml",System.currentTimeMillis()-inicio);
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
//		System.out.println(m+" "+disco);
		
		boolean direcao = disco % 2 == testador;
		
		if(orintacao[disco-1] != null && orintacao[disco-1]) {
			direcao = !direcao;
			for(int c = disco-1; c >= 0; c--) orintacao[c] = !orintacao[c];
		}
		
		if(imprimirMovimentos && ++contMovimentos%10==1) System.out.printf("\nLinha %,5d: ",m/10 +1);

		if(imprimirMovimentos) System.out.printf("%.0f%s ",log(2.0, result) + 1.0, direcao ? 'D' : 'E');

	}

	public static double log(double base, double valor) {
		return Math.log(valor) / Math.log(base);
	}
	
}

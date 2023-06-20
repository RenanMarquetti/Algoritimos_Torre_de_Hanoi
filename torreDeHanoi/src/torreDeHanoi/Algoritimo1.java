package torreDeHanoi;

import java.util.Date;

public class Algoritimo1 {
	
	public static void main(String[] args) {
		
		int qtdDiscos = 20;
		int torreIcial = 0;
		
		boolean imprimirMovimentos = true;
		
		Date inicio = new Date();
		
		// discos: 29
		// imprimindo: 4.326 ml
		// sem imprimir: 19 ml
		// limite: 29 discos, sem imprimir: 3.172 ml
		
		int qtdMovimentos = (int) (Math.pow(2, qtdDiscos) -1.0);
		
		byte[] movimentos = new byte[qtdMovimentos], posisoes = new byte[qtdMovimentos];
		
		for(int c = 1; c<=qtdDiscos; c++) {
			
			int disco = qtdDiscos-c+1;
			int salto = (int) Math.pow(2.0, c);
			
			for(int x = (int) Math.pow(2.0, c-1) -1; x < movimentos.length; x += salto) {
				
				int posAtual = ((x / salto)+1)+torreIcial;
				
				if(disco%2 == 0) posisoes[x] = (byte) (posAtual%3);
				else posisoes[x] = (byte) ((posAtual*2)%3);
				
				movimentos[x] = (byte) disco;			
			}

		}
		
		
		if(imprimirMovimentos) {
			
			char nomeTorres[] = {'A','B','C'};
			System.out.print("Linha     1: ");
			for(int y = 0; y<movimentos.length; ) {
				
				char torre = nomeTorres[posisoes[y]];
				
				System.out.printf("%2d%s ", qtdDiscos-movimentos[y]+1,torre);
				if(++y%10 == 0) System.out.printf("\nLinha %,5d: ",y/10 +1);
			}
		}
		
		Date fim = new Date();
		
		System.out.printf("\n\nDemorou : %,d ml",fim.getTime()-inicio.getTime());
	}
}

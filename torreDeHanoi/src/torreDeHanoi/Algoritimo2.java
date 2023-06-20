package torreDeHanoi;

import java.util.Arrays;
import java.util.Date;
import java.util.Stack;

public class Algoritimo2 {
	
	static Stack torres[] = {new Stack(),new Stack(),new Stack()};
	
	static int qtdDiscos = 20;
	static int torreIcial = 0;
	
	static boolean imprimirMovimentos = true;
	
	// discos: 20
	// imprimindo: 4.706 ml
	// sem imprimir: 157 ml
	// limite: finito 29 sem imprimir: 67.811 ml
	
	
	public static void main(String[] args) {
		
		Date inicio = new Date();
		
		for( int c = 1; c <= qtdDiscos; torres[torreIcial].push(c++));
		
		int ultimoMecher = torreIcial+1;
		
		char nomeTorres[] = {'A','B','C'};
		
		for(long i = 0; i < (long) Math.pow(2.0, qtdDiscos)-1; i++) {
			
			if(imprimirMovimentos && i%10==0) System.out.printf("\nLinha %,5d: ",i/10 +1);
			
			ultimoMecher = mecher(qualMecher(ultimoMecher));

			
			if(imprimirMovimentos) System.out.printf("%s ",nomeTorres[ultimoMecher]);
		}

		Date fim = new Date();
		
		System.out.printf("\n\nDemorou : %,d ml",fim.getTime()-inicio.getTime());
			
	}
	
	static int mecher(int torre) {
		
		int disco = (int) torres[torre].pop();
		
		if(imprimirMovimentos) System.out.printf("%2d",qtdDiscos-disco+1);
		
		torre = (torre+1+disco%2)%3;
		
		torres[torre].push(disco);
		
		return torre;
	}
	
	static int qualMecher(int ultimaTorre) {
		
		int torreDireita = (ultimaTorre+1)%3;
		int torreEsquerda = (ultimaTorre+2)%3;
		
		if(torres[torreDireita].isEmpty()) return torreEsquerda;
		if(torres[torreEsquerda].isEmpty()) return torreDireita;
		
		boolean direitaMaior = (int) torres[torreDireita].lastElement() > (int) torres[torreEsquerda].lastElement();

		return direitaMaior ? torreDireita : torreEsquerda;
	}
	
}

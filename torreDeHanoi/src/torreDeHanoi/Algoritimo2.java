package torreDeHanoi;

import java.util.Date;
import java.util.Stack;

import torreDeHanoi.util.LogMovimentos;
import torreDeHanoi.util.Movimento;

public class Algoritimo2 {
	
	static Stack torres[] = {new Stack(),new Stack(),new Stack()};
	
	static int qtdDiscos = 4;
	static int torreInicial = 0;
	static int testador = (qtdDiscos+torreInicial)%2;
	
	static boolean imprimirMovimentos = true;

	
	public static void main(String[] args) {
		
		Date inicio = new Date();
		
		for( int c = 1; c <= qtdDiscos; torres[torreInicial].push(c++));
		int ultimoMecher = torreInicial+1;
		
		int disco = 0;
		int qualMecher = 0;
		
		System.out.println("Qtd discos: "+qtdDiscos+" Torre Inicial: "+torreInicial);
		
		LogMovimentos logSaida = new LogMovimentos();
		for(long i = 0; i < (long) Math.pow(2.0, qtdDiscos)-1; i++) {

			qualMecher = qualMecher(ultimoMecher);
			disco = qtdDiscos - (int) torres[qualMecher].peek() +1;
			ultimoMecher = mecher(qualMecher);


			if(imprimirMovimentos) logSaida.imprimirMovimento(new Movimento(disco, disco%2 != testador));
		}

		Date fim = new Date();
		
		System.out.printf("\n\nDemorou : %,d ml",fim.getTime()-inicio.getTime());
			
	}
	
	static int mecher(int torre) {
		
		int disco = (int) torres[torre].pop();
		
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

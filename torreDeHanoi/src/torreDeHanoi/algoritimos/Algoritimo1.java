package torreDeHanoi.algoritimos;

import java.util.Date;

import torreDeHanoi.model.Movimento;
import torreDeHanoi.util.LogMovimentos;

public class Algoritimo1 {
	
	static int qtdDiscos = 4;
	static int torreInicial = 0;
	static int testador = (qtdDiscos+torreInicial)%2;
	
	public static void main(String[] args) {
		
		boolean imprimirMovimentos = true;
		
		Date inicio = new Date();
		
		int qtdMovimentos = (int) (Math.pow(2, qtdDiscos) -1.0);
		
		byte[] movimentos = new byte[qtdMovimentos], posisoes = new byte[qtdMovimentos];
		
		for(int c = 1; c<=qtdDiscos; c++) {
			
			int disco = qtdDiscos-c+1;
			int salto = (int) Math.pow(2.0, c);
			
			for(int x = (int) Math.pow(2.0, c-1) -1; x < movimentos.length; x += salto) {
				
				int posAtual = ((x / salto)+1)+torreInicial;
				
				if(disco%2 == 0) posisoes[x] = (byte) (posAtual%3);
				else posisoes[x] = (byte) ((posAtual*2)%3);
				
				movimentos[x] = (byte) disco;			
			}

		}
		
		
		if(imprimirMovimentos) {
			System.out.println("Qtd discos: "+qtdDiscos+" Torre Inicial: "+torreInicial);
			
			LogMovimentos logSaida = new LogMovimentos(); 
			for(int y = 0; y<movimentos.length; y++) {
				
				int disco = qtdDiscos-movimentos[y]+1;
				logSaida.imprimirMovimento(new Movimento(disco, disco%2 != testador));
			}
		}
		
		Date fim = new Date();
		
		System.out.printf("\n\nDemorou : %,d ml",fim.getTime()-inicio.getTime());
	}
}

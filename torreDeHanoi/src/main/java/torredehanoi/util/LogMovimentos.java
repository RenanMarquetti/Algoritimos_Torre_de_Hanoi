package torredehanoi.util;

import torredehanoi.abstrato.LogSaida;
import torredehanoi.model.Movimento;

public class LogMovimentos implements LogSaida {
	
	private long qtdMovimentosImprecos = 0;
	
	@Override
	public void imprimirMovimento(Movimento m) {
		if(++qtdMovimentosImprecos%10==1) System.out.printf("\nLinha %,5d: ", qtdMovimentosImprecos/10 +1);
		System.out.print(m.getNotacao()+" ");	
	}
	
}

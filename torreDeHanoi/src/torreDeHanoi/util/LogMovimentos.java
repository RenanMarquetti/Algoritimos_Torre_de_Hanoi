package torreDeHanoi.util;

import torreDeHanoi.model.Movimento;

public class LogMovimentos {
	
	private long qtdMovimentosImprecos = 0;
	
	
	public void imprimirMovimento(Movimento m) {
		if(++qtdMovimentosImprecos%10==1) System.out.printf("\nLinha %,5d: ", qtdMovimentosImprecos/10 +1);
		System.out.print(m.getNotacao()+" ");	
	}
	
}

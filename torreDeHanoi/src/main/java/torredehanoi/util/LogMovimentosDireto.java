package torredehanoi.util;

import torredehanoi.abstrato.LogSaida;
import torredehanoi.model.Movimento;

public class LogMovimentosDireto implements LogSaida {
	
	@Override
	public void imprimirMovimento(Movimento m) {
		System.out.print(m.getNotacao()+" ");
	}
	
}

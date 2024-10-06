package torreDeHanoi.util;

import torreDeHanoi.abstrato.LogSaida;
import torreDeHanoi.model.Movimento;

public class LogMovimentosDireto implements LogSaida {
	
	@Override
	public void imprimirMovimento(Movimento m) {
		System.out.print(m.getNotacao()+" ");
	}
	
}

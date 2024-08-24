package torreDeHanoi.algoritimos;

import torreDeHanoi.abstrato.AlgoritimoStatico;
import torreDeHanoi.abstrato.LogSaida;
import torreDeHanoi.model.Movimento;
import torreDeHanoi.util.Util;

public class AlgoritimoByLong extends AlgoritimoStatico {
	
	public AlgoritimoByLong(int qtdDiscos, int torreInicial, int torreFinal) {
		super(qtdDiscos, torreInicial, torreFinal);
	}
	
	@Override
	public void imprimir(LogSaida log) {
		System.out.println("Qtd discos: "+qtdDiscos+" Torre Inicial: "+torreInicial+" Torre Final: "+torreFinal);
		if(torreInicial == torreFinal) return;
		for(long m = 1L; m <= qtdMovimentos; m++) log.imprimirMovimento(buildMovimento(m));
	}
	
	private Movimento buildMovimento(long m) {
		int disco = Util.calcDisco(m);
		return new Movimento(disco, disco % 2 != testador);
	}

}

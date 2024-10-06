package torredehanoi.algoritimos;

import torredehanoi.abstrato.AlgoritimoStatico;
import torredehanoi.abstrato.LogSaida;
import torredehanoi.model.Movimento;
import torredehanoi.util.Util;

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

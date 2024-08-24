package torreDeHanoi.algoritimos;

import torreDeHanoi.abstrato.AlgoritimoStatico;
import torreDeHanoi.abstrato.LogSaida;
import torreDeHanoi.model.Movimento;

public class AlgoritimoByArray extends AlgoritimoStatico {
	
	private byte[] movimentos, posisoes;
	
	public AlgoritimoByArray(int qtdDiscos, int torreInicial, int torreFinal) {
		super(qtdDiscos, torreInicial, torreFinal);
		movimentos = new byte[qtdMovimentos];
		posisoes = new byte[qtdMovimentos];
		carregarArray();
	}
	
	private  void carregarArray() {
		for(int c = 1; c<=qtdDiscos; c++) {
			
			int disco = qtdDiscos-c+1;
			int salto = (int) Math.pow(2.0, c);
			
			for(int x = (int) Math.pow(2.0, c-1) -1; x < movimentos.length; x += salto) {
				posisoes[x] = (byte) (((((x / salto)+1)+torreInicial)*(disco%2 == 0 ? 1 : 2))%3);
				movimentos[x] = (byte) disco;			
			}
		}
	}

	@Override
	public void imprimir(LogSaida log) {
		System.out.println("Qtd discos: "+qtdDiscos+" Torre Inicial: "+torreInicial+" Torre Final: "+torreFinal);
		if(torreInicial == torreFinal) return;
		for(int y = 0; y<movimentos.length; y++) {
			int disco = qtdDiscos-movimentos[y]+1;
			log.imprimirMovimento(new Movimento(disco, disco%2 != testador));
		}
	}
	
}

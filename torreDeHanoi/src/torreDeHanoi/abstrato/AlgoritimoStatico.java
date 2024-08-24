package torreDeHanoi.abstrato;

public abstract class AlgoritimoStatico {
	
	public int qtdDiscos, torreInicial, torreFinal;
	protected int qtdMovimentos, testador;
	
	public AlgoritimoStatico(int qtdDiscos, int torreInicial, int torreFinal) {
		this.qtdDiscos = qtdDiscos;
		qtdMovimentos = (int) (Math.pow(2, qtdDiscos) -1.0);
		
		this.torreInicial = torreInicial;
		this.torreFinal = torreFinal;
		testador = (qtdDiscos+torreInicial+torreFinal + (torreFinal<torreInicial ? 1 : 0))%2;
	}

	public abstract void imprimir(LogSaida log);
}

package torreDeHanoi.abstrato;

public abstract class AlgoritimoDinamico {
	
	public String notacaoPosicaoInicial;
	public String notacaoPosicaoFinal;
	
	public AlgoritimoDinamico(String notacaoPosicaoInicial, String notacaoPosicaoFinal) {
		this.notacaoPosicaoInicial = notacaoPosicaoInicial;
		this.notacaoPosicaoFinal = notacaoPosicaoFinal;
	}

	public abstract AlgoritimoDinamico calcular();
	public abstract void imprimir(LogSaida log);
	
}

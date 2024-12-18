package torredehanoi.abstrato;

public abstract class AlgoritimoDinamico {
	
	public String notacaoPosicaoInicial, notacaoPosicaoFinal;
	
	public AlgoritimoDinamico(String notacaoPosicaoInicial, String notacaoPosicaoFinal) {
		this.notacaoPosicaoInicial = notacaoPosicaoInicial;
		this.notacaoPosicaoFinal = notacaoPosicaoFinal;
	}

	public abstract AlgoritimoDinamico calcular();
	public abstract void imprimir(LogSaida log);
	
	public abstract String getResult();
	
}

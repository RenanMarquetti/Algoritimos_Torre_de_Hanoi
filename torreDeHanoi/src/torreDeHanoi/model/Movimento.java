package torreDeHanoi.model;

public class Movimento {

	private Integer disco;
	private char direcao;
	
	public Movimento() {
		// TODO Auto-generated constructor stub
	}
	
	public Movimento(PosicaoGrafo pos, int origem, int destino) {
		this.disco = pos.getStacks().get(origem).peek();
		this.direcao = (origem+1)%3 == destino ? 'D' : 'E';
	}
	
	public Movimento(String notacao) {
		this.direcao = notacao.charAt(notacao.length()-1);
		this.disco = Integer.parseInt(notacao.substring(0, notacao.length()-1));
	}
	
	public Movimento(int disco, boolean direcao) {
		this.disco = disco;
		this.direcao = direcao ? 'D' : 'E';
	}
	
	public Movimento(double disco, boolean direcao) {
		this((int) disco, direcao);
	}

	public String getNotacao() {
		return String.format("%,2d%s", disco, direcao);
	}

	public Integer getDisco() {
		return disco;
	}

	public void setDisco(Integer disco) {
		this.disco = disco;
	}

	public char getDirecao() {
		return direcao;
	}

	public void setDirecao(char direcao) {
		this.direcao = direcao;
	}
	
}

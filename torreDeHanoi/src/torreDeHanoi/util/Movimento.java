package torreDeHanoi.util;

import java.util.List;
import java.util.Stack;

public class Movimento {
	
	private String notacao;
	private Integer disco;
	private char direcao;
	
	public Movimento() {
		// TODO Auto-generated constructor stub
	}
	
	public Movimento(Posicao pos, int origem, int destino) {
		this.disco = pos.getStacks().get(origem).peek();
		this.direcao = (origem+1)%3 == destino ? 'D' : 'E';
		this.notacao = disco.toString()+direcao;
	}
	
	public Movimento(String notacao) {
		this.notacao = notacao;
		this.direcao = notacao.charAt(notacao.length()-1);
		this.disco = Integer.parseInt(notacao.substring(0, notacao.length()-1));
	}
	
	public static boolean isValid(Posicao pos, int origem, int destino) {
		
		if(origem%3 == destino%3) return false;
			
		List<Stack<Integer>> stacks = pos.getStacks();
		Stack<Integer> stackOrigem = stacks.get(origem%3);
		Stack<Integer> stackDestino = stacks.get(destino%3);
		
		if(stackOrigem.isEmpty()) return false;
		if(stackDestino.isEmpty()) return true;
		
		return stackOrigem.peek() < stackDestino.peek();
	}

	public String getNotacao() {
		return notacao;
	}

	public void setNotacao(String notacao) {
		this.notacao = notacao;
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

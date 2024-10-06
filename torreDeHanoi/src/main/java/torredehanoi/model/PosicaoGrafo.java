package torredehanoi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PosicaoGrafo extends Posicao {
	
	private boolean visitada = false;
	private Map<Movimento, String> movimentosValidos = new HashMap<Movimento, String>();
	private Movimento movimentoAnterior;
	private PosicaoGrafo posicaoAnterior;

	public PosicaoGrafo(String notacao) {
		super(notacao);
	}

	@SuppressWarnings("unchecked")
	public PosicaoGrafo(PosicaoGrafo posicao, Movimento movimento) {
		List<Stack<Integer>> stacks = posicao.getStacks();
		
		int indexOrigem = 0;
		for(;indexOrigem < 3; indexOrigem++) if(!stacks.get(indexOrigem).isEmpty() && stacks.get(indexOrigem).peek() == movimento.getDisco()) break;
		
		int indexDestino = (indexOrigem + (movimento.getDirecao() == 'D' ? 1:2))%3;
		
		this.stacks = new ArrayList<Stack<Integer>>(stacks.stream().map(s -> (Stack<Integer>) s.clone()).toList() );
		this.stacks.get(indexDestino).add(this.stacks.get(indexOrigem).pop());
		
		this.notacao = this.stacks.stream().map(PosicaoGrafo::notacaoPilha).reduce((v1,v2) -> v1+v2).get();
	}
	
	static String notacaoPilha(Stack<Integer> stack) {
		if(stack.isEmpty()) return "0|";
		
		StringBuilder builder = new StringBuilder();
		stack.stream().sorted((v1,v2) -> v1.compareTo(v2)).forEach(v -> builder.append(v+":"));
		builder.setCharAt(builder.length()-1, '|');
		
		return builder.toString();
		
	}

	public boolean isVisitada() {
		return visitada;
	}

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}

	public Map<Movimento, String> getMovimentosValidos() {
		return movimentosValidos;
	}

	public void setMovimentosValidos(Map<Movimento, String> movimentosValidos) {
		this.movimentosValidos = movimentosValidos;
	}

	public Movimento getMovimentoAnterior() {
		return movimentoAnterior;
	}

	public void setMovimentoAnterior(Movimento movimentoAnterior) {
		this.movimentoAnterior = movimentoAnterior;
	}

	public PosicaoGrafo getPosicaoAnterior() {
		return posicaoAnterior;
	}

	public void setPosicaoAnterior(PosicaoGrafo posicaoAnterior) {
		this.posicaoAnterior = posicaoAnterior;
	}
	
}

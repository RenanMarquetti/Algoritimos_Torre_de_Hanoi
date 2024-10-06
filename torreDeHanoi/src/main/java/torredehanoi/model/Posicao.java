package torredehanoi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Posicao {
	
	protected String notacao;
	protected List<Stack<Integer>> stacks;
	
	public Posicao() {
		this.stacks = new ArrayList<Stack<Integer>>();
		stacks.add(new Stack<Integer>());
		stacks.add(new Stack<Integer>());
		stacks.add(new Stack<Integer>());
	}
	
	public Posicao(String notacao) {
		this();
		this.notacao = notacao;
		montarPosicaoByNotacao();
	}
	
	private void montarPosicaoByNotacao() {
		String[] values = notacao.split("\\|");
		for(int x = 0; x < values.length; x++) {
			 
			String[] discos = values[x].split(":");
			for(int y = discos.length-1; y >= 0; y--) if(!discos[y].equals("0")) {
				stacks.get(x).add(Integer.parseInt(discos[y]));
			}
		}
	}

	public String getNotacao() {
		return notacao;
	}

	public void setNotacao(String notacao) {
		this.notacao = notacao;
	}

	public List<Stack<Integer>> getStacks() {
		return stacks;
	}

	public void setStacks(List<Stack<Integer>> stacks) {
		this.stacks = stacks;
	}

}

package torreDeHanoi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Posicao {
	
	private boolean visitada = false;
	private String notacao;
	private List<Stack<Integer>> stacks;
	private Map<Movimento, String> movimentosValidos = new HashMap<Movimento, String>();
	private Movimento movimentoAnterior;
	private Posicao posicaoAnterior;
	private int qtdDiscos = 0;

	public Posicao() {
		this.stacks = new ArrayList<Stack<Integer>>();
		stacks.add(new Stack<Integer>());
		stacks.add(new Stack<Integer>());
		stacks.add(new Stack<Integer>());
	}
	
	public Posicao(String notacao) {
		this();
		this.notacao = notacao;
		String[] values = notacao.split("\\|");
		for(int x = 0; x < values.length; x++) {
			 
			String[] discos = values[x].split(":");
			for(int y = discos.length-1; y >= 0; y--) if(!discos[y].equals("0")) {
				stacks.get(x).add(Integer.parseInt(discos[y]));
			}
		}
		
		qtdDiscos = stacks.stream().map(s -> s.size()).reduce((v1 , v2) -> v1+v2).get();
		
	}
	
	@SuppressWarnings("unchecked")
	public Posicao(Posicao posicao, Movimento movimento) {
		this();
		
		List<Stack<Integer>> stacks = posicao.getStacks();
		
		int i = 0;
		for(;i < 3; i++) if(!stacks.get(i).isEmpty() && stacks.get(i).peek() == movimento.getDisco()) break;
		
		int indexOrigem = i;
		int indexDestino = (indexOrigem + (movimento.getDirecao() == 'D' ? 1:2))%3;
		
		this.stacks = new ArrayList<Stack<Integer>>();
		this.stacks.add((Stack<Integer>) stacks.get(0).clone());
		this.stacks.add((Stack<Integer>) stacks.get(1).clone());
		this.stacks.add((Stack<Integer>) stacks.get(2).clone());

		this.stacks.get(indexDestino).add(this.stacks.get(indexOrigem).pop());
		
		this.notacao = toNotacao(this.stacks);
	}

	public List<Stack<Integer>> getStacks() {
		return stacks;
	}

	public void setStacks(List<Stack<Integer>> stacks) {
		this.stacks = stacks;
	}

	public boolean isVisitada() {
		return visitada;
	}

	public void setVisitada(boolean visitada) {
		this.visitada = visitada;
	}

	public String getNotacao() {
		return notacao;
	}

	public void setNotacao(String notacao) {
		this.notacao = notacao;
	}
	
	static String toNotacao(List<Stack<Integer>> stacks) {
		String pilha1 = notacaoPilha(stacks.get(0));
		String pilha2 = notacaoPilha(stacks.get(1));
		String pilha3 = notacaoPilha(stacks.get(2));
		
		
		return pilha1+"|"+pilha2+"|"+pilha3+"|";
	}
	
	@SuppressWarnings("unchecked")
	static String notacaoPilha(Stack<Integer> stack) {
		StringBuilder builder = new StringBuilder();
		
		if(stack.isEmpty()) return "0";
		
		Stack<Integer> clone = (Stack<Integer>) stack.clone();
		
		while(!clone.isEmpty()) {
			builder.append(clone.pop());
			builder.append(':');
		}
		builder.deleteCharAt(builder.length()-1);
		
		return builder.toString();
	}
	
	public int[] getPosicaoDisco(int disco) {
		int posicao = 0;
		for( ; !stacks.get(posicao).contains(disco) ;posicao++) ;
		
		int altura = stacks.get(posicao).indexOf(disco);
		
		int[] res = {posicao, altura};
		return res;
	}


	public Boolean[][] calcValuePosicaoFinalByPosicaoInicial(Posicao posiInicial) {
		
		int testador = qtdDiscos % 2;
		int discoAnalisado = qtdDiscos;
		
		Boolean[][] values = new Boolean[2][qtdDiscos];
		Integer pilhaInicial = null;
		Integer pilhaFinal = null;
		Integer posicaoAposMovimentoNatural = null;

		int[] discoAnteriorFinal = null;
		
		for( ; discoAnalisado > 0; discoAnalisado--) {
			
			
			int[] discoAtualInicial = posiInicial.getPosicaoDisco(discoAnalisado);
			int[] discoAtualFinal = getPosicaoDisco(discoAnalisado);
			
			if(discoAnalisado == qtdDiscos || pilhaInicial == pilhaFinal) {
				
				values[1][discoAnalisado-1] = true;
				pilhaInicial = discoAtualInicial[0];
				pilhaFinal = discoAtualFinal[0];
				
			} else {
				values[1][discoAnalisado-1] = discoAtualFinal[0] != 3-pilhaInicial-pilhaFinal;
			}
			
			if(!values[1][discoAnalisado-1]) {
				values[0][discoAnalisado-1] = values[0][discoAnalisado];
			} else if(discoAnteriorFinal != null && discoAnteriorFinal[0] == discoAtualFinal[0] && discoAnteriorFinal[1] + 1 == discoAtualFinal[1]) {
				values[0][discoAnalisado-1] = values[0][discoAnalisado];
			} else {
				posicaoAposMovimentoNatural = (pilhaInicial + (discoAnalisado % 2 == testador ? 2 : 1)) % 3;
				values[0][discoAnalisado-1] = discoAtualInicial[0] != discoAtualFinal[0] && posicaoAposMovimentoNatural != discoAtualFinal[0];
			}

			discoAnteriorFinal = discoAtualFinal;
		}
		
		for (int i = 0; i < values[0].length / 2; i++) {
			Boolean temp = values[0][i];
			values[0][i] = values[0][values[0].length - 1 - i];
			values[0][values[0].length - 1 - i] = temp;
		}
		
		for (int i = 0; i < values[1].length / 2; i++) {
			Boolean temp = values[1][i];
			values[1][i] = values[1][values[1].length - 1 - i];
			values[1][values[1].length - 1 - i] = temp;
		}	
		
		return values;
	}
	public Boolean[][] calcValuePosicaoInicioByPosicaoFinal(Posicao posiFinal) {
		
		int testador = qtdDiscos % 2;
		int discoAnalisado = qtdDiscos;
		
		Boolean[][] values = new Boolean[2][qtdDiscos];
		Integer pilhaAtual = null;
		Integer pilhaFinal = null;
		Integer posicaoAposMovimentoNatural = null;
		
		for( ; discoAnalisado > 0; discoAnalisado--) {
			
			int numTorre = 0;
			for (Stack<Integer> s : stacks) if(s.contains(discoAnalisado)) break; else numTorre++;

			int[] discoPosicaoFinal = posiFinal.getPosicaoDisco(discoAnalisado);
			
			if(discoAnalisado == qtdDiscos) {
				
				pilhaFinal = discoPosicaoFinal[0];
				
			} else if(!values[1][discoAnalisado]) {
				
				pilhaFinal = 3-pilhaAtual-pilhaFinal;
				
			}
			
			pilhaAtual = numTorre;
			values[1][discoAnalisado-1] = pilhaAtual == pilhaFinal;
			
			posicaoAposMovimentoNatural = (pilhaAtual + (discoAnalisado % 2 == testador ? 2 : 1)) % 3;
			
			if(pilhaAtual != pilhaFinal) values[0][discoAnalisado-1] = posicaoAposMovimentoNatural != pilhaFinal;
			else values[0][discoAnalisado-1] = false;
			
		}
		
		for (int i = 0; i < values[0].length / 2; i++) {
			Boolean temp = values[0][i];
			values[0][i] = values[0][values[0].length - 1 - i];
			values[0][values[0].length - 1 - i] = temp;
		}
		
		for (int i = 0; i < values[1].length / 2; i++) {
			Boolean temp = values[1][i];
			values[1][i] = values[1][values[1].length - 1 - i];
			values[1][values[1].length - 1 - i] = temp;
		}
		
		return values;
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

	public Posicao getPosicaoAnterior() {
		return posicaoAnterior;
	}

	public void setPosicaoAnterior(Posicao posicaoAnterior) {
		this.posicaoAnterior = posicaoAnterior;
	}

	public int getQtdDiscos() {
		return qtdDiscos;
	}

	public void setQtdDiscos(int qtdDiscos) {
		this.qtdDiscos = qtdDiscos;
	}
	
}

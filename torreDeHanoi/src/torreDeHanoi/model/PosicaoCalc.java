package torreDeHanoi.model;

import java.util.Stack;

import torreDeHanoi.util.Util;

public class PosicaoCalc extends Posicao {
	
	private int qtdDiscos;
	private int testador;
	
	public PosicaoCalc(String notacao) {
		super(notacao);
		qtdDiscos = stacks.stream().map(s -> s.size()).reduce((v1 , v2) -> v1+v2).get();
		testador = qtdDiscos%2;
		
	}
	
	public int[] getPosicaoDisco(int disco) {
		int posicao = 0;
		for( ; !stacks.get(posicao).contains(disco) ;posicao++) ;
		
		int altura = stacks.get(posicao).indexOf(disco);
		
		int[] res = {posicao, altura};
		return res;
	}
	
	public Boolean[][] calcValuePosicaoFinalByPosicaoInicial(PosicaoCalc posiInicial) {
		
		Boolean[][] values = new Boolean[2][qtdDiscos];
		Integer pilhaInicial = null;
		Integer pilhaFinal = null;
		Integer posicaoAposMovimentoNatural = null;

		int[] discoAnteriorFinal = null;
		
		for(int discoAnalisado = qtdDiscos; discoAnalisado > 0; discoAnalisado--) {
			
			
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
		
		return Util.inverterArray(values);
	}
	
	public Boolean[][] calcValuePosicaoInicioByPosicaoFinal(PosicaoCalc posiFinal) {

		Boolean[][] values = new Boolean[2][qtdDiscos];
		Integer pilhaAtual = null;
		Integer pilhaFinal = null;
		Integer posicaoAposMovimentoNatural = null;
		
		for(int discoAnalisado = qtdDiscos; discoAnalisado > 0; discoAnalisado--) {
			
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
		
		return Util.inverterArray(values);
	}

	public int getQtdDiscos() {
		return qtdDiscos;
	}

	public void setQtdDiscos(int qtdDiscos) {
		this.qtdDiscos = qtdDiscos;
	}
	
}

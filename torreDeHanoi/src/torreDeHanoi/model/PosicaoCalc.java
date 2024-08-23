package torreDeHanoi.model;

import java.util.Stack;

import torreDeHanoi.util.TipoCalculo;
import torreDeHanoi.util.Util;

public class PosicaoCalc extends Posicao {
	
	public int qtdDiscos;
	public int testador;
	public int referencia;
	public TipoCalculo tipo;
	public Boolean[] orientacao;
	public Boolean[] base;
	
	public PosicaoCalc(String notacao) {
		super(notacao);
		qtdDiscos = stacks.stream().map(s -> s.size()).reduce((v1 , v2) -> v1+v2).get();
		testador = qtdDiscos%2;
	}
	
	public PosicaoCalc calcValuePosicao(PosicaoCalc posicao, TipoCalculo tipo) {
		this.tipo = tipo;
		Boolean[][] values = new Boolean[2][qtdDiscos];
		
		Integer pilhaInicial = null;
		Integer pilhaFinal = null;
		Integer posicaoAposMovimentoNatural = null;
		
		int[] discoAnteriorFinal = null;
		int[] discoAtualInicial = null;
		int[] discoAtualFinal = null;
		
		for(int discoAnalisado = qtdDiscos; discoAnalisado > 0; discoAnalisado--) {
			
			if(TipoCalculo.INICIAL == tipo) {
				
				int numTorre = 0;
				for (Stack<Integer> s : stacks) if(s.contains(discoAnalisado)) break; else numTorre++;
				
				discoAtualFinal = posicao.getPosicaoDisco(discoAnalisado);
				pilhaFinal = discoAnalisado == qtdDiscos ? discoAtualFinal[0] : !values[1][discoAnalisado] ? 3-pilhaInicial-pilhaFinal : pilhaFinal;
				pilhaInicial = numTorre;
				values[1][discoAnalisado-1] = pilhaInicial == pilhaFinal;

			} else {
				
				discoAtualInicial = posicao.getPosicaoDisco(discoAnalisado);
				discoAtualFinal = getPosicaoDisco(discoAnalisado);
				
				if(discoAnalisado == qtdDiscos || pilhaInicial == pilhaFinal) {
					values[1][discoAnalisado-1] = true;
					pilhaInicial = discoAtualInicial[0];
					pilhaFinal = discoAtualFinal[0];
				} else values[1][discoAnalisado-1] = discoAtualFinal[0] != 3-pilhaInicial-pilhaFinal;
				
			}
			
			posicaoAposMovimentoNatural = (pilhaInicial + (discoAnalisado % 2 == testador ? 2 : 1)) % 3;
			
			if(TipoCalculo.INICIAL == tipo) {
				values[0][discoAnalisado-1] = pilhaInicial != pilhaFinal && posicaoAposMovimentoNatural != pilhaFinal;
			} else {
				values[0][discoAnalisado-1] = !values[1][discoAnalisado-1] || (discoAnteriorFinal != null && discoAnteriorFinal[0] == discoAtualFinal[0] && discoAnteriorFinal[1] + 1 == discoAtualFinal[1]) ? values[0][discoAnalisado] : discoAtualInicial[0] != discoAtualFinal[0] && posicaoAposMovimentoNatural != discoAtualFinal[0];
				discoAnteriorFinal = discoAtualFinal;
			}
			
		}
		
		values = Util.inverterArray(values);
		orientacao = values[0];
		base = values[1];
		
		for (int i = 0; i < base.length; ++i) referencia = (referencia << 1) + (base[i] ? 1 : 0);
		if(TipoCalculo.INICIAL == tipo) referencia++;
		
		return this;
	}
	
	public int[] getPosicaoDisco(int disco) {
		int posicao = 0;
		for( ;!stacks.get(posicao).contains(disco); posicao++) ;
		
		int altura = stacks.get(posicao).indexOf(disco);

		return new int[] {posicao, altura};
	}
	
}

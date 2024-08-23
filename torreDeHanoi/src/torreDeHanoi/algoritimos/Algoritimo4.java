package torreDeHanoi.algoritimos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import torreDeHanoi.model.Movimento;
import torreDeHanoi.model.PosicaoGrafo;
import torreDeHanoi.util.LogMovimentos;
import torreDeHanoi.util.Util;

public class Algoritimo4 {
	
	static Map<String, PosicaoGrafo> grafo = new HashMap<String, PosicaoGrafo>();
	static List<PosicaoGrafo> posicoes = new ArrayList<PosicaoGrafo>();
	static Queue<PosicaoGrafo> filaBusca = new LinkedList<PosicaoGrafo>();
	static List<Movimento> resultado = new ArrayList<Movimento>();
	
	public static void main(String[] args) {
		
		PosicaoGrafo posiInicial = new PosicaoGrafo("1:2:3|0|0|");
		PosicaoGrafo posiFinal = new PosicaoGrafo("1|2|3|");
		
		grafo.put(posiInicial.getNotacao(), posiInicial);
		posicoes.add(posiInicial);
		
		int c = 0;
		for( ;c < posicoes.size(); ) gerarProximasPosicoes(posicoes.get(c++));
	
		buscaEmLargura(posiInicial.getNotacao());
		navegar(grafo.get(posiFinal.getNotacao()));
		
		System.out.println("Operação: "+posiInicial.getNotacao()+" -> "+posiFinal.getNotacao()+"\n");
		LogMovimentos logSaida = new LogMovimentos();
		for(c = resultado.size()-1; c>=0; c--) logSaida.imprimirMovimento(resultado.get(c));
		
		System.out.print("\n\nfim\n");
		
	}
	
	static void navegar(PosicaoGrafo posicao) {	
		if(posicao.getPosicaoAnterior() != null) {
			resultado.add(posicao.getMovimentoAnterior());
			navegar(posicao.getPosicaoAnterior());
		}
	}
	
	static void buscaEmLargura(String posInical) {

		grafo.values().forEach(pos -> {
			pos.setVisitada(false);
			pos.setMovimentoAnterior(null);
			pos.setPosicaoAnterior(null);
		});
		
		filaBusca.add(grafo.get(posInical));
		filaBusca.peek().setVisitada(true);
		
		while(!filaBusca.isEmpty()) {
			PosicaoGrafo posicaoEmBusca = filaBusca.remove();
			
			
			posicaoEmBusca.getMovimentosValidos().keySet().forEach(movValido -> {
				PosicaoGrafo pos = grafo.get(posicaoEmBusca.getMovimentosValidos().get(movValido));
				if(!pos.isVisitada()) {
					pos.setPosicaoAnterior(posicaoEmBusca);
					pos.setMovimentoAnterior(movValido);
					pos.setVisitada(true);
					filaBusca.add(pos);
				}
			});
		}
		
		
	}
	
	static void gerarProximasPosicoes(PosicaoGrafo posicao) {
		
		if(posicao.isVisitada()) return;
		
		for(int origem = 0; origem < 3; origem++) 
			for(int destino = 0; destino < 3; destino++) {
				if(Util.isValid(posicao, origem, destino)) {
					
					
					Movimento mov = new Movimento(posicao, origem, destino);
					PosicaoGrafo novaPosicao = new PosicaoGrafo(posicao, mov);
					posicao.getMovimentosValidos().put(mov, novaPosicao.getNotacao());
					
					if(!grafo.containsKey(novaPosicao.getNotacao())) {
						grafo.put(novaPosicao.getNotacao(), novaPosicao);
						posicoes.add(novaPosicao);
					}
				}
		}
		
		posicao.setVisitada(true);
		
	}
	
}

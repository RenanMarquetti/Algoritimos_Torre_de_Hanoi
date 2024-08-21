package torreDeHanoi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import torreDeHanoi.util.LogMovimentos;
import torreDeHanoi.util.Movimento;
import torreDeHanoi.util.Posicao;

public class Algoritimo4 {
	
	static Map<String, Posicao> grafo = new HashMap<String, Posicao>();
	static List<Posicao> posicoes = new ArrayList<Posicao>();
	static Queue<Posicao> filaBusca = new LinkedList<Posicao>();
	static List<Movimento> resultado = new ArrayList<Movimento>();
	
	public static void main(String[] args) {
		
		Posicao posiInicial = new Posicao("1:2:3|0|0|");
		Posicao posiFinal = new Posicao("1|2|3|");
		
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
	
	static void navegar(Posicao posicao) {	
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
			Posicao posicaoEmBusca = filaBusca.remove();
			
			
			posicaoEmBusca.getMovimentosValidos().keySet().forEach(movValido -> {
				Posicao pos = grafo.get(posicaoEmBusca.getMovimentosValidos().get(movValido));
				if(!pos.isVisitada()) {
					pos.setPosicaoAnterior(posicaoEmBusca);
					pos.setMovimentoAnterior(movValido);
					pos.setVisitada(true);
					filaBusca.add(pos);
				}
			});
		}
		
		
	}
	
	static void gerarProximasPosicoes(Posicao posicao) {
		
		if(posicao.isVisitada()) return;
		
		for(int origem = 0; origem < 3; origem++) 
			for(int destino = 0; destino < 3; destino++) {
				if(Movimento.isValid(posicao, origem, destino)) {
					
					
					Movimento mov = new Movimento(posicao, origem, destino);
					Posicao novaPosicao = new Posicao(posicao, mov);
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

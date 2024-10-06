package torredehanoi;

import torredehanoi.abstrato.AlgoritimoDinamico;
import torredehanoi.abstrato.AlgoritimoStatico;
import torredehanoi.algoritimos.AlgoritimoByArray;
import torredehanoi.algoritimos.AlgoritimoByCalculoBinario;
import torredehanoi.algoritimos.AlgoritimoByGrafo;
import torredehanoi.algoritimos.AlgoritimoByLong;
import torredehanoi.algoritimos.AlgoritimoByStack;
import torredehanoi.util.LogMovimentos;
import torredehanoi.util.LogMovimentosDireto;

public class Main {
	public static void main(String[] args) {
		
		Long init;
		
		int qtdDisco = 6;
		int torreInicial = 0;
		int torreFinal = 2;
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Statico By Array\n");
		AlgoritimoStatico algoritimoArray = new AlgoritimoByArray(qtdDisco, torreInicial, torreFinal);
		algoritimoArray.imprimir(new LogMovimentos());
		System.out.println("\nTempo: "+(System.currentTimeMillis() - init)+"ms");
		System.out.println('\n');
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Statico By Stack\n");
		AlgoritimoStatico algoritimoStack = new AlgoritimoByStack(qtdDisco, torreInicial, torreFinal);
		algoritimoStack.imprimir(new LogMovimentos());
		System.out.println("\nTempo: "+(System.currentTimeMillis() - init)+"ms");
		System.out.println('\n');
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Statico By Long\n");
		AlgoritimoStatico algoritimoLong = new AlgoritimoByLong(qtdDisco, torreInicial, torreFinal);
		algoritimoLong.imprimir(new LogMovimentos());
		System.out.println("\nTempo: "+(System.currentTimeMillis() - init)+"ms");
		System.out.println('\n');
		

		String inicio = "1:4|3|2:5|";
		String fim = "1:2:3:4:5|0|0|";
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Dinamico By Grafo\n");
		AlgoritimoDinamico algoritimoGrafo = new AlgoritimoByGrafo(inicio, fim).calcular();
		algoritimoGrafo.imprimir(new LogMovimentosDireto());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ms");
		System.out.println('\n'); 
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Dinamico By Calculo Binario\n");
		AlgoritimoDinamico algoritimoCalculoBinario = new AlgoritimoByCalculoBinario(inicio, fim).calcular();
		algoritimoCalculoBinario.imprimir(new LogMovimentosDireto());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ms");
		
	}
}

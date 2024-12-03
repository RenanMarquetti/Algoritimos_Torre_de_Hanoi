package torredehanoi;

import torredehanoi.abstrato.AlgoritimoDinamico;
import torredehanoi.algoritimos.AlgoritimoByCalculoBinario;
import torredehanoi.algoritimos.AlgoritimoByGrafo;
import torredehanoi.util.LogMovimentos;
import torredehanoi.util.LogVazio;

public class Main {
	public static void main(String[] args) {
		
		Long init;
		
		int qtdDisco = 6;
		int torreInicial = 0;
		int torreFinal = 2;
		
//		init = System.currentTimeMillis();
//		System.out.println("Algoritimo Statico By Array\n");
//		AlgoritimoStatico algoritimoArray = new AlgoritimoByArray(qtdDisco, torreInicial, torreFinal);
//		algoritimoArray.imprimir(new LogMovimentos());
//		System.out.println("\nTempo: "+(System.currentTimeMillis() - init)+"ms");
//		System.out.println('\n');
//		
//		init = System.currentTimeMillis();
//		System.out.println("Algoritimo Statico By Stack\n");
//		AlgoritimoStatico algoritimoStack = new AlgoritimoByStack(qtdDisco, torreInicial, torreFinal);
//		algoritimoStack.imprimir(new LogMovimentos());
//		System.out.println("\nTempo: "+(System.currentTimeMillis() - init)+"ms");
//		System.out.println('\n');
//		
//		init = System.currentTimeMillis();
//		System.out.println("Algoritimo Statico By Long\n");
//		AlgoritimoStatico algoritimoLong = new AlgoritimoByLong(qtdDisco, torreInicial, torreFinal);
//		algoritimoLong.imprimir(new LogMovimentos());
//		System.out.println("\nTempo: "+(System.currentTimeMillis() - init)+"ms");
//		System.out.println('\n');

		String discos = "1:2:3:4:5:6:7:8:9:10";
		String inicio = discos+"|0|0|";
		String fim = "0|0|"+discos+"|";
		
//		init = System.currentTimeMillis();
//		System.out.println("Algoritimo Dinamico By Grafo\n");
//		AlgoritimoDinamico algoritimoGrafo = new AlgoritimoByGrafo(inicio, fim).calcular();
//		algoritimoGrafo.imprimir(new LogMovimentos());
//		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ms");
//		System.out.println('\n'); 
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Dinamico By Calculo Binario\n");
		AlgoritimoDinamico algoritimoCalculoBinario = new AlgoritimoByCalculoBinario(inicio, fim).calcular();
		algoritimoCalculoBinario.imprimir(new LogVazio());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ms");
		
	}
}

package torreDeHanoi;

import torreDeHanoi.abstrato.AlgoritimoDinamico;
import torreDeHanoi.abstrato.AlgoritimoStatico;
import torreDeHanoi.algoritimos.AlgoritimoByArray;
import torreDeHanoi.algoritimos.AlgoritimoByCalculoBinario;
import torreDeHanoi.algoritimos.AlgoritimoByGrafo;
import torreDeHanoi.algoritimos.AlgoritimoByLong;
import torreDeHanoi.algoritimos.AlgoritimoByStack;
import torreDeHanoi.util.LogMovimentos;

public class Main {
	public static void main(String[] args) {
		
		Long init;
		
		int qtdDisco = 2;
		int torreInicial = 0;
		int torreFinal = 2;
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Statico By Grafo\n");
		AlgoritimoStatico algoritimoArray = new AlgoritimoByArray(qtdDisco, torreInicial, torreFinal);
		algoritimoArray.imprimir(new LogMovimentos());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ml");
		System.out.println('\n');
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Statico By Grafo\n");
		AlgoritimoStatico algoritimoStack = new AlgoritimoByStack(qtdDisco, torreInicial, torreFinal);
		algoritimoStack.imprimir(new LogMovimentos());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ml");
		System.out.println('\n');
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Statico By Grafo\n");
		AlgoritimoStatico algoritimoLong = new AlgoritimoByLong(qtdDisco, torreInicial, torreFinal);
		algoritimoLong.imprimir(new LogMovimentos());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ml");
		System.out.println('\n');
		
		String inicio = "1:2|0|0|";
		String fim = "0|0|1:2|";
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Dinamico By Grafo\n");
		AlgoritimoDinamico algoritimoGrafo = new AlgoritimoByGrafo(inicio, fim).calcular();
		algoritimoGrafo.imprimir(new LogMovimentos());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ml");
		System.out.println('\n');
		
		init = System.currentTimeMillis();
		System.out.println("Algoritimo Dinamico By Calculo Binario\n");
		AlgoritimoDinamico algoritimoCalculoBinario = new AlgoritimoByCalculoBinario(inicio, fim).calcular();
		algoritimoCalculoBinario.imprimir(new LogMovimentos());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ml");
		
	}
}

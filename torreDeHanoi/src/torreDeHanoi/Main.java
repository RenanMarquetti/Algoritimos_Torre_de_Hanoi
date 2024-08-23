package torreDeHanoi;

import torreDeHanoi.abstrato.AlgoritimoDinamico;
import torreDeHanoi.algoritimos.AlgoritimoByCalculoBinario;
import torreDeHanoi.algoritimos.AlgoritimoByGrafo;
import torreDeHanoi.util.LogMovimentos;

public class Main {
	public static void main(String[] args) {
		String inicio = "1:2:3:4:5|6:7:8:9|10|";
		String fim = "0|0|1:2:3:4:5:6:7:8:9:10|";
		
		Long init = System.currentTimeMillis();
		System.out.println("Algoritimo By Grafo\n");
		AlgoritimoDinamico algoritimoGrafo = new AlgoritimoByGrafo(inicio, fim).calcular();
		algoritimoGrafo.imprimir(new LogMovimentos());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ml");
		System.out.println('\n');
		init = System.currentTimeMillis();
		System.out.println("Algoritimo By Calculo Binario\n");
		AlgoritimoDinamico algoritimoCalculoBinario = new AlgoritimoByCalculoBinario(inicio, fim).calcular();
		algoritimoCalculoBinario.imprimir(new LogMovimentos());
		System.out.println("Tempo: "+(System.currentTimeMillis() - init)+"ml");
		
	}
}

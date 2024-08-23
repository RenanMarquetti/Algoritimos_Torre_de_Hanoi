package torreDeHanoi.util;

import java.util.List;
import java.util.Stack;

import torreDeHanoi.model.Posicao;

public class Util {
	
	public static Integer calcDisco(long m) {
		
		long comparador = 1L;
		int disco = 1;
		long result = m & comparador;

		while(result == 0) {
			comparador = comparador<<1;
			result = m & comparador;
			disco++;
		}
		
		return disco;
	}
	
	public static Boolean[][] inverterArray(Boolean[][] array) {
		for (int x = 0; x < array.length; x++) {
			for (int y = 0; y < array[x].length / 2; y++) {
				Boolean temp = array[x][y];
				array[x][y] = array[x][array[x].length - 1 - y];
				array[x][array[x].length - 1 - y] = temp;
			}
		}
		return array;
	}
	
	public static boolean isValid(Posicao pos, int origem, int destino) {
		
		if(origem%3 == destino%3) return false;
			
		List<Stack<Integer>> stacks = pos.getStacks();
		Stack<Integer> stackOrigem = stacks.get(origem%3);
		Stack<Integer> stackDestino = stacks.get(destino%3);
		
		if(stackOrigem.isEmpty()) return false;
		if(stackDestino.isEmpty()) return true;
		
		return stackOrigem.peek() < stackDestino.peek();
	}
}

package torreDeHanoi.util;

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
	
}

package torredehanoi.algoritimos;

import java.util.Stack;

import torredehanoi.abstrato.AlgoritimoStatico;
import torredehanoi.abstrato.LogSaida;
import torredehanoi.model.Movimento;

public class AlgoritimoByStack extends AlgoritimoStatico {
	
	@SuppressWarnings("rawtypes")
	private Stack[] torres = new Stack[] {new Stack<Integer>(), new Stack<Integer>(), new Stack<Integer>()};
	int ultimoMecher, disco, qualMecher;
	
	
	@SuppressWarnings("unchecked")
	public AlgoritimoByStack(int qtdDiscos, int torreInicial, int torreFinal) {
		super(qtdDiscos, torreInicial, torreFinal);
		for( int c = 1; c <= qtdDiscos; torres[torreInicial].push(c++));
		ultimoMecher = torreInicial+1;
	}
	

	@Override
	public void imprimir(LogSaida log) {
		System.out.println("Qtd discos: "+qtdDiscos+" Torre Inicial: "+torreInicial+" Torre Final: "+torreFinal);
		if(torreInicial == torreFinal) return;
		for(long i = 0; i < qtdMovimentos; i++) {

			qualMecher = qualMecher(ultimoMecher);
			disco = qtdDiscos - (int) torres[qualMecher].peek() +1;
			ultimoMecher = mecher(qualMecher);

			log.imprimirMovimento(new Movimento(disco, disco%2 != testador));
		}
	}
	
	@SuppressWarnings("unchecked")
	private int mecher(int torre) {
		
		int disco = (int) torres[torre].pop();
		
		torre = (torre+1+disco%2)%3;
		
		torres[torre].push(disco);
		
		return torre;
	}
	
	private int qualMecher(int ultimaTorre) {
		
		int torreDireita = (ultimaTorre+1)%3;
		int torreEsquerda = (ultimaTorre+2)%3;
		
		if(torres[torreDireita].isEmpty()) return torreEsquerda;
		if(torres[torreEsquerda].isEmpty()) return torreDireita;
		
		boolean direitaMaior = (int) torres[torreDireita].lastElement() > (int) torres[torreEsquerda].lastElement();

		return direitaMaior ? torreDireita : torreEsquerda;
	}
	
}

package br.com.sad_diagnostico;

import java.util.ArrayList;

import android.util.Log;

public class Metodos {
	
	String proximo_sintoma[]; 
	private String resultado[];
	String id;
	String sintoma;
	
	
public String[] proximo_sintom(ArrayList<String> proximo, ArrayList<String> selecionados,int cont){
		
		
		
		for (int n = 0; n < selecionados.size(); n++) {
			for (int r = 0; r < proximo.size(); r++) {
				if (proximo.get(r).equals(selecionados.get(n))) {
					proximo.remove(r);
				//	Log.e("" + l_proximo.get(r), "Proximo 2");
				}
			}
		}

		for (int w = 0; w < proximo.size(); w++) {
			proximo_sintoma = proximo.get(cont).split("-");
			id = proximo_sintoma[0];
			sintoma = proximo_sintoma[1];
			resultado[0] = id;
			resultado[1] = sintoma;
			setResultado(resultado); 
			Log.i(""+w+"-"+proximo.get(w), "ID_PROXIMOS");
		}
		
	//	resultado[1] = sintoma;
		
		Log.e("Metodo"+getResultado(), "MEtodo Resultado");
		
		
		return getResultado();
	}


public String[] getResultado() {
	return resultado;
}


public void setResultado(String[] resultado) {
	this.resultado = resultado;
}



}

package br.com.sad.regras;

public class Regras {
	
	public Regras(){
		
		
	}
	
	public String[] trataSintomas(){
		
		l_ID.add(String.valueOf(rd_escolhido.getId()));

		db.retorna(l_ID, soma,compara);
		valores = db.Pesquisa(l_ID, soma);
		l_passados = db.getL_proximo();

		// Log.i(""+r_sim.getId(), "Radio sim");

		num_doencas = valores[0];
		selecionados = valores[1].split("-");
		id_selec = selecionados[0];
		sint_selet = selecionados[1];
		l_selecionados.add(sint_selet);
		l_sint.add(valores[1]);

		doencas = valores[3].split("-");
		id_doenas = doencas[0];
		sint_doencas = doencas[1];

		for (int n = 0; n < l_sint.size(); n++) {
			for (int r = 0; r < l_proximo.size(); r++) {
				if (l_proximo.get(r).equals(l_sint.get(n))) {
					l_proximo.remove(r);
					// Log.e("" + l_proximo.get(r), "Proximo 2");
				}
			}
		}

		for (int w = 0; w < l_proximo.size(); w++) {
			proximo_sintoma = l_proximo.get(cont).split("-");
			id_proximo = proximo_sintoma[0];
			sint_proximo = proximo_sintoma[1];
		//	Log.i("" + w + "-" + l_proximo.get(w), "ID_PROXIMOS");
		}

		cont++;

		for(int s=sim ;s<sim;s--){
			mostra_sintoma = l_selecionados.get(s)+mostra_sintoma;
			
		}
		for (String item : l_selecionados) {
			mostra_sintoma = item + "\n" + mostra_sintoma;
		}

		
		
		
		
		
		
		return null;
		
		
	}

}

package br.com.sad.actyvity;

import java.util.ArrayList;
import br.com.sad.conexao.Conexao;
import br.com.sad.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EscolhaActivity extends Activity {

	RadioGroup rg;
	RadioButton r_sim;
	RadioButton r_nao;
	RadioButton rd_escolhido;

	TextView escolha1;
	TextView t_escolhas;
	TextView pergunta;
	TextView numsit;
	Button b_proximo;
	Button b_reiniciar;
	Button b_abandonar;
	int soma = 2;
	int cont = 0;
	String doencas[];

	private String escolhido;

	ArrayList<String> l_ID = new ArrayList<String>();
	ArrayList<String> l_selecionados = new ArrayList<String>();
	ArrayList<String> l_sint = new ArrayList<String>();
	ArrayList<String> l_proximo = new ArrayList<String>();
	ArrayList<String> l_doencas = new ArrayList<String>();
	ArrayList<String> l_passados = new ArrayList<String>();

	String valores[] = new String[7];

	final Conexao db = new Conexao(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_escolha);

		String[] selecionados;

		String[] proximo_sintoma = null;

		String num_doencas;
		String id_selec;
		String sint_selet;
		String id_proximo = null;
		String sint_proximo = null;

		b_proximo = (Button) findViewById(R.id.b_proximo);
	//	b_abandonar = (Button) findViewById(R.id.b_abandonar);
		pergunta = (TextView) findViewById(R.id.t_sintoma);
		numsit = (TextView) findViewById(R.id.t_numsit);

		rg = (RadioGroup) findViewById(R.id.rg_perguntas);
		r_nao = (RadioButton) findViewById(R.id.r_nao);
		r_sim = (RadioButton) findViewById(R.id.r_sim);
		escolha1 = (TextView) findViewById(R.id.t_escolha1);

		Intent escolhido = getIntent();
		valores = escolhido.getStringArrayExtra("escolha");
		l_proximo = escolhido.getStringArrayListExtra("proximos");


		num_doencas = valores[0];
		selecionados = valores[1].split("-");
		id_selec = selecionados[0];
		sint_selet = selecionados[1];
		l_selecionados.add(sint_selet);
		l_sint.add(valores[1]);

		for (int n = 0; n < l_sint.size(); n++) {
			for (int r = 0; r < l_proximo.size(); r++) {
				if (l_proximo.get(r).equals(l_sint.get(n))) {
					l_proximo.remove(r);
					// Log.e("" + l_proximo.get(r), "Proximo 2");
				}
			}
		}

		for (int w = 0; w < l_proximo.size(); w++) {
			proximo_sintoma = l_proximo.get(0).split("-");
			id_proximo = proximo_sintoma[0];
			sint_proximo = proximo_sintoma[1];
			// Log.i(""+l_proximo.get(0)+" "+l_proximo.get(1), "ID_PROXIMO");
		}

		cria_radio(rg, id_proximo);

		if (l_ID.isEmpty()) {
			l_ID.add(id_selec);
		}

		pergunta.setText(sint_proximo
				+ "?");
		escolha1.setText(sint_selet);
		if (Integer.parseInt(num_doencas) > 10) {
			numsit.setBackgroundColor(Color.RED);
			numsit.setTextColor(Color.WHITE);
		}
		 else if(Integer.parseInt(num_doencas) < 10 && Integer.parseInt(num_doencas) > 5 ){
			 numsit.setBackgroundColor(Color.rgb(255,165,0));
				numsit.setTextColor(Color.WHITE);
			
		}
		 else{
			 numsit.setBackgroundColor(Color.GREEN);
				numsit.setTextColor(Color.WHITE);
			 
		 }
		numsit.setText("Numero de Doenças com esse Sintoma:\n" + num_doencas);
		botao_proximo();

	}

	public void cria_radio(RadioGroup rg, String id) {
		r_sim.setId(Integer.parseInt(id));
		r_nao.setId(0);
	}

	public String botao_proximo() {
		db.abrirDataBase();

		b_proximo.setOnClickListener(new OnClickListener() {

			String[] selecionados;
			String[] proximo_sintoma;

			String num_doencas;
			String id_selec;
			String sint_selet;
			String id_proximo;
			String sint_proximo;
			String mostra_sintoma;
			String id_doenas;
			String sint_doencas;
			String compara;
			int sim =0;

			@Override
			public void onClick(View v) {

				int selectedId = rg.getCheckedRadioButtonId();

				rd_escolhido = (RadioButton) findViewById(selectedId);
				r_sim.getId();

				if (rd_escolhido.getId() != 0) {
					String compara = "=";
					sim++;


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

					cria_radio(rg, id_proximo);

					pergunta.setText(sint_proximo);
					escolha1.setText(mostra_sintoma);
					numsit.setText("Numero de Doenças com esse Sintoma:"
							+ num_doencas);

					setEscolhido(num_doencas + "-" + id_proximo + "-"
							+ proximo_sintoma);

					soma++;
					r_sim.setChecked(false);

					if (Integer.parseInt(num_doencas) <= 1 || num_doencas == null) {

						Intent resultado = new Intent(v.getContext(),
								ResultadoActivity.class);
						resultado.putExtra("resultado", sint_doencas);
						startActivity(resultado);

					}

				} else {
					cont++;
					String compara = "<>";

					
					l_ID.add(String.valueOf(r_sim.getId()));

					db.retorna(l_ID, soma,compara);
					valores = db.Pesquisa(l_ID, soma);
					l_passados = db.getL_proximo();

					Log.e(""+valores[1], "valores[1]");
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

					for (String item : l_selecionados) {
						mostra_sintoma = item + "\n" + mostra_sintoma;
					}

					cria_radio(rg, id_proximo);

					pergunta.setText(sint_proximo);
					escolha1.setText(mostra_sintoma);
					numsit.setText("Numero de Doenças com esse Sintoma:"
							+ num_doencas);

					setEscolhido(num_doencas + "-" + id_proximo + "-"
							+ proximo_sintoma);

					soma++;
					r_sim.setChecked(false);
				}
			}
		});
		return escolhido;
	}

	public String getEscolhido() {
		return escolhido;
	}

	public void setEscolhido(String escolhido) {
		this.escolhido = escolhido;
	}

	public String[] proximo_sintom(ArrayList<String> proximo,
			ArrayList<String> selecionados, int cont) {

		String proximo_sintoma[], resultado[] = null;
		String id = null;
		String sintoma = null;

		for (int n = 0; n < selecionados.size(); n++) {
			for (int r = 0; r < proximo.size(); r++) {
				if (proximo.get(r).equals(selecionados.get(n))) {
					proximo.remove(r);
					// Log.e("" + l_proximo.get(r), "Proximo 2");
				}
			}
		}

		for (int w = 0; w < proximo.size(); w++) {
			proximo_sintoma = proximo.get(cont).split("-");
			id = proximo_sintoma[0];
			sintoma = proximo_sintoma[1];
			Log.i("" + w + "-" + proximo.get(w), "ID_PROXIMOS");
		}

		resultado[0] = id;
		resultado[1] = sintoma;

		return resultado;
	}
}

package br.com.sad_diagnostico;



import java.util.ArrayList;
import java.util.List;

import br.com.sad._diagnostico_escolha.AlertDialogRadio;
import br.com.sad._diagnostico_escolha.AlertDialogRadio.AlertPositiveListener;
import br.com.sad_diagnostico.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PrincipalActivity extends Activity implements AlertPositiveListener  {
	int position = 0;

	private Button btnDisplay;
	Button button;
	int escolha;
	ArrayList l_ID = new ArrayList();
	ArrayList<String> l_selecionados = new ArrayList<String>(0);
	ArrayList<String> l_proximo = new ArrayList<String>();
	String valores[] = new String[7];
	int id;
	String sintoma;
	private String sintomas[];

	final Conexao db = new Conexao(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		try {
			db.CriarDataBase();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}

		db.abrirDataBase();
		db.inicializa();
		l_selecionados = db.frequentes();
		
		String sint[] = new String[l_selecionados.size()];
		sintomas = sint;
		
		int t=0;
		for(String item : l_selecionados){
			sintomas[t] = item;
			
			Log.e(""+sintomas[t], "LAÇO FOR");
			t++;
		}
		
		addListenerOnButton();
		
		OnClickListener listener = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				
				AlertDialogRadio alert = new AlertDialogRadio();
				
				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", sintomas);
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
        Button btn = (Button) findViewById(R.id.b_princ_freguente);
        btn.setOnClickListener(listener);
        
	}
	
	@Override
    public void onPositiveClick(int position) {
    	this.position = position;
    	
    	TextView tv = (TextView) findViewById(R.id.text_princ_escolhido);

			String converte[] = sintomas[this.position].split("-");
			id = Integer.parseInt(converte[0]);
			sintoma = converte[1];
			
    	tv.setText("Sintomas Escolhido :\n " + sintoma);    	
    }
		

	public int addListenerOnButton() {
		db.abrirDataBase();

		btnDisplay = (Button) findViewById(R.id.b_princ_inicia);
		btnDisplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String escolha;

				l_ID.add(id);
				db.retorna(l_ID, 1);
				valores = db.Pesquisa(l_ID, 1);

				l_proximo = db.getL_proximo();
				db.repetidos(l_proximo);

				escolha = id + "-" + sintoma;
				Intent escolhido = new Intent(v.getContext(),EscolhaActivity.class);
				escolhido.putExtra("proximos", l_proximo);
				escolhido.putExtra("escolha", valores);
				
				startActivity(escolhido);
			}
		});
		return escolha;
	}
	 
}

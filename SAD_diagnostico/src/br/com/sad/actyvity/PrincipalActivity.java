package br.com.sad.actyvity;



import java.util.ArrayList;
import br.com.sad.conexao.Conexao;
import br.com.sad.util.AlertDialogRadio;
import br.com.sad.util.AlertDialogRadio.AlertPositiveListener;
import br.com.sad.util.Partes;
import br.com.sad.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

public class PrincipalActivity extends Activity implements AlertPositiveListener  {
	int position = 0;

	private Button btnDisplay;
	int escolha;
	ArrayList l_ID = new ArrayList();
	ArrayList<String> l_selecionados = new ArrayList<String>(0);
	ArrayList<String> l_proximo = new ArrayList<String>();
	String valores[] = new String[7];
	int id;
	String sintoma;
	
	int botao;
	

	final Conexao db = new Conexao(this);
	final  Partes partes = new Partes();

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
		
		btnDisplay = (Button) findViewById(R.id.b_princ_inicia);
		
		btnDisplay.setEnabled(false);

		
		
		
		addListenerOnButton();
		
		OnClickListener cabeca = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 1;
				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getCabeca())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		OnClickListener pescoco = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 2;

				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getPescoco())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		OnClickListener peito = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 3;

				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getPeito())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		OnClickListener braco = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 4;

				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getBraco())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		OnClickListener abdomem = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 5;

				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getAbdomem())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		
		OnClickListener genital = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 6;

				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getGenital())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		OnClickListener perna = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 7;

				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getPerna())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		OnClickListener costa = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 8;

				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getCosta())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		OnClickListener sinais = new OnClickListener() {			
			@Override
			public void onClick(View v) {
				FragmentManager manager = getFragmentManager();
				AlertDialogRadio alert = new AlertDialogRadio();
				 botao = 9;

				Bundle b  = new Bundle();
				b.putInt("position", position);
				b.putStringArray("sintomas", partes.trata_sintomas(db.retorna_sintomas(partes.getSinais())));
				
				alert.setArguments(b);
				alert.show(manager, "alert_dialog_radio");			
			}
		};  
		
		
        ImageButton b_cabeca = (ImageButton) findViewById(R.id.cabeca_frente);
        b_cabeca.setOnClickListener(cabeca);
        
        ImageButton b_pescoco = (ImageButton) findViewById(R.id.pescoco_frente);
        b_pescoco.setOnClickListener(pescoco);
        
        ImageButton b_peito = (ImageButton) findViewById(R.id.peito_frente);
        b_peito.setOnClickListener(peito);
        
        ImageButton b_braco = (ImageButton) findViewById(R.id.braco_direito);
        b_braco.setOnClickListener(braco);
        
        ImageButton b_braco2 = (ImageButton) findViewById(R.id.braco_esquerdo);
        b_braco2.setOnClickListener(braco);
        
        ImageButton b_abdomem = (ImageButton) findViewById(R.id.abdomem);
        b_abdomem.setOnClickListener(abdomem);
        
        ImageButton b_genital = (ImageButton) findViewById(R.id.genital);
        b_genital.setOnClickListener(genital);
        
        ImageButton b_perna = (ImageButton) findViewById(R.id.perna_direita);
        b_perna.setOnClickListener(perna);
        
        ImageButton b_perna2 = (ImageButton) findViewById(R.id.perna_esquerda);
        b_perna2.setOnClickListener(perna);
	}
	
	
	@Override
    public void onPositiveClick(int position) {
    	this.position = position;
    	
    	TextView tv = (TextView) findViewById(R.id.text_princ_escolhido);
    	
    	String converte[] = null;
    	
    	switch (botao) {
		case 1:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getCabeca()))[this.position].split("-");
			break;
			
		case 2:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getPescoco()))[this.position].split("-");
			break;
			
		case 3:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getPeito()))[this.position].split("-");
			break;
			
		case 4:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getBraco()))[this.position].split("-");
			break;
			
		case 5:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getAbdomem()))[this.position].split("-");
			break;
			
		case 6:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getGenital()))[this.position].split("-");
			break;
			
		case 7:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getPerna()))[this.position].split("-");
			break;
			
		case 8:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getCosta()))[this.position].split("-");
			break;
			
		case 9:
			 converte = partes.trata_sintomas(db.retorna_sintomas(partes.getSinais()))[this.position].split("-");
			break;

		default:
			break;
		}

			
			id = Integer.parseInt(converte[0]);
			sintoma = converte[1];
			
    	tv.setText("Sintomas Escolhido :\n\n " + sintoma);    	
    	
    	if(tv.getText() != null){
    		btnDisplay.setEnabled(true);
    		
    	}
    }
		

	public int addListenerOnButton() {
		db.abrirDataBase();

		btnDisplay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String escolha;
				String compara = "=";


				l_ID.add(id);
				db.retorna(l_ID, 1,compara);
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

package br.com.sad.actyvity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.sad.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class InfActitivity extends Activity {
	
	Button ok;
	Button sair;
	EditText nome;
	EditText idade;
	RadioButton masculino;
	RadioButton feminino;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inf);
		
		nome = (EditText) findViewById(R.id.text_nome);
		idade = (EditText) findViewById(R.id.text_idade);
		ok = (Button) findViewById(R.id.b_ok);
		sair = (Button) findViewById(R.id.b_sair);
		masculino = (RadioButton) findViewById(R.id.rad_masculino);
		feminino = (RadioButton) findViewById(R.id.rad_feminino);	
		
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String t_nome;
				
		/*		
				InicialFragment main = new InicialFragment();
				t_nome =   nome.toString();
				Bundle data = new Bundle();
				
				Log.i("cxxcv"+t_nome, "cv");
				Log.e("jh"+t_nome, "valor");

				data.putString("nome", t_nome);
				main.setArguments(data);
				
				*/

				// TODO Auto-generated method stub
				
					Validator validar = new Validator();
					
					validar.validateNotNull(nome, "Digite seu nome");
					validar.validateNotNull(idade, "Digite seu idade");
					
					


					
					if(validar.validateNotNull(nome, "Digite seu nome") && validar.validateNotNull(idade, "Digite seu idade")){
						Intent escolhido = new Intent(v.getContext(),MainActivity.class);
						startActivity(escolhido);
					}
					
					
					
			}
		});
		
	}
	
	public static class Validator {

		public static boolean validateNotNull(View pView, String pMessage) {
		if (pView instanceof EditText) {
		EditText edText = (EditText) pView;
		Editable text = edText.getText();
		if (text != null) {
		String strText = text.toString();
		if (!TextUtils.isEmpty(strText)) {
		return true;
		}
		}
		// em qualquer outra condição é gerado um erro
		edText.setError(pMessage);
		edText.setFocusable(true);
		edText.requestFocus();
		return false;
		}
		return false;
		}

		public static boolean validateDateFormat(View pView, String pDateFormat,
		String pMessage) {
		if (pView instanceof EditText) {
		EditText edText = (EditText) pView;
		Editable text = edText.getText();
		if (text != null) {
		String strText = text.toString();
		if (!TextUtils.isEmpty(strText)) {
		SimpleDateFormat format = new SimpleDateFormat(pDateFormat);
		try {
		format.parse(strText);
		return true;
		} catch (ParseException pe) {

		}
		}
		}
		// em qualquer outra condição é gerado um erro
		edText.setError(pMessage);
		edText.setFocusable(true);
		edText.requestFocus();
		return false;
		}
		return false;
		}
		}

}



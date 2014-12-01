package br.com.sad.actyvity;

import br.com.sad.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends Activity {
	
	TextView t_resultado;
	Button b_reiniciar;
	String resultado;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultado);
		
		Log.e("Entrou", "Ultima");
		
		t_resultado =  (TextView) findViewById(R.id.t_resultado);
		b_reiniciar = (Button) findViewById(R.id.b_reiniciar);
		
		
		Intent ultimo = getIntent();
		resultado = ultimo.getStringExtra("resultado");
		
		t_resultado.setText(resultado);
		
		b_reiniciar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent inicio = new Intent(v.getContext(),PrincipalActivity.class);
				startActivity(inicio);
				
			}
		});
		
		

		
		
		
		
	}
}

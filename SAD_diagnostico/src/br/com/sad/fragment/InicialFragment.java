package br.com.sad.fragment;

import br.com.sad.actyvity.PrincipalActivity;
import br.com.sad.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class InicialFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		//String retorna  = getArguments().getString("nome");


		View v = inflater.inflate(R.layout.framgment_inicial, container, false);

		Button inicia = (Button) v.findViewById(R.id.b_framgment);
		TextView nome = (TextView) v.findViewById(R.id.nome);
		
	//	nome.setText("Olá:"+retorna);

		inicia.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),  PrincipalActivity.class);
				startActivity(intent);
			}
		});
		return v;
	}
}

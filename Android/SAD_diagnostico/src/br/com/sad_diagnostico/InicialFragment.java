package br.com.sad_diagnostico;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

public class InicialFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View v = inflater.inflate(R.layout.framgment_inicial, container, false);

		Button inicia = (Button) v.findViewById(R.id.b_framgment);

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

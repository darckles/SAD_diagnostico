package br.com.sad_diagnostico;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.ListActivity;
import android.app.ListFragment;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/*	
 public class Lista_Sintomas extends Fragment {

 String[] sintomas = getArguments().getStringArray("position");

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

 View v = inflater.inflate(R.layout.activity_sintomas, container, false);
 Creating an array adapter to store the list of countries 
 ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1,sintomas);

 Setting the list adapter for the ListFragment 
 setListAdapter(adapter);

 return v;
 }
 }

 */

public class Lista_Sintomas extends Fragment implements OnItemClickListener {

		
	String[] itens = getArguments().getStringArray("position");

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_sintomas, container, false);
		ListView list = (ListView) view.findViewById(R.id.ListaSintomas);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, itens);
		list.setAdapter(adapter);
		return view;
	}
}

/*
 * ArrayList<String> lista = new ArrayList<String>();
 * 
 * 
 * final Conexao db = new Conexao(this);
 * 
 * @Override public void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState); try { db.CriarDataBase(); } catch
 * (SQLiteException e) { e.printStackTrace(); }
 * 
 * db.abrirDataBase(); // db.lista_sintomas();
 * 
 * String[] sintomas = new String[db.lista_sintomas().size()];
 * 
 * // Log.e("Lista"+db.lista_sintomas(), "Listou");
 * 
 * Log.e("Enctou aqui", "aui");
 * 
 * int i = 0; for(String item : db.lista_sintomas()){ sintomas[i] = item;
 * 
 * i++; Log.e("lista"+item, "Lista"); }
 * 
 * 
 * setListAdapter(new ArrayAdapter<String>(this,
 * R.layout.activity_sintomas,sintomas));
 * 
 * ListView listView = getListView(); listView.setTextFilterEnabled(true);
 * 
 * listView.setOnItemClickListener(new OnItemClickListener() { public void
 * onItemClick(AdapterView<?> parent, View view, int position, long id) { //
 * When clicked, show a toast with the TextView text
 * Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
 * Toast.LENGTH_SHORT).show(); } }); }
 * 
 * 
 * }
 */
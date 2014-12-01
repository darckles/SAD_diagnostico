package br.com.sad.actyvity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.sad.conexao.Conexao;
import br.com.sad.fragment.CountryFragment;
import br.com.sad.fragment.InicialFragment;
import br.com.sad.fragment.Lista_Sintomas;
import br.com.sad.fragment.VersaoFragment;
import br.com.sad.R;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ZoomControls;

public class MainActivity extends ActionBarActivity {

	int mPosition = -1;
	String mTitle = "MENU";
	RelativeLayout rl;
	ZoomControls zoom;
	ImageView img;

	final Conexao db = new Conexao(this);

	String[] mCountries;

	// Array of integers points to images stored in /res/drawable-ldpi/
	int[] mFlags = new int[] { R.drawable.home, R.drawable.sintomas,
			R.drawable.ajuda, R.drawable.versao,

	};

	// Array of strings to initial counts
	String[] mCount = new String[] { "", "", "", "", };

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private LinearLayout mDrawer;
	private List<HashMap<String, String>> mList;
	private SimpleAdapter mAdapter;
	final private String menu = "Menu";
	final private String FLAG = "flag";
	final private String COUNT = "count";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		inicio();

		try {
			db.CriarDataBase();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}

		// Getting an array of country names
		mCountries = getResources().getStringArray(R.array.menus);

		mDrawerList = (ListView) findViewById(R.id.drawer_list);

		mDrawer = (LinearLayout) findViewById(R.id.drawer);

		mList = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < 4; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put(menu, mCountries[i]);
			hm.put(COUNT, mCount[i]);
			hm.put(FLAG, Integer.toString(mFlags[i]));
			mList.add(hm);
		}

		// Keys used in Hashmap
		String[] from = { FLAG, menu, COUNT };

		// Ids of views in listview_layout
		int[] to = { R.id.flag, R.id.country, R.id.count };

		// Instantiating an adapter to store each items
		// R.layout.drawer_layout defines the layout of each item
		mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_layout, from,
				to);

		// Getting reference to DrawerLayout
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Creating a ToggleButton for NavigationDrawer with drawer event
		// listener
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				highlightSelectedCountry();
				supportInvalidateOptionsMenu();
			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle("Selecione uma Opção");
				supportInvalidateOptionsMenu();
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				incrementHitCount(position);

				if (position < 5) { // Show fragment for countries : 0 to 4
				//	showFragment(position);
					// mostrar_Sintomas();
					telas(position);
				} else { // Show message box for countries : 5 to 9
					Toast.makeText(getApplicationContext(),
							mCountries[position], Toast.LENGTH_LONG).show();
				}

				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawer);
			}
		});
		

        
           

		// Enabling Up navigation
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		getSupportActionBar().setDisplayShowHomeEnabled(true);

		// Setting the adapter to the listView
		mDrawerList.setAdapter(mAdapter);
		
		

	}



	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();

	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void incrementHitCount(int position) {
		HashMap<String, String> item = mList.get(position);
		String count = item.get(COUNT);
		item.remove(COUNT);
		if (count.equals("")) {
			count = "  1  ";
		} else {
			int cnt = Integer.parseInt(count.trim());
			cnt++;
			count = "  " + cnt + "  ";
		}
		item.put(COUNT, count);
		mAdapter.notifyDataSetChanged();
	}

	public void showFragment(int position) {

		// Currently selected country
		mTitle = mCountries[position];

		CountryFragment cFragment = new CountryFragment();

		Bundle data = new Bundle();

		data.putInt("position", position);

		cFragment.setArguments(data);

		FragmentManager fragmentManager = getSupportFragmentManager();

		FragmentTransaction ft = fragmentManager.beginTransaction();

		ft.replace(R.id.content_frame, cFragment);

		// Committing the transaction
		ft.commit();
	}

	// Highlight the selected country : 0 to 4
	public void highlightSelectedCountry() {
		int selectedItem = mDrawerList.getCheckedItemPosition();

		if (selectedItem > 4)
			mDrawerList.setItemChecked(mPosition, true);
		else
			mPosition = selectedItem;

		if (mPosition != -1)
			getSupportActionBar().setTitle(mCountries[mPosition]);
	}

	public void telas(int position) {

		switch (position) {
		case 0:
			Intent resultado = new Intent(this, MainActivity.class);
			startActivity(resultado);

			break;

		case 1:
			// Intent resultado2 = new Intent(this, Lista_Sintomas.class);
			// startActivity(resultado2);

			break;
			
		case 2:
			// Intent resultado2 = new Intent(this, Lista_Sintomas.class);
			// startActivity(resultado2);

			break;
			
		case 3:
			versao();
			break;

		default:
			break;
		}
	}

	public void mostrar_Sintomas() {

		db.abrirDataBase();
		// db.lista_sintomas();

		String[] sintomas = new String[db.lista_sintomas().size()];

		int i = 0;
		for (String item : db.lista_sintomas()) {
			sintomas[i] = item;
			i++;
			Log.e("lista" + item, "Lista");
		}

		// Currently selected country
		mTitle = "Lista de Sontomas";

		// Creating a fragment object
		Lista_Sintomas sint_Fragment = new Lista_Sintomas();

		// Creating a Bundle object
		Bundle data = new Bundle();

				data.putStringArray("position", sintomas);

		sint_Fragment.setArguments(data);

		FragmentManager fragmentManager = getSupportFragmentManager();

		// Creating a fragment transaction
		FragmentTransaction ft = fragmentManager.beginTransaction();

		// Adding a fragment to the fragment transaction
	//	 ft.replace(R.id.content_frame, sint_Fragment);

		// Committing the transaction
		ft.commit();
	}

	public void inicio() {

		InicialFragment inicial = new InicialFragment();

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();

		ft.add(R.id.content_frame, new InicialFragment());
		ft.commit();

	}
	
	public void versao() {

		VersaoFragment inicial = new VersaoFragment();

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();

		ft.replace(R.id.content_frame, new VersaoFragment());
		ft.commit();

	}
	
	
}

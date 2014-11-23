package br.com.sad_diagnostico;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

public class Conexao extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/br.com.sad_diagnostico/databases/";
	private static String DB_NAME = "doenca.db";
	private SQLiteDatabase dbQuery;
	private final Context dbContexto;
	final String tb_sintomas = "SINTOMAS";
	final String campos[] = { "ID", "SINT_DESCRICAO" };
	private Boolean verifica = true;
	private ArrayList<String> l_proximo = new ArrayList<String>();

	public Boolean getVerifica() {
		return verifica;
	}

	public void setVerifica(Boolean verifica) {
		this.verifica = verifica;
	}

	public Conexao(Context context) {
		super(context, DB_NAME, null, 1);
		this.dbContexto = context;
	}

	public void CriarDataBase() throws SQLiteException {

		boolean dbExist = checkDataBase();

		if (!dbExist) {
			this.getReadableDatabase();

			try {
				Log.i("Criar Banco Banco de Dados Copiado", "Banco Não Existia");
				this.copiarDataBase();
			} catch (IOException e) {
				Log.i("Erro ao Copiar o Banco", "Banco erro");
				throw new Error("Erro ao copiar o Banco de Dados!");

			}
		}
	}

	private void copiarDataBase() throws IOException {

		InputStream myInput = dbContexto.getAssets().open(DB_NAME);
		String outFileName = DB_PATH + DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);

		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			File database = dbContexto.getDatabasePath(DB_NAME);

			if (database.exists()) {

				String myPath = database.getAbsolutePath();
				checkDB = SQLiteDatabase.openDatabase(myPath, null,
						SQLiteDatabase.OPEN_READWRITE);
				Log.i("Banco de Dados Chegado:" + database.toString(),
						"Banco OK");
			} else {
				Log.i("Banco não ainda não existe", "Banco deu pau");
			}

		} catch (SQLiteException e) {

			Log.i("Deu Pau aqui:" + e, "Banco deu pau");
		} finally {

			if (checkDB != null) {
				checkDB.close();
			}
		}
		return checkDB != null ? true : false;
	}

	public SQLiteDatabase abrirDataBase() throws SQLException {
		String myPath = DB_PATH + DB_NAME;

		try {
			Log.i("Banco de Dados Aberto de Boa", "Banco Ok aberto");
			dbQuery = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
		} catch (SQLException e) {
			Log.i("Banco de Dados Erro abertura:" + e, "Banco deu Pau");
		}

		return dbQuery;
	}

	@Override
	public synchronized void close() {
		if (dbQuery != null)
			dbQuery.close();
		Log.e("Banco", "Banco Fechado");
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public ArrayList<String> selecionaTodos() {
		ArrayList<String> list = new ArrayList<String>();
		Cursor cursor = dbQuery
				.rawQuery("SELECT * FROM SINTOMAS WHERE ID=43 OR ID=44 OR ID=45 OR ID=46 OR ID=47 "
								+ "OR ID=48 OR ID=49 OR ID=50 OR ID=51 OR ID=52 OR ID=53 OR ID=54 OR ID=55 ",
						null);
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0) + "-" + cursor.getString(1));
				
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public List repetidos(List entrada) {
		String grava1 = null, grava2 = null;

		for (int i = 0; i < entrada.size(); i++) {
			grava1 = (String) entrada.get(i);
			for (int j = i + 1; j < entrada.size(); j++) {
				grava2 = (String) entrada.get(j);
				if (grava1.equals(grava2)) {
					entrada.remove(j);
				}
			}
		}
		return entrada;
	}

	public String[] Pesquisa(ArrayList<String> ID, int cont) {

		String SINT0 = null, SINT1 = null, SINT2 = null, SINT3 = null, SINT4 = null, SINT5 = null, SINT6 = null, SINT7 = null;
		String recebe[] = new String[7];
		String proximos[] = new String[7];

		String SINT[] = new String[7];
		ArrayList<String> selecionado = new ArrayList<String>();
		ArrayList<String> proximo = new ArrayList<String>();
		ArrayList<String> doencas = new ArrayList<String>();
		String entrada;
		String s_proximo = null;
		Cursor cursor = null;

		String compara = null;

		for (int y = 0; y < ID.size(); y++) {
			SINT[y + 1] = String.valueOf(ID.get(y));
		}

		try {

			switch (cont) {

			case 1:
				cursor = dbQuery.rawQuery("SELECT * FROM V_SINTOMAS1", null);
				compara = SINT[cont];
				break;

			case 2:
				cursor = dbQuery.rawQuery("SELECT * FROM V_SINTOMAS2", null);
				compara = SINT[cont];
				break;

			case 3:
				cursor = dbQuery.rawQuery("SELECT * FROM V_SINTOMAS3", null);
				compara = SINT[cont];
				break;

			case 4:
				cursor = dbQuery.rawQuery("SELECT * FROM V_SINTOMAS4", null);
				compara = SINT[cont];
				break;

			case 5:
				cursor = dbQuery.rawQuery("SELECT * FROM V_SINTOMAS5", null);
				compara = SINT[cont];
				break;

			case 6:
				cursor = dbQuery.rawQuery("SELECT * FROM V_SINTOMAS6", null);
				compara = SINT[cont];
				break;
			case 7:
				cursor = dbQuery.rawQuery("SELECT * FROM V_Sintoma7", null);
				compara = SINT[cont];
				break;
			}
			int num = 1;

			while (cursor.moveToNext()) {

				recebe[0] = String.valueOf(num);

				String b_id = cursor.getString(cursor.getColumnIndex("CAT"));
				String b_doenca = cursor.getString(cursor
						.getColumnIndex("DESCRICAO"));
				String b_sintoma;

				if (cont > 1) {
					b_sintoma = cursor.getString(cursor
							.getColumnIndex("SINT_DESCRICAO:" + (cont - 1)));
				} else {
					b_sintoma = cursor.getString(cursor
							.getColumnIndex("SINT_DESCRICAO"));
				}
				// Log.e(" " + num + " " + b_doenca, "Doenças");
				int prox = 0;
				for (int y = 0; y < 7; y++) {
					String prox_sintomas = cursor.getString(cursor
							.getColumnIndex("SINT" + y));
					if ((prox_sintomas != null) && (prox_sintomas != "null")
							&& (Integer.parseInt(prox_sintomas) != 0)) {
						selec_proximo(prox_sintomas);
					}
				}
				for (int i = 0; i < 8; i++) {

					if (cursor.getString(cursor.getColumnIndex("SINT" + i)) != null) {
						if (cursor.getString(cursor.getColumnIndex("SINT" + i))
								.equals(compara)) {

							entrada = cursor.getString(cursor
									.getColumnIndex("SINT" + i));

							selecionado.add(entrada);
							doencas.add(b_doenca);

							repetidos(doencas);
							repetidos(selecionado);

							recebe[0] = String.valueOf(num);

							for (String item : selecionado) {
								recebe[1] = item + "-" + b_sintoma;
							}

							for (String item : doencas) {
								recebe[3] = b_id + "-" + item;
							}
						}

					}
				}
				num++;
			}
			cursor.close();
		}

		catch (SQLiteException e) {
			Log.e("Erro aqui:" + e, "Erro na PESQUISA");
		}

		return recebe;
	}

	public void retorna(ArrayList<String> ID, int cont) {

		String SINT[] = new String[7];
		String recebe[] = new String[7];
		ArrayList<String> primeiro = new ArrayList<String>();
		recebe[cont] = "";
		Cursor cursor = null;
		String compara = null;

		for (int t = 0; t < ID.size(); t++) {
			if (ID.get(0) != null) {
				SINT[0] = String.valueOf(ID.get(0));
			}
			if (t == 1) {
				if (ID.get(1) != null) {
					SINT[1] = (String) ID.get(1);
				}
			}
			if (t == 2) {
				if (ID.get(2) != null) {
					SINT[2] = (String) ID.get(2);
				}
			}
			if (t == 3) {
				if (ID.get(3) != null) {
					SINT[3] = (String) ID.get(3);
				}
			}
			if (t == 4) {
				if (ID.get(4) != null) {
					SINT[4] = (String) ID.get(4);
				}
			}
			if (t == 5) {
				if (ID.get(5) != null) {
					SINT[5] = (String) ID.get(5);
				}
			}
			if (t == 6) {
				if (ID.get(6) != null) {
					SINT[6] = (String) ID.get(6);
				}
			}
		}

		Log.i("" + SINT[0] + " " + SINT[1], "String[]");

		try {

			switch (cont) {

			case 1:
				dbQuery.execSQL("CREATE VIEW V_SINTOMAS1 AS SELECT * FROM CATEGORIAS,SINTOMAS ON (CATEGORIAS.SINT0 = '"
						+ SINT[0]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[0]
						+ "')"
						+ "OR (CATEGORIAS.SINT1 = '"
						+ SINT[0]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[0]
						+ "') OR (CATEGORIAS.SINT2 = '"
						+ SINT[0]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[0]
						+ "')"
						+ "OR (CATEGORIAS.SINT3 = '"
						+ SINT[0]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[0]
						+ "') OR (CATEGORIAS.SINT4 = '"
						+ SINT[0]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[0]
						+ "')"
						+ "OR (CATEGORIAS.SINT5 = '"
						+ SINT[0]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[0]
						+ "') OR (CATEGORIAS.SINT6 = '"
						+ SINT[0]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[0]
						+ "')"
						+ "OR (CATEGORIAS.SINT7 = '"
						+ SINT[0]
						+ "' AND SINTOMAS.ID = '" + SINT[0] + "')");
				break;

			case 2:
				dbQuery.execSQL("CREATE VIEW V_SINTOMAS2 AS SELECT * FROM V_SINTOMAS1,SINTOMAS ON (V_SINTOMAS1.SINT0 = '"
						+ SINT[1]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[1]
						+ "')"
						+ "OR (V_SINTOMAS1.SINT1 = '"
						+ SINT[1]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[1]
						+ "') OR (V_SINTOMAS1.SINT2 = '"
						+ SINT[1]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[1]
						+ "')"
						+ "OR (V_SINTOMAS1.SINT3 = '"
						+ SINT[1]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[1]
						+ "') OR (V_SINTOMAS1.SINT4 = '"
						+ SINT[1]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[1]
						+ "')"
						+ "OR (V_SINTOMAS1.SINT5 = '"
						+ SINT[1]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[1]
						+ "') OR (V_SINTOMAS1.SINT6 = '"
						+ SINT[1]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[1]
						+ "')"
						+ "OR (V_SINTOMAS1.SINT7 = '"
						+ SINT[1]
						+ "' AND SINTOMAS.ID = '" + SINT[1] + "')");
				break;

			case 3:

				dbQuery.execSQL("CREATE VIEW V_SINTOMAS3 AS SELECT * FROM V_SINTOMAS2,SINTOMAS ON (V_SINTOMAS2.SINT0 = '"
						+ SINT[2]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[2]
						+ "')"
						+ "OR (V_SINTOMAS2.SINT1 = '"
						+ SINT[2]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[2]
						+ "') OR (V_SINTOMAS2.SINT2 = '"
						+ SINT[2]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[2]
						+ "')"
						+ "OR (V_SINTOMAS2.SINT3 = '"
						+ SINT[2]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[2]
						+ "') OR (V_SINTOMAS2.SINT4 = '"
						+ SINT[2]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[2]
						+ "')"
						+ "OR (V_SINTOMAS2.SINT5 = '"
						+ SINT[2]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[2]
						+ "') OR (V_SINTOMAS2.SINT6 = '"
						+ SINT[2]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[2]
						+ "')"
						+ "OR (V_SINTOMAS2.SINT7 = '"
						+ SINT[2]
						+ "' AND SINTOMAS.ID = '" + SINT[2] + "')");
				break;

			case 4:
				dbQuery.execSQL("CREATE VIEW V_SINTOMAS4 AS SELECT * FROM V_SINTOMAS3,SINTOMAS ON (V_SINTOMAS3.SINT0 = '"
						+ SINT[3]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[3]
						+ "')"
						+ "OR (V_SINTOMAS3.SINT1 = '"
						+ SINT[3]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[3]
						+ "') OR (V_SINTOMAS3.SINT2 = '"
						+ SINT[3]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[3]
						+ "')"
						+ "OR (V_SINTOMAS3.SINT3 = '"
						+ SINT[3]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[3]
						+ "') OR (V_SINTOMAS3.SINT4 = '"
						+ SINT[3]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[3]
						+ "')"
						+ "OR (V_SINTOMAS3.SINT5 = '"
						+ SINT[3]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[3]
						+ "') OR (V_SINTOMAS3.SINT6 = '"
						+ SINT[3]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[3]
						+ "')"
						+ "OR (V_SINTOMAS3.SINT7 = '"
						+ SINT[3]
						+ "' AND SINTOMAS.ID = '" + SINT[3] + "')");
				break;

			case 5:
				dbQuery.execSQL("CREATE VIEW V_SINTOMAS5 AS SELECT * FROM V_SINTOMAS4,SINTOMAS ON (V_SINTOMAS4.SINT0 = '"
						+ SINT[5]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[5]
						+ "')"
						+ "OR (V_SINTOMAS4.SINT1 = '"
						+ SINT[5]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[5]
						+ "') OR (V_SINTOMAS4.SINT2 = '"
						+ SINT[5]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[5]
						+ "')"
						+ "OR (V_SINTOMAS4.SINT3 = '"
						+ SINT[5]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[5]
						+ "') OR (V_SINTOMAS4.SINT4 = '"
						+ SINT[5]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[5]
						+ "')"
						+ "OR (V_SINTOMAS4.SINT5 = '"
						+ SINT[5]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[5]
						+ "') OR (V_SINTOMAS4.SINT6 = '"
						+ SINT[5]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[5]
						+ "')"
						+ "OR (V_SINTOMAS4.SINT7 = '"
						+ SINT[5]
						+ "' AND SINTOMAS.ID = '" + SINT[5] + "')");
				break;

			case 6:
				dbQuery.execSQL("CREATE VIEW V_SINTOMAS6 AS SELECT * FROM V_SINTOMAS5,SINTOMAS ON (V_SINTOMAS5.SINT0 = '"
						+ SINT[6]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[6]
						+ "')"
						+ "OR (V_SINTOMAS5.SINT1 = '"
						+ SINT[6]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[6]
						+ "') OR (V_SINTOMAS5.SINT2 = '"
						+ SINT[6]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[6]
						+ "')"
						+ "OR (V_SINTOMAS5.SINT3 = '"
						+ SINT[6]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[6]
						+ "') OR (V_SINTOMAS5.SINT4 = '"
						+ SINT[6]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[6]
						+ "')"
						+ "OR (V_SINTOMAS5.SINT5 = '"
						+ SINT[6]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[6]
						+ "') OR (V_SINTOMAS5.SINT6 = '"
						+ SINT[6]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[6]
						+ "')"
						+ "OR (V_SINTOMAS5.SINT7 = '"
						+ SINT[6]
						+ "' AND SINTOMAS.ID = '" + SINT[6] + "')");
				break;

			case 7:
				dbQuery.execSQL("CREATE VIEW V_SINTOMAS7 AS SELECT * FROM V_SINTOMAS6,SINTOMAS ON (V_SINTOMAS6.SINT0 = '"
						+ SINT[7]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[7]
						+ "')"
						+ "OR (V_SINTOMAS6.SINT1 = '"
						+ SINT[7]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[7]
						+ "') OR (V_SINTOMAS6.SINT2 = '"
						+ SINT[7]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[7]
						+ "')"
						+ "OR (V_SINTOMAS6.SINT3 = '"
						+ SINT[7]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[7]
						+ "') OR (V_SINTOMAS6.SINT4 = '"
						+ SINT[7]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[7]
						+ "')"
						+ "OR (V_SINTOMAS6.SINT5 = '"
						+ SINT[7]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[7]
						+ "') OR (V_SINTOMAS6.SINT6 = '"
						+ SINT[7]
						+ "' AND SINTOMAS.ID = '"
						+ SINT[7]
						+ "')"
						+ "OR (V_SINTOMAS6.SINT7 = '"
						+ SINT[7]
						+ "' AND SINTOMAS.ID = '" + SINT[7] + "')");
				break;
			}

		} catch (SQLiteException e) {
			Log.e("" + e, "Erro no retona");
			System.exit(0);
		}
	}

	public ArrayList<String> repetidos(ArrayList<String> entrada) {
		String grava1 = null, grava2 = null;

		for (int i = 0; i < entrada.size(); i++) {
			grava1 = (String) entrada.get(i);
			for (int j = i + 1; j < entrada.size(); j++) {
				grava2 = (String) entrada.get(j);
				if (grava1.equals(grava2)) {
					entrada.remove(j);
					// Log.i("Entrada"+entrada, "Repetidos entrada");

				}
			}

		}
		return entrada;
	}

	public ArrayList<String> repetidos_proximo(ArrayList<String> entrada,
			ArrayList<String> saida) {
		String grava1 = null, grava2 = null;

		for (int i = 0; i < entrada.size(); i++) {
			grava1 = (String) entrada.get(i);
			for (int j = i + 1; j < saida.size(); j++) {
				grava2 = (String) saida.get(j);
				if (grava1.equals(grava2)) {
					// System.err.println("Repetidos:" + saida);
					saida.remove(j);

				}
			}
		}
		return saida;
	}

	public ArrayList<String> selec_proximo(String prox_sintomas) {
		String id = null;
		String descricao = null;
		String proximo;
		Cursor cursor;

		try {
			cursor = dbQuery.rawQuery("SELECT * FROM SINTOMAS WHERE ID = ?",
					new String[] { prox_sintomas + "" });

			if (cursor.moveToNext()) {

				id = cursor.getString(cursor.getColumnIndex("ID"));
				descricao = cursor.getString(cursor
						.getColumnIndex("SINT_DESCRICAO"));

				proximo = id + "-" + descricao;
				l_proximo.add(proximo);

				// Log.e(""+l_proximo, "Proximo Coxexao");

				// Log.i("" + id + " " + descricao, "Proximos Sintomas");
			}
			cursor.close();
		} catch (SQLException e) {
		}
		return l_proximo;
	}

	public void inicializa() throws SQLException {

		for (int i = 1; i < 7; i++) {
			try {
				dbQuery.execSQL("DROP VIEW IF EXISTS V_SINTOMAS" + i);
				// Log.i("Excluido V_Sintomas" + i, "Excluido");
			} catch (SQLiteException e) {
				Log.e("" + e, "Erro Inicializar");
			}
		}
	}

	public ArrayList<String> lista_sintomas() {

		ArrayList<String> resultado = new ArrayList<String>();
		Cursor percorre;

		try {

			percorre = dbQuery.rawQuery("SELECT * FROM CATEGORIAS ORDER BY DESCRICAO ASC", null);
			
			while (percorre.moveToNext()) {
				resultado.add(percorre.getString(1));
			}
			percorre.close();
		} catch (SQLException e) {
			Log.e("" + e, "Lista Sintomas deu pau");
		}
		return resultado;
	}
	
		
	public ArrayList<String> frequentes() {
		ArrayList<String> list = new ArrayList<String>();
		Cursor cursor = dbQuery.rawQuery("SELECT * FROM SINTOMAS WHERE ID=50 OR ID=61 OR ID=78 OR ID=173 OR ID=114",	null);
		if (cursor.moveToFirst() && !cursor.isNull(0) ) {
			while (cursor.moveToNext()) {
				list.add(cursor.getString(0) + "-" + cursor.getString(1));
				Log.e(""+cursor.getString(0)+"-"+cursor.getString(1), "Freguentes");
			}
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return list;
	}

	public ArrayList<String> getL_proximo() {
		return l_proximo;
	}

	public void setL_proximo(ArrayList<String> l_proximo) {
		this.l_proximo = l_proximo;
	}

}

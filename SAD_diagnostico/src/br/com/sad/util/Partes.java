package br.com.sad.util;

import java.util.ArrayList;

import android.util.Log;

public class Partes {

	final private String cabeca = "ID=2 OR ID= 19 OR ID=21 OR ID=23 OR ID= 25 OR ID=34 OR ID=47 "
			+ " OR ID=62  OR ID=69 OR ID=96 OR ID=105 OR ID=112 OR ID= 120 OR ID=122 OR ID=123 "
			+ " OR ID=124 OR ID=125 OR ID=145 OR ID=146 OR ID=149 OR ID=152 OR ID=157 OR ID=158 "
			+ " OR ID=160 OR ID=162 OR ID=163 OR ID=164 OR ID=171 OR ID=189 OR ID=209 OR ID=218 "
			+ " OR ID=220 OR ID=227 OR ID=232 OR ID=234 OR ID=237 OR ID=241 OR ID=255 OR ID=256 ";

	final private String pescoco = "ID=18 OR ID=84 OR ID=98 OR ID=50 OR ID=40 OR ID=115 OR ID=162 "
			+ " OR ID=163 OR ID=142 OR ID=181 OR ID=189 OR ID=194 OR ID=235 OR ID=254 OR ID=175 "
			+ " OR ID=206 OR ID=53 OR ID=188 OR ID=109 ";

	final private String peito = "ID=12 OR ID=39 OR ID=53 OR ID=56 OR ID=58 OR ID=74 OR ID=144 "
			+ " OR ID=162 OR ID=164 OR ID=181 OR ID=194 OR ID=196 OR ID=213 OR ID=215 OR ID=216 "
			+ " OR ID=217 OR ID=224 OR ID=225 OR ID=226 OR ID=230 OR ID=231";

	final private String braco = "ID=15 OR ID=18 OR ID=53 OR ID=61 OR ID=63 OR ID=68 OR ID=69 "
			+ " OR ID=109 OR ID=110 OR ID=116 OR ID=126 OR ID=127 OR ID=135 OR ID=165 OR ID=169 "
			+ " OR ID=180 OR ID=181 OR ID=186 OR ID=187 OR ID=188 OR ID=194 OR ID=196 OR ID=197 "
			+ " OR ID=206 OR ID=208";

	final private String abdomem = "ID=12 OR ID=18 OR ID=53 OR ID=60 OR ID=61 OR ID=64 OR ID=69 "
			+ " OR ID=109 OR ID=126 OR ID=139 OR ID=180 OR ID=181 OR ID=189 OR ID=194 OR ID=196 "
			+ " OR ID=197 OR ID=208 OR ID=228 OR ID=235 OR ID=236 OR ID=238 OR ID=239 OR ID=240 "
			+ " OR ID=246 OR ID=251";

	final private String genital = "ID=12 OR ID=65 OR ID=95 OR ID=168 OR ID=170 OR ID=190 OR ID=192 "
			+ " OR ID=193 OR ID=195 OR ID=201 OR ID=214 OR ID=243 OR ID=244";

	final private String perna = "ID=12 OR ID=15 OR ID=18 OR ID=53 OR ID=63 OR ID=64 OR ID=68 "
			+ " OR ID=97 OR ID=109 OR ID=116 OR ID=126 OR ID=127 OR ID=135 OR ID=151 OR ID=165 "
			+ " OR ID=169 OR ID=181 OR ID=186 OR ID=187 OR ID=188 OR ID=191 OR ID=194 OR ID=196 "
			+ " OR ID=197 OR ID=208 OR ID=221 OR ID=243 OR ID=245";

	final private String bunda = "ID=32 OR ID=42 OR ID=43 OR ID=44 OR ID=80 OR ID=81 OR ID=85 "
			+ " OR ID=94 OR ID=139 OR ID=147 OR ID=166 OR ID=198 OR ID=211 OR ID=212 OR ID=246 "
			+ " OR ID=248 OR ID=249";

	final private String costa = "ID=12 OR ID=53 OR ID=54 OR ID=61 OR ID=64 OR ID=69 OR ID=109 "
			+ " OR ID=126 OR ID=181 OR ID=189 OR ID=194 OR ID=196";

	final private String sinais = "ID=3 OR ID=8 OR ID=16 OR ID=17 OR ID=24 OR ID=30 OR ID=36 "
			+ " OR ID=66 OR ID=71 OR ID=72 OR ID=73 OR ID=78 OR ID=89 OR ID=91 OR ID=102 OR ID=108 "
			+ " OR ID=111 OR ID=114 OR ID=129 OR ID=130 OR ID=131 OR ID=153 OR ID=155 OR ID=158 "
			+ " OR ID=165 OR ID=173 OR ID=189 OR ID=204 OR ID=213 OR ID=229 OR ID=235";

	public String getCabeca() {
		return cabeca;
	}

	public String getPescoco() {
		return pescoco;
	}

	public String getPeito() {
		return peito;
	}

	public String getBraco() {
		return braco;
	}

	public String getAbdomem() {
		return abdomem;
	}

	public String getGenital() {
		return genital;
	}

	public String getPerna() {
		return perna;
	}

	public String getBunda() {
		return bunda;
	}

	public String getCosta() {
		return costa;
	}

	public String getSinais() {
		return sinais;
	}

	public String[] trata_sintomas(ArrayList<String> frequentes) {
		ArrayList<String> l_selecionados = new ArrayList<String>(0);
		l_selecionados = frequentes;
		String sintomas[] = new String[frequentes.size()];

		int t = 0;
		for (String item : l_selecionados) {
			sintomas[t] = item;
//			Log.e("" + sintomas[t], "LAÇO FOR");
			t++;
		}
		return sintomas;
	}

}

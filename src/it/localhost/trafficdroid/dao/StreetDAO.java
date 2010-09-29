package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.dto.StreetDTO;

import java.util.ArrayList;

import android.content.SharedPreferences;

public class StreetDAO {
	private static final String s1 = "A1 Milano-Napoli";
	private static final String s2 = "A3 Napoli-Salerno";
	private static final String s3 = "A3 Salerno-Reggio Calabria";
	private static final String s4 = "A4 Torino-Trieste";
	private static final String s5 = "A5 Torino-Aosta";
	private static final String s6 = "A6 Torino-Savona";
	private static final String s7 = "A7 Milano-Genova";
	private static final String s8 = "A8 Autostrada dei Laghi";
	private static final String s9 = "A9 Lainate-Como-Chiasso";
	private static final String s10 = "A10 Genova-Ventimiglia";
	private static final String s11 = "A11 Firenze-Pisa";
	private static final String s121 = "A12 Genova-Rosignano";
	private static final String s12 = "A12 Roma-Civitavecchia";
	private static final String s13 = "A13 Bologna-Padova";
	private static final String s14 = "A14 Bologna-Taranto";
	private static final String s15 = "A15 Parma-La Spezia";
	private static final String s16 = "A16 Napoli-Canosa";
	private static final String s18 = "A18 Messina-Catania";
	private static final String s19 = "A19 Palermo-Catania";
	private static final String s20 = "A20 Messina-Palermo";
	private static final String s21 = "A21 Torino-Brescia";
	private static final String s22 = "A22 Brennero-Modena";
	private static final String s23 = "A23 Palmanova-Tarvisio";
	private static final String s24 = "A24 Roma-Teramo";
	private static final String s241 = "A24 A24 interna GRA";
	private static final String s25 = "A25 Torano-Pescara";
	private static final String s26 = "A26 Genova-Gravellona Toce";
	private static final String s27 = "A27 Venezia-Belluno";
	private static final String s28 = "A28 Portogruaro-Pordenone";
	private static final String s29 = "A29 Palermo-Mazzara del vallo";
	private static final String s30 = "A30 Caserta-Salerno";
	private static final String s31 = "A31 Vicenza-Thiene";
	private static final String s32 = "A32 Torino-Bardonecchia";
	private static final String s50 = "A50 Tangenziale Ovest";
	private static final String s51 = "A51 Tangenziale Est";
	private static final String s52 = "A52 Tangenziale Nord";
	private static final String s55 = "A55 Tangenziale di Torino";
	private static final String s90 = "A90 Grande Raccordo Anulare";
	private static final String s91 = "A91 Autostrada Fiumicino-Roma";
	private static final String s101 = "A1dir Dir. A1 Roma Nord";
	private static final String s102 = "A1dir Dir. A1 Roma Sud";
	private static final String s143 = "A1R Rac. A1 Bettole-Perugia";
	private static final String s103 = "A1R Rac. A1 Firenze-Siena";
	private static final String s302 = "A3R Rac. A3 Avellino-Salerno";
	private static final String s303 = "A3R Rac. A3 Sicignano-Potenza";
	private static final String s501 = "A5dir Dir. A5/A4 Ivrea";
	private static final String s701 = "A7R Rac. A7 Bereguardo-Pavia";
	private static final String s111 = "A11R Rac. A11/A12 Viareggio-Lucca";
	private static final String s131 = "A13R Rac. A13 Ferrara-P. Garibaldi";
	private static final String s142 = "A14dir Dir. A14 Ravenna";
	private static final String s141 = "A14R A14 Bologna-Casalecchio";
	private static final String s144 = "A14R Rac. A14 Ascoli Piceno";
	private static final String s161 = "A16R Rac. A16 Benevento";
	private static final String s181 = "A18T Tangenziale di Catania";
	private static final String s211 = "A21R Rac. A21/A1 Cortemaggiore";
	private static final String s261 = "A26dir A26/A4 Stroppiana-Santhia";
	private static final String s262 = "A26dir Dir. A26/A8 Cattico-Gallarate";
	private static final String s263 = "A26dir Dir. A26/A7 Predosa";
	private static final String s291 = "A29Dir Dir. A29 Alcamo-Trapani";
	private static final String s552 = "A55Dir Dir. A55 Pinerolo";
	private static final String s551 = "A55R Rac. A55 Torino-Caselle";
	private static final String s553 = "A55T Tangenziale di Torino Sud";
	private static final String s56 = "A56 Tangenziale di Napoli";
	private static final String s301 = "RA4 S.S. Ionica 106";

	public static ArrayList<StreetDTO> getAllEnabled(SharedPreferences settings) {
		ArrayList<StreetDTO> streets = new ArrayList<StreetDTO>();
		if (settings.getBoolean(s1, false))
			streets.add(new StreetDTO(s1, 1));
		if (settings.getBoolean(s2, false))
			streets.add(new StreetDTO(s2, 2));
		if (settings.getBoolean(s3, false))
			streets.add(new StreetDTO(s3, 3));
		if (settings.getBoolean(s4, false))
			streets.add(new StreetDTO(s4, 4));
		if (settings.getBoolean(s5, false))
			streets.add(new StreetDTO(s5, 5));
		if (settings.getBoolean(s6, false))
			streets.add(new StreetDTO(s6, 6));
		if (settings.getBoolean(s7, false))
			streets.add(new StreetDTO(s7, 7));
		if (settings.getBoolean(s8, false))
			streets.add(new StreetDTO(s8, 8));
		if (settings.getBoolean(s9, false))
			streets.add(new StreetDTO(s9, 9));
		if (settings.getBoolean(s10, false))
			streets.add(new StreetDTO(s10, 10));
		if (settings.getBoolean(s11, false))
			streets.add(new StreetDTO(s11, 11));
		if (settings.getBoolean(s121, false))
			streets.add(new StreetDTO(s121, 121));
		if (settings.getBoolean(s12, false))
			streets.add(new StreetDTO(s12, 12));
		if (settings.getBoolean(s13, false))
			streets.add(new StreetDTO(s13, 13));
		if (settings.getBoolean(s14, false))
			streets.add(new StreetDTO(s14, 14));
		if (settings.getBoolean(s15, false))
			streets.add(new StreetDTO(s15, 15));
		if (settings.getBoolean(s16, false))
			streets.add(new StreetDTO(s16, 16));
		if (settings.getBoolean(s18, false))
			streets.add(new StreetDTO(s18, 18));
		if (settings.getBoolean(s19, false))
			streets.add(new StreetDTO(s19, 19));
		if (settings.getBoolean(s20, false))
			streets.add(new StreetDTO(s20, 20));
		if (settings.getBoolean(s21, false))
			streets.add(new StreetDTO(s21, 21));
		if (settings.getBoolean(s22, false))
			streets.add(new StreetDTO(s22, 22));
		if (settings.getBoolean(s23, false))
			streets.add(new StreetDTO(s23, 23));
		if (settings.getBoolean(s24, false))
			streets.add(new StreetDTO(s24, 24));
		if (settings.getBoolean(s241, false))
			streets.add(new StreetDTO(s241, 241));
		if (settings.getBoolean(s25, false))
			streets.add(new StreetDTO(s25, 25));
		if (settings.getBoolean(s26, false))
			streets.add(new StreetDTO(s26, 26));
		if (settings.getBoolean(s27, false))
			streets.add(new StreetDTO(s27, 27));
		if (settings.getBoolean(s28, false))
			streets.add(new StreetDTO(s28, 28));
		if (settings.getBoolean(s29, false))
			streets.add(new StreetDTO(s29, 29));
		if (settings.getBoolean(s30, false))
			streets.add(new StreetDTO(s30, 30));
		if (settings.getBoolean(s31, false))
			streets.add(new StreetDTO(s31, 31));
		if (settings.getBoolean(s32, false))
			streets.add(new StreetDTO(s32, 32));
		if (settings.getBoolean(s50, false))
			streets.add(new StreetDTO(s50, 50));
		if (settings.getBoolean(s51, false))
			streets.add(new StreetDTO(s51, 51));
		if (settings.getBoolean(s52, false))
			streets.add(new StreetDTO(s52, 52));
		if (settings.getBoolean(s55, false))
			streets.add(new StreetDTO(s55, 55));
		if (settings.getBoolean(s90, false))
			streets.add(new StreetDTO(s90, 90));
		if (settings.getBoolean(s91, false))
			streets.add(new StreetDTO(s91, 91));
		if (settings.getBoolean(s101, false))
			streets.add(new StreetDTO(s101, 101));
		if (settings.getBoolean(s102, false))
			streets.add(new StreetDTO(s102, 102));
		if (settings.getBoolean(s143, false))
			streets.add(new StreetDTO(s143, 143));
		if (settings.getBoolean(s103, false))
			streets.add(new StreetDTO(s103, 103));
		if (settings.getBoolean(s302, false))
			streets.add(new StreetDTO(s302, 302));
		if (settings.getBoolean(s303, false))
			streets.add(new StreetDTO(s303, 303));
		if (settings.getBoolean(s501, false))
			streets.add(new StreetDTO(s501, 501));
		if (settings.getBoolean(s701, false))
			streets.add(new StreetDTO(s701, 701));
		if (settings.getBoolean(s111, false))
			streets.add(new StreetDTO(s111, 111));
		if (settings.getBoolean(s131, false))
			streets.add(new StreetDTO(s131, 131));
		if (settings.getBoolean(s142, false))
			streets.add(new StreetDTO(s142, 142));
		if (settings.getBoolean(s141, false))
			streets.add(new StreetDTO(s141, 141));
		if (settings.getBoolean(s144, false))
			streets.add(new StreetDTO(s144, 144));
		if (settings.getBoolean(s161, false))
			streets.add(new StreetDTO(s161, 161));
		if (settings.getBoolean(s181, false))
			streets.add(new StreetDTO(s181, 181));
		if (settings.getBoolean(s211, false))
			streets.add(new StreetDTO(s211, 211));
		if (settings.getBoolean(s261, false))
			streets.add(new StreetDTO(s261, 261));
		if (settings.getBoolean(s262, false))
			streets.add(new StreetDTO(s262, 262));
		if (settings.getBoolean(s263, false))
			streets.add(new StreetDTO(s263, 263));
		if (settings.getBoolean(s291, false))
			streets.add(new StreetDTO(s291, 291));
		if (settings.getBoolean(s552, false))
			streets.add(new StreetDTO(s552, 552));
		if (settings.getBoolean(s551, false))
			streets.add(new StreetDTO(s551, 551));
		if (settings.getBoolean(s553, false))
			streets.add(new StreetDTO(s553, 553));
		if (settings.getBoolean(s56, false))
			streets.add(new StreetDTO(s56, 56));
		if (settings.getBoolean(s301, false))
			streets.add(new StreetDTO(s301, 301));
		return streets;
	}
}
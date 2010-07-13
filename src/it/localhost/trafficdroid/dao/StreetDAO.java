package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.dto.StreetDTO;

import java.util.ArrayList;

import android.content.SharedPreferences;

public class StreetDAO {
	public static ArrayList<StreetDTO> getAllEnabled(SharedPreferences settings) {
		ArrayList<StreetDTO> streets = new ArrayList<StreetDTO>();
		if (settings.getBoolean("s1", false))
			streets.add(new StreetDTO("A1 Milano-Napoli", 1));
		if (settings.getBoolean("s2", false))
			streets.add(new StreetDTO("A3 Napoli-Salerno", 2));
		if (settings.getBoolean("s3", false))
			streets.add(new StreetDTO("A3 Salerno-Reggio Calabria", 3));
		if (settings.getBoolean("s4", false))
			streets.add(new StreetDTO("A4 Torino-Trieste", 4));
		if (settings.getBoolean("s5", false))
			streets.add(new StreetDTO("A5 Torino-Aosta", 5));
		if (settings.getBoolean("s6", false))
			streets.add(new StreetDTO("A6 Torino-Savona", 6));
		if (settings.getBoolean("s7", false))
			streets.add(new StreetDTO("A7 Milano-Genova", 7));
		if (settings.getBoolean("s8", false))
			streets.add(new StreetDTO("A8 Autostrada dei Laghi", 8));
		if (settings.getBoolean("s9", false))
			streets.add(new StreetDTO("A9 Lainate-Como-Chiasso", 9));
		if (settings.getBoolean("s10", false))
			streets.add(new StreetDTO("A10 Genova-Ventimiglia", 10));
		if (settings.getBoolean("s11", false))
			streets.add(new StreetDTO("A11 Firenze-Pisa", 11));
		if (settings.getBoolean("s121", false))
			streets.add(new StreetDTO("A12 Genova-Rosignano", 121));
		if (settings.getBoolean("s12", false))
			streets.add(new StreetDTO("A12 Roma-Civitavecchia", 12));
		if (settings.getBoolean("s13", false))
			streets.add(new StreetDTO("A13 Bologna-Padova", 13));
		if (settings.getBoolean("s14", false))
			streets.add(new StreetDTO("A14 Bologna-Taranto", 14));
		if (settings.getBoolean("s15", false))
			streets.add(new StreetDTO("A15 Parma-La Spezia", 15));
		if (settings.getBoolean("s16", false))
			streets.add(new StreetDTO("A16 Napoli-Canosa", 16));
		if (settings.getBoolean("s18", false))
			streets.add(new StreetDTO("A18 Messina-Catania", 18));
		if (settings.getBoolean("s19", false))
			streets.add(new StreetDTO("A19 Palermo-Catania", 19));
		if (settings.getBoolean("s20", false))
			streets.add(new StreetDTO("A20 Messina-Palermo", 20));
		if (settings.getBoolean("s21", false))
			streets.add(new StreetDTO("A21 Torino-Brescia", 21));
		if (settings.getBoolean("s22", false))
			streets.add(new StreetDTO("A22 Brennero-Modena", 22));
		if (settings.getBoolean("s23", false))
			streets.add(new StreetDTO("A23 Palmanova-Tarvisio", 23));
		if (settings.getBoolean("s24", false))
			streets.add(new StreetDTO("A24 Roma-Teramo", 24));
		if (settings.getBoolean("s241", false))
			streets.add(new StreetDTO("A24 A24 interna GRA", 241));
		if (settings.getBoolean("s25", false))
			streets.add(new StreetDTO("A25 Torano-Pescara", 25));
		if (settings.getBoolean("s26", false))
			streets.add(new StreetDTO("A26 Genova-Gravellona Toce", 26));
		if (settings.getBoolean("s27", false))
			streets.add(new StreetDTO("A27 Venezia-Belluno", 27));
		if (settings.getBoolean("s28", false))
			streets.add(new StreetDTO("A28 Portogruaro-Pordenone", 28));
		if (settings.getBoolean("s29", false))
			streets.add(new StreetDTO("A29 Palermo-Mazzara del vallo", 29));
		if (settings.getBoolean("s30", false))
			streets.add(new StreetDTO("A30 Caserta-Salerno", 30));
		if (settings.getBoolean("s31", false))
			streets.add(new StreetDTO("A31 Vicenza-Thiene", 31));
		if (settings.getBoolean("s32", false))
			streets.add(new StreetDTO("A32 Torino-Bardonecchia", 32));
		if (settings.getBoolean("s50", false))
			streets.add(new StreetDTO("A50 Tangenziale Ovest", 50));
		if (settings.getBoolean("s51", false))
			streets.add(new StreetDTO("A51 Tangenziale Est", 51));
		if (settings.getBoolean("s52", false))
			streets.add(new StreetDTO("A52 Tangenziale Nord", 52));
		if (settings.getBoolean("s55", false))
			streets.add(new StreetDTO("A55 Tangenziale di Torino", 55));
		if (settings.getBoolean("s90", false))
			streets.add(new StreetDTO("A90 Grande Raccordo Anulare", 90));
		if (settings.getBoolean("s91", false))
			streets.add(new StreetDTO("A91 Autostrada Fiumicino-Roma", 91));
		if (settings.getBoolean("s101", false))
			streets.add(new StreetDTO("A1dir Dir. A1 Roma Nord", 101));
		if (settings.getBoolean("s102", false))
			streets.add(new StreetDTO("A1dir Dir. A1 Roma Sud", 102));
		if (settings.getBoolean("s143", false))
			streets.add(new StreetDTO("A1R Rac. A1 Bettole-Perugia", 143));
		if (settings.getBoolean("s103", false))
			streets.add(new StreetDTO("A1R Rac. A1 Firenze-Siena", 103));
		if (settings.getBoolean("s302", false))
			streets.add(new StreetDTO("A3R Rac. A3 Avellino-Salerno", 302));
		if (settings.getBoolean("s303", false))
			streets.add(new StreetDTO("A3R Rac. A3 Sicignano-Potenza", 303));
		if (settings.getBoolean("s501", false))
			streets.add(new StreetDTO("A5dir Dir. A5/A4 Ivrea", 501));
		if (settings.getBoolean("s701", false))
			streets.add(new StreetDTO("A7R Rac. A7 Bereguardo-Pavia", 701));
		if (settings.getBoolean("s111", false))
			streets.add(new StreetDTO("A11R Rac. A11/A12 Viareggio-Lucca", 111));
		if (settings.getBoolean("s131", false))
			streets.add(new StreetDTO("A13R Rac. A13 Ferrara-P. Garibaldi", 131));
		if (settings.getBoolean("s142", false))
			streets.add(new StreetDTO("A14dir Dir. A14 Ravenna", 142));
		if (settings.getBoolean("s141", false))
			streets.add(new StreetDTO("A14R A14 Bologna-Casalecchio", 141));
		if (settings.getBoolean("s144", false))
			streets.add(new StreetDTO("A14R Rac. A14 Ascoli Piceno", 144));
		if (settings.getBoolean("s161", false))
			streets.add(new StreetDTO("A16R Rac. A16 Benevento", 161));
		if (settings.getBoolean("s181", false))
			streets.add(new StreetDTO("A18T Tangenziale di Catania", 181));
		if (settings.getBoolean("s211", false))
			streets.add(new StreetDTO("A21R Rac. A21/A1 Cortemaggiore", 211));
		if (settings.getBoolean("s261", false))
			streets.add(new StreetDTO("A26dir A26/A4 Stroppiana-Santhia", 261));
		if (settings.getBoolean("s262", false))
			streets.add(new StreetDTO("A26dir Dir. A26/A8 Cattico-Gallarate", 262));
		if (settings.getBoolean("s263", false))
			streets.add(new StreetDTO("A26dir Dir. A26/A7 Predosa", 263));
		if (settings.getBoolean("s291", false))
			streets.add(new StreetDTO("A29Dir Dir. A29 Alcamo-Trapani", 291));
		if (settings.getBoolean("s552", false))
			streets.add(new StreetDTO("A55Dir Dir. A55 Pinerolo", 552));
		if (settings.getBoolean("s551", false))
			streets.add(new StreetDTO("A55R Rac. A55 Torino-Caselle", 551));
		if (settings.getBoolean("s553", false))
			streets.add(new StreetDTO("A55T Tangenziale di Torino Sud", 553));
		if (settings.getBoolean("s56", false))
			streets.add(new StreetDTO("A56 Tangenziale di Napoli", 56));
		if (settings.getBoolean("s301", false))
			streets.add(new StreetDTO("RA4 S.S. Ionica 106", 301));
		return streets;
	}
}
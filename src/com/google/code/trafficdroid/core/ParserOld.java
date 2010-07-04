package com.google.code.trafficdroid.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.google.code.trafficdroid.dto.Zone;

public class ParserOld {
	// String[] numeri = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
	// "11", "121", "12", "13", "14", "15", "16", "18", "19", "20", "21", "22",
	// "23", "24", "241", "25", "26", "27", "28", "29", "30", "31", "32", "50",
	// "51", "52", "55", "90", "91", "101", "102", "143", "103", "302", "303",
	// "501", "701", "111", "131", "142", "141", "144", "161", "181", "211",
	// "261", "262", "263", "291", "552", "551", "553", "56", "301"};
	// String[] nomi = { "A1 Milano-Napoli",
	// "A3 Napoli-Salerno",
	// "A3 Salerno-Reggio Calabria",
	// "A4 Torino-Trieste",
	// "A5 Torino-Aosta",
	// "A6 Torino-Savona",
	// "A7 Milano-Genova",
	// "A8 Autostrada dei Laghi",
	// "A9 Lainate-Como-Chiasso",
	// "A10 Genova-Ventimiglia",
	// "A11 Firenze-Pisa",
	// "A12 Genova-Rosignano",
	// "A12 Roma-Civitavecchia",
	// "A13 Bologna-Padova",
	// "A14 Bologna-Taranto",
	// "A15 Parma-La Spezia",
	// "A16 Napoli-Canosa",
	// "A18 Messina-Catania",
	// "A19 Palermo-Catania",
	// "A20 Messina-Palermo",
	// "A21 Torino-Brescia",
	// "A22 Brennero-Modena",
	// "A23 Palmanova-Tarvisio",
	// "A24 Roma-Teramo",
	// "A24 A24 interna GRA",
	// "A25 Torano-Pescara",
	// "A26 Genova-Gravellona Toce",
	// "A27 Venezia-Belluno",
	// "A28 Portogruaro-Pordenone",
	// "A29 Palermo-Mazzara del vallo",
	// "A30 Caserta-Salerno",
	// "A31 Vicenza-Thiene",
	// "A32 Torino-Bardonecchia",
	// "A50 Tangenziale Ovest",
	// "A51 Tangenziale Est",
	// "A52 Tangenziale Nord",
	// "A55 Tangenziale di Torino",
	// "A90 Grande Raccordo Anulare",
	// "A91 Autostrada Fiumicino-Roma",
	// "A1dir Dir. A1 Roma Nord",
	// "A1dir Dir. A1 Roma Sud",
	// "A1R Rac. A1 Bettole-Perugia",
	// "A1R Rac. A1 Firenze-Siena",
	// "A3R Rac. A3 Avellino-Salerno",
	// "A3R Rac. A3 Sicignano-Potenza",
	// "A5dir Dir. A5/A4 Ivrea",
	// "A7R Rac. A7 Bereguardo-Pavia",
	// "A11R Rac. A11/A12 Viareggio-Lucca",
	// "A13R Rac. A13 Ferrara-P. Garibaldi",
	// "A14dir Dir. A14 Ravenna",
	// "A14R A14 Bologna-Casalecchio",
	// "A14R Rac. A14 Ascoli Piceno",
	// "A16R Rac. A16 Benevento",
	// "A18T Tangenziale di Catania",
	// "A21R Rac. A21/A1 Cortemaggiore",
	// "A26dir A26/A4 Stroppiana-Santhia",
	// "A26dir Dir. A26/A8 Cattico-Gallarate",
	// "A26dir Dir. A26/A7 Predosa",
	// "A29Dir Dir. A29 Alcamo-Trapani",
	// "A55Dir Dir. A55 Pinerolo",
	// "A55R Rac. A55 Torino-Caselle",
	// "A55T Tangenziale di Torino Sud",
	// "A56 Tangenziale di Napoli",
	// "RA4 S.S. Ionica 106"
	// };
	public static List<Zone> parse(String string) {
		String[] LetterValues = { "?", "<10", "10-30", "30-50", "50-70", "70-90", ">90" };
		boolean esci = false;
		int SegmentsCount = 1;
		int pos = 0;
		Vector<String> SegmentValues = new Vector<String>();
		List<Zone> res = new ArrayList<Zone>();
		while (esci == false) {
			String marker = "tipU" + SegmentsCount;
			pos = string.indexOf(marker, pos) + 1;
			if (pos > 0 + marker.length() + 14) {
				pos = pos + marker.length() + 14;
				esci = false;
				int endpos = string.indexOf(" -", pos);
				SegmentValues.add(string.substring(pos, endpos));
				SegmentsCount += 1;
			} else {
				esci = true;
			}
		}
		for (int index = 1; index <= SegmentsCount - 1; index++) {
			// Determine positions of numbers and letters for traffic data:
			int InizioSezione = string.indexOf("LD" + index);
			int InizioVelSin = string.indexOf("lx", InizioSezione) + 2;
			int InizioVelDes = string.indexOf("rx", InizioSezione) + 2;
			// ********* Left value ***********
			// Get value for LEFT column:
			int ArrayIndex = Integer.parseInt(string.substring(InizioVelSin, InizioVelSin + 1));
			Zone tr = new Zone();
			String beginning = index + " " + SegmentValues.get(index - 1);
			tr.setName(beginning);
			String Letter = LetterValues[ArrayIndex];
			tr.setSpeedLeft(Letter);
			// ********* Right value ***********
			// Get value for RIGHT column:
			ArrayIndex = Integer.parseInt(string.substring(InizioVelDes, InizioVelDes + 1));
			Letter = LetterValues[ArrayIndex];
			tr.setSpeedRight(Letter);
			res.add(tr);
		} // end for
		return res;
	}
}

package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.dto.StreetDTO;

import java.util.ArrayList;

public class StreetDAO {
	public static ArrayList<StreetDTO> get() {
		ArrayList<StreetDTO> streets = new ArrayList<StreetDTO>();
		streets.add(new StreetDTO("A1 Milano-Napoli", true, 1));
		streets.add(new StreetDTO("A3 Napoli-Salerno", true, 2));
		streets.add(new StreetDTO("A3 Salerno-Reggio Calabria", true, 3));
		streets.add(new StreetDTO("A4 Torino-Trieste", true, 4));
		streets.add(new StreetDTO("A5 Torino-Aosta", true, 5));
		streets.add(new StreetDTO("A6 Torino-Savona", true, 6));
		streets.add(new StreetDTO("A7 Milano-Genova", true, 7));
		streets.add(new StreetDTO("A8 Autostrada dei Laghi", true, 8));
		streets.add(new StreetDTO("A9 Lainate-Como-Chiasso", true, 9));
		streets.add(new StreetDTO("A10 Genova-Ventimiglia", true, 10));
		streets.add(new StreetDTO("A11 Firenze-Pisa", true, 11));
		streets.add(new StreetDTO("A12 Genova-Rosignano", true, 121));
		streets.add(new StreetDTO("A12 Roma-Civitavecchia", true, 12));
		streets.add(new StreetDTO("A13 Bologna-Padova", true, 13));
		streets.add(new StreetDTO("A14 Bologna-Taranto", true, 14));
		streets.add(new StreetDTO("A15 Parma-La Spezia", true, 15));
		streets.add(new StreetDTO("A16 Napoli-Canosa", true, 16));
		streets.add(new StreetDTO("A18 Messina-Catania", true, 18));
		streets.add(new StreetDTO("A19 Palermo-Catania", true, 19));
		streets.add(new StreetDTO("A20 Messina-Palermo", true, 20));
		streets.add(new StreetDTO("A21 Torino-Brescia", true, 21));
		streets.add(new StreetDTO("A22 Brennero-Modena", true, 22));
		streets.add(new StreetDTO("A23 Palmanova-Tarvisio", true, 23));
		streets.add(new StreetDTO("A24 Roma-Teramo", true, 24));
		streets.add(new StreetDTO("A24 A24 interna GRA", true, 241));
		streets.add(new StreetDTO("A25 Torano-Pescara", true, 25));
		streets.add(new StreetDTO("A26 Genova-Gravellona Toce", true, 26));
		streets.add(new StreetDTO("A27 Venezia-Belluno", true, 27));
		streets.add(new StreetDTO("A28 Portogruaro-Pordenone", true, 28));
		streets.add(new StreetDTO("A29 Palermo-Mazzara del vallo", true, 29));
		streets.add(new StreetDTO("A30 Caserta-Salerno", true, 30));
		streets.add(new StreetDTO("A31 Vicenza-Thiene", true, 31));
		streets.add(new StreetDTO("A32 Torino-Bardonecchia", true, 32));
		streets.add(new StreetDTO("A50 Tangenziale Ovest", true, 50));
		streets.add(new StreetDTO("A51 Tangenziale Est", true, 51));
		streets.add(new StreetDTO("A52 Tangenziale Nord", true, 52));
		streets.add(new StreetDTO("A55 Tangenziale di Torino", true, 55));
		streets.add(new StreetDTO("A90 Grande Raccordo Anulare", true, 90));
		streets.add(new StreetDTO("A91 Autostrada Fiumicino-Roma", true, 91));
		streets.add(new StreetDTO("A1dir Dir. A1 Roma Nord", true, 101));
		streets.add(new StreetDTO("A1dir Dir. A1 Roma Sud", true, 102));
		streets.add(new StreetDTO("A1R Rac. A1 Bettole-Perugia", true, 143));
		streets.add(new StreetDTO("A1R Rac. A1 Firenze-Siena", true, 103));
		streets.add(new StreetDTO("A3R Rac. A3 Avellino-Salerno", true, 302));
		streets.add(new StreetDTO("A3R Rac. A3 Sicignano-Potenza", true, 303));
		streets.add(new StreetDTO("A5dir Dir. A5/A4 Ivrea", true, 501));
		streets.add(new StreetDTO("A7R Rac. A7 Bereguardo-Pavia", true, 701));
		streets.add(new StreetDTO("A11R Rac. A11/A12 Viareggio-Lucca", true, 111));
		streets.add(new StreetDTO("A13R Rac. A13 Ferrara-P. Garibaldi", true, 131));
		streets.add(new StreetDTO("A14dir Dir. A14 Ravenna", true, 142));
		streets.add(new StreetDTO("A14R A14 Bologna-Casalecchio", true, 141));
		streets.add(new StreetDTO("A14R Rac. A14 Ascoli Piceno", true, 144));
		streets.add(new StreetDTO("A16R Rac. A16 Benevento", true, 161));
		streets.add(new StreetDTO("A18T Tangenziale di Catania", true, 181));
		streets.add(new StreetDTO("A21R Rac. A21/A1 Cortemaggiore", true, 211));
		streets.add(new StreetDTO("A26dir A26/A4 Stroppiana-Santhia", true, 261));
		streets.add(new StreetDTO("A26dir Dir. A26/A8 Cattico-Gallarate", true, 262));
		streets.add(new StreetDTO("A26dir Dir. A26/A7 Predosa", true, 263));
		streets.add(new StreetDTO("A29Dir Dir. A29 Alcamo-Trapani", true, 291));
		streets.add(new StreetDTO("A55Dir Dir. A55 Pinerolo", true, 552));
		streets.add(new StreetDTO("A55R Rac. A55 Torino-Caselle", true, 551));
		streets.add(new StreetDTO("A55T Tangenziale di Torino Sud", true, 553));
		streets.add(new StreetDTO("A56 Tangenziale di Napoli", true, 56));
		streets.add(new StreetDTO("RA4 S.S. Ionica 106", true, 301));
		return streets;
	}
}
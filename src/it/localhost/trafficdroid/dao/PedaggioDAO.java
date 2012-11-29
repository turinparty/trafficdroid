package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.exception.BadConfException;

import java.net.URL;
import java.util.Scanner;

public class PedaggioDAO {
	private static final String url = "http://autostrade.it/autostrade/ricercaPercorso.do?tipo=G&dtxpDa=";
	private static final String arg = "&dtxpA=";
	private static final String spanAperto = "<span class=\"km\">";
	private static final String and = "&";
	private static final String delimiter = "\\A";
	private static final String badParams = "Parametri non validi";

	public static String getData(int da, int a) throws BadConfException {
		try {
			String s = new Scanner(new URL(url + da + arg + a).openStream()).useDelimiter(delimiter).next();
			int start = s.indexOf(spanAperto);
			if (start != -1) {
				start = start + spanAperto.length();
				return s.substring(start, s.indexOf(and, start)).trim();
			} else
				throw new BadConfException(badParams);
		} catch (Exception e) {
			throw new BadConfException(e.getMessage());
		}
	}
}
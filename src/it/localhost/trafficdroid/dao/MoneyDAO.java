package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.exception.BadConfException;

import java.net.URL;
import java.util.Scanner;

public class MoneyDAO {
	private static final String path = "/autostrade/ricercaPercorso.do?tipo=G&dtxpDa=";
	private static final String arg = "&dtxpA=";
	private static final String spanAperto = "<span class=\"km\">";
	private static final String and = "&";
	private static final String delimiter = "\\A";
	private static final String badParams = "Parametri non validi";

	public static String getData(int da, int a, String baseUrl) throws BadConfException {
		try {
			String s = new Scanner(new URL(Const.http + baseUrl + path + da + arg + a).openStream()).useDelimiter(delimiter).next();
			int start = s.indexOf(spanAperto);
			if (start != -1) {
				start = start + spanAperto.length();
				int end = s.indexOf(and, start);
				return s.substring(start, end).trim();
			} else
				throw new BadConfException(badParams);
		} catch (Exception e) {
			throw new BadConfException(e.getMessage());
		}
	}
}
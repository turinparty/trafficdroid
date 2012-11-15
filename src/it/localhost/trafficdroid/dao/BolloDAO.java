package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.dto.BolloDTO;
import it.localhost.trafficdroid.exception.BadConfException;

import java.net.URL;
import java.util.Scanner;

public class BolloDAO {
	private static final String bolloUrl = "https://servizi.aci.it/Bollonet/calcolo.do?TipoVeicolo=1&LinguaSelezionata=ita&CodiceServizio=2&Targa=";
	private static final String euroUrl = "http://servizi.aci.it/AciTipoEuroWeb/calcolo.do?TipoVeicolo=1&LinguaSelezionata=ita&CodiceServizio=42&NormativaCEE=&Targa=";
	private static final String leftLimitBollo = "<span class=\"blu\">";
	private static final String leftLimitEuro = "<td><b>";
	private static final String rightLimitBollo = "</span>";
	private static final String rightLimitEuro = "</b></td>";
	private static final String delimiter = "\\A";
	private static final String badParams = "Parametri non validi";

	public static BolloDTO getData(String targa) throws BadConfException {
		try {
			String bollo = new Scanner(new URL(bolloUrl + targa).openStream()).useDelimiter(delimiter).next();
			String euro = new Scanner(new URL(euroUrl + targa).openStream()).useDelimiter(delimiter).next();
			int startBollo = bollo.indexOf(leftLimitBollo);
			int startEuro = euro.indexOf(leftLimitEuro);
			if (startBollo != -1 && startEuro != -1) {
				startBollo = startBollo + leftLimitBollo.length();
				startEuro = startEuro + leftLimitEuro.length();
				return new BolloDTO(bollo.substring(startBollo, bollo.indexOf(rightLimitBollo, startBollo)).trim(), euro.substring(startEuro, euro.indexOf(rightLimitEuro, startEuro)).trim());
			} else
				throw new BadConfException(badParams);
		} catch (Exception e) {
			throw new BadConfException(e.getMessage());
		}
	}
}
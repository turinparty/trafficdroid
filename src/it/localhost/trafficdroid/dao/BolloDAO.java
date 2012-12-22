package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.exception.BadConfException;

import java.net.URL;
import java.util.Scanner;

public class BolloDAO {
	private static final String bolloUrl = "https://servizi.aci.it/Bollonet/calcolo.do?TipoVeicolo=1&LinguaSelezionata=ita&CodiceServizio=2&Targa=";
	private static final String startDelimiterYes = "<table summary=\"Informazioni sul veicolo\" id=\"info\">";
	private static final String endDelimiterYes = "<p>� possibile il pagamento nelle seguenti modalit�: </p>";
	private static final String startDelimiterNo = "<span class=\"blu\">";
	private static final String endDelimiterNo = "<br /><br />";
	private static final String header = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" /><title>Bollo Auto</title></head><body>\n";
	private static final String footer = "</body></html>";
	private static final String RM1 = "<br />";
	private static final String RM2 = "\t";
	private static final String RM3 = "\n";
	private static final String RM4 = "<font tyle=\"clor:red\">";
	private static final String RM5 = "</font>";
	private static final String BLANK = "";
	private static final String delimiter = "\\A";
	private static final String badParams = "Parametri non validi";

	public static String getData(String targa) throws BadConfException {
		try {
			String bollo = new Scanner(new URL(bolloUrl + targa).openStream()).useDelimiter(delimiter).next();
			int startBollo = bollo.indexOf(startDelimiterYes);
			if (startBollo < 1)
				startBollo = bollo.indexOf(startDelimiterNo);
			int endBollo = bollo.indexOf(endDelimiterYes, startBollo);
			if (endBollo < 1)
				endBollo = bollo.indexOf(endDelimiterNo, startBollo);
			if (startBollo != -1 && endBollo != -1) {
				bollo = bollo.substring(startBollo, endBollo).trim();
				bollo = bollo.replaceAll(RM1, BLANK);
				bollo = bollo.replaceAll(RM2, BLANK);
				bollo = bollo.replaceAll(RM3, BLANK);
				bollo = bollo.replaceAll(RM4, BLANK);
				bollo = bollo.replaceAll(RM5, BLANK);
				bollo = header + bollo + footer;
				return bollo;
			} else
				throw new BadConfException(badParams);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BadConfException(e.getMessage());
		}
	}
}
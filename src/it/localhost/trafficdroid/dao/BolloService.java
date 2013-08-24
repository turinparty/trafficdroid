package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.dto.BaseDto;
import it.localhost.trafficdroid.dto.BolloDto;

import java.net.URL;
import java.util.Scanner;

import android.os.AsyncTask;

public class BolloService extends AsyncTask<String, Void, BaseDto> {
	private static final String bolloUrl = "https://servizi.aci.it/Bollonet/calcolo.do?LinguaSelezionata=ita&CodiceServizio=2&TipoVeicolo=";
	private static final String param1 = "&RegioneResidenza=";
	private static final String param2 = "&Targa=";
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

	@Override
	protected BaseDto doInBackground(String... args) {
		try {
			String out = new Scanner(new URL(bolloUrl + args[0] + param1 + args[1] + param2 + args[2]).openStream()).useDelimiter(delimiter).next();
			int startBollo = out.indexOf(startDelimiterYes);
			if (startBollo < 1)
				startBollo = out.indexOf(startDelimiterNo);
			int endBollo = out.indexOf(endDelimiterYes, startBollo);
			if (endBollo < 1)
				endBollo = out.indexOf(endDelimiterNo, startBollo);
			if (startBollo != -1 && endBollo != -1) {
				out = out.substring(startBollo, endBollo).trim();
				out = out.replaceAll(RM1, BLANK);
				out = out.replaceAll(RM2, BLANK);
				out = out.replaceAll(RM3, BLANK);
				out = out.replaceAll(RM4, BLANK);
				out = out.replaceAll(RM5, BLANK);
				out = header + out + footer;
				return new BolloDto(true, out);
			} else
				return new BaseDto(false, badParams);
		} catch (Exception e) {
			return new BaseDto(false, e.getMessage());
		}
	}
}

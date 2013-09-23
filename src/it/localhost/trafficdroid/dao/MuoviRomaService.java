package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.dto.BaseDTO;
import it.localhost.trafficdroid.dto.MuoviRomaDTO;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import android.os.AsyncTask;

public class MuoviRomaService extends AsyncTask<String, Void, BaseDTO> {
	private static final String URL = "http://muovi.roma.it/ztl/";
	private static final String VALUE = "red_bullet.gif";
	private static final String NAME = "<h3>";
	private static final String FILE = "\\A";
	private static final String LINE = "\n";
	private static final String BLANK = "";

	@Override
	protected BaseDTO doInBackground(String... args) {
		try {
			ArrayList<String[]> out = new ArrayList<String[]>();
			for (String line : new Scanner(new URL(URL).openStream()).useDelimiter(FILE).next().split(LINE)) {
				if (line.contains(NAME) || line.contains(VALUE)) {
					if (line.contains(NAME))
						out.add(new String[] { line.trim().substring(4, line.length() - 7), BLANK });
					else if (line.contains(VALUE))
						out.get(out.size() - 1)[1] = line.trim().substring(105, line.length() - 10);
				}
			}
			for (String[] strings : out)
				System.err.println(strings[0] + ":" + strings[1]);
			return new MuoviRomaDTO(true, out);
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseDTO(false, e.getMessage());
		}
	}
}

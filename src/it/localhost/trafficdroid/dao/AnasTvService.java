package it.localhost.trafficdroid.dao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import android.os.AsyncTask;

public class AnasTvService extends AsyncTask<Void, Void, String[]> {
	private static final String EXT = ".mp4.jpg";
	private static final String TAG = "<img src=\"/images/video/news/";
	private static final String DELIMITER_ROW = "\\n";
	private static final String ANAS_URL = "http://www.stradeanas.tv";
	private static final String DELIMITER_FILE = "\\A";

	@Override
	protected String[] doInBackground(Void... params) {
		ArrayList<String> out = new ArrayList<String>();
		try {
			String bollo = new Scanner(new URL(ANAS_URL).openStream()).useDelimiter(DELIMITER_FILE).next();
			String[] rows = bollo.split(DELIMITER_ROW);
			for (String row : rows)
				if (row.contains(TAG))
					out.add(row.substring(37, row.indexOf(EXT)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toArray(new String[out.size()]);
	}
}
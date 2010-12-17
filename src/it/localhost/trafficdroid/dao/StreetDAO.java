package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.content.res.Resources;

public class StreetDAO {
	public static ArrayList<StreetDTO> getAllEnabled(SharedPreferences settings, Resources resources) {
		ArrayList<StreetDTO> streets = new ArrayList<StreetDTO>();
		StreetDTO street;
		int[] streetsId = resources.getIntArray(Const.streetsRes[0]);
		String[] streetsName = resources.getStringArray(Const.streetsRes[1]);
		for (int i = 0; i < streetsId.length; i++) {
			street = new StreetDTO(streetsId[i]);
			boolean streetEnabled = settings.getBoolean(Integer.toString(street.getId()), false);
			String[][] zones = new String[2][];
			zones[0] = resources.getStringArray(Const.zonesRes()[0][i]);
			zones[1] = resources.getStringArray(Const.zonesRes()[1][i]);
			for (int j = 0; j < zones[0].length; j++)
				if (streetEnabled || settings.getBoolean(zones[0][j], false))
					street.addZone(new ZoneDTO(zones[0][j], zones[1][j]));
			if (street.getZones().size() > 0) {
				street.setName(streetsName[i]);
				streets.add(street);
			}
		}
		return streets;
	}
}
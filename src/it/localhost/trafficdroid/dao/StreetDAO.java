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
		for (int i = 0; i < resources.getIntArray(Const.streetsResId[0]).length; i++) {
			street = new StreetDTO(resources.getIntArray(Const.streetsResId[0])[i]);
			boolean streetEnabled = settings.getBoolean(Integer.toString(street.getId()), false);
			for (int j = 0; j < resources.getStringArray(Const.zonesResId()[0][i]).length; j++)
				if (streetEnabled || settings.getBoolean(resources.getStringArray(Const.zonesResId()[0][i])[j], false))
					street.addZone(new ZoneDTO(resources.getStringArray(Const.zonesResId()[1][i])[j]));
			if (street.getZones().size() > 0) {
				street.setName(resources.getStringArray(Const.streetsResId[1])[i]);
				streets.add(street);
			}
		}
		return streets;
	}
}
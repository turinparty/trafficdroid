package it.localhost.trafficdroid.dao;

import java.util.ArrayList;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class DlcTaskDAO {
	private static DLCTaskDTO dlcTaskDto;

	public static DLCTaskDTO get(SharedPreferences sharedPreferences, Resources resources) {
		String url = sharedPreferences.getString(resources.getString(R.string.urlKey), Const.emptyString);
		ArrayList<StreetDTO> enabledStreets = StreetDAO.getAllEnabled(sharedPreferences, resources);
		if (url.equals(Const.emptyString) || url.equals(resources.getString(R.string.urlDefaultValue)))
			dlcTaskDto = null;
		else {
			dlcTaskDto = new DLCTaskDTO(enabledStreets, url);
			dlcTaskDto.setCongestionThreshold(Integer.parseInt(sharedPreferences.getString(resources.getString(R.string.trafficKey), Const.CongestionThreshold)));
		}
		return dlcTaskDto;
	}
}

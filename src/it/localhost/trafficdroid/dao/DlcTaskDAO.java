package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import android.content.SharedPreferences;
import android.content.res.Resources;

public class DlcTaskDAO {
	private static DLCTaskDTO dlcTaskDto;

	public static DLCTaskDTO get(SharedPreferences sharedPreferences, Resources resources) {
		String url = sharedPreferences.getString(resources.getString(R.string.urlKey), Const.emptyString);
		if (url.equals(Const.emptyString) || url.equals(resources.getString(R.string.urlDefaultValue)))
			dlcTaskDto = null;
		else {
			dlcTaskDto = new DLCTaskDTO(StreetDAO.getAllEnabled(sharedPreferences, resources), url);
			dlcTaskDto.setCongestionThreshold(Integer.parseInt(sharedPreferences.getString(resources.getString(R.string.trafficKey), Const.defaultCongestionThreshold)));
		}
		return dlcTaskDto;
	}
}

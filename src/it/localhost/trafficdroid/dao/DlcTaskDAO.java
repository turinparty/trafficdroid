package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

public class DlcTaskDAO {
	public static DLCTaskDTO get(Context context) throws TdException {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Resources resources = context.getResources();
		String url = sharedPreferences.getString(resources.getString(R.string.urlKey), Const.emptyString);
		if (!url.equals(Const.emptyString) && !url.equals(resources.getString(R.string.urlDefaultValue))) {
			DLCTaskDTO dlcTaskDto = new DLCTaskDTO(StreetDAO.getAllEnabled(sharedPreferences, resources), url);
			dlcTaskDto.setCongestionThreshold(Integer.parseInt(sharedPreferences.getString(resources.getString(R.string.notificationSpeedKey), Const.notificationSpeedKeyDefault)));
			return dlcTaskDto;
		} else
			throw new TdException(TdException.MalformedURLException, resources.getString(R.string.badConf));
	}
}
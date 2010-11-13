package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.core.UpdateService;
import android.os.Binder;

public class LocalBinder extends Binder
{
	private final UpdateService weatherPlusService;

	public LocalBinder(UpdateService weatherPlusService)
	{
		this.weatherPlusService = weatherPlusService;
	}

	public UpdateService getService()
	{
		return weatherPlusService;
	}
}
package it.localhost.trafficdroid.widget.provider;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

public class WidgetZoneProvider extends AppWidgetProvider {
	public static final String WIDGET_ZONE_STREET = "widgetZoneStreet";
	public static final String WIDGET_ZONE_ZONE = "widgetZoneZone";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++)
			updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int mAppWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		views.setOnClickPendingIntent(R.id.widget, PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0));
		try {
			MainDTO dto = MainDAO.retrieve();
			StreetDTO street = dto.getStreet(TdApp.getPrefInt(WIDGET_ZONE_STREET + mAppWidgetId, 0));
			if (street != null) {
				ZoneDTO zone = street.getZone(TdApp.getPrefInt(WIDGET_ZONE_ZONE + mAppWidgetId, 0));
				if (zone != null) {
					views.setTextViewText(R.id.streetDirLeft, street.getDirectionLeft());
					views.setTextViewText(R.id.streetDirRight, street.getDirectionRight());
					views.setTextViewText(R.id.zoneName, DateFormat.getTimeFormat(context).format(dto.getTrafficTime()) + " " + zone.getName());
					views.setTextViewText(R.id.zoneSpeedLeft, Short.toString(zone.getSpeedLeft()));
					views.setTextViewText(R.id.zoneSpeedRight, Short.toString(zone.getSpeedRight()));
					views.setImageViewResource(R.id.trendLeft, zone.getTrendLeft());
					views.setImageViewResource(R.id.trendRight, zone.getTrendRight());
				}
			}
		} catch (Exception e) {
			//Do nothing
		}
		appWidgetManager.updateAppWidget(mAppWidgetId, views);
	}
}
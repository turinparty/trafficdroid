package it.localhost.trafficdroid.widget.provider;

import it.localhost.trafficdroid.premium.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dao.MainDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetZoneProvider extends AppWidgetProvider {
	public static final String WIDGET_ZONE_STREET = "widgetZoneStreet";
	public static final String WIDGET_ZONE_ZONE = "widgetZoneZone";

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++)
			updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
	}

	public static void onUpdate(Context context) {
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetZoneProvider.class));
		for (int i = 0; i < appWidgetIds.length; i++)
			updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int mAppWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		views.setOnClickPendingIntent(R.id.widget, PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0));
		int street = TdApp.getPrefInt(WIDGET_ZONE_STREET + mAppWidgetId, 0);
		int zone = TdApp.getPrefInt(WIDGET_ZONE_ZONE + mAppWidgetId, 0);
		try {
			MainDTO dto = MainDAO.retrieve();
			if (dto.getStreet(street) != null && dto.getStreet(street).getZone(zone) != null) {
				views.setTextViewText(R.id.zoneName, dto.getStreet(street).getZone(zone).getName());
				views.setTextViewText(R.id.zoneSpeedLeft, Short.toString(dto.getStreet(street).getZone(zone).getSpeedLeft()));
				views.setTextViewText(R.id.zoneSpeedRight, Short.toString(dto.getStreet(street).getZone(zone).getSpeedRight()));
				views.setTextViewText(R.id.streetDirLeft, dto.getStreet(street).getDirectionLeft());
				views.setTextViewText(R.id.streetDirRight, dto.getStreet(street).getDirectionRight());
				views.setImageViewResource(R.id.trendLeft, dto.getStreet(street).getZone(zone).getTrendLeft());
				views.setImageViewResource(R.id.trendRight, dto.getStreet(street).getZone(zone).getTrendRight());
			}
		} catch (Exception e) {
			//Do nothing
		}
		appWidgetManager.updateAppWidget(mAppWidgetId, views);
	}
}
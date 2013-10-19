package it.localhost.trafficdroid.widget.provider;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.PersistanceService;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

public class WidgetStreetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int i = 0; i < appWidgetIds.length; i++)
			updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
	}

	public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int mAppWidgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
		views.setOnClickPendingIntent(R.id.widget, PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0));
		try {
			MainDTO dto = PersistanceService.retrieve(context);
			StreetDTO street = dto.getStreet(Utility.getWidgetStreetStreet(context, mAppWidgetId));
			if (street != null) {
				views.setTextViewText(R.id.zoneName, DateFormat.getTimeFormat(context).format(dto.getTrafficTime()) + " " + street.getName());
				views.setTextViewText(R.id.zoneSpeedLeft, Short.toString(street.getSpeedLeft()));
				views.setTextViewText(R.id.zoneSpeedRight, Short.toString(street.getSpeedRight()));
			//	views.setTextViewText(R.id.streetDirLeft, street.getDirectionLeft());
			//	views.setTextViewText(R.id.streetDirRight, street.getDirectionRight());
				views.setImageViewResource(R.id.trendLeft, street.getTrendLeft());
				views.setImageViewResource(R.id.trendRight, street.getTrendRight());
			}
		} catch (Exception e) {
			// Do nothing
		}
		appWidgetManager.updateAppWidget(mAppWidgetId, views);
	}
}
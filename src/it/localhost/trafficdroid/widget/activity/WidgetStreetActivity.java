package it.localhost.trafficdroid.widget.activity;

import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.PersistanceService;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.widget.provider.WidgetStreetProvider;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WidgetStreetActivity extends ListActivity {
	private MainDTO dto;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setResult(RESULT_CANCELED);
		List<String> data = new ArrayList<String>();
		try {
			dto = PersistanceService.retrieve(this);
			for (StreetDTO street : dto.getStreets())
				data.add(street.getName());
			setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		int mAppWidgetId = getIntent().getExtras().getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		Utility.getEditor(this).putInt(WidgetStreetProvider.WIDGET_STREET_STREET + mAppWidgetId, dto.getStreets().get(position).getId()).commit();
		WidgetStreetProvider.updateAppWidget(this, AppWidgetManager.getInstance(this), mAppWidgetId);
		setResult(RESULT_OK, getIntent());
		finish();
	}
}
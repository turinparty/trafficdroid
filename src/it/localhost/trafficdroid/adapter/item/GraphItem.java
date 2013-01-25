package it.localhost.trafficdroid.adapter.item;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.analytics.tracking.android.EasyTracker;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.AbstractActivity;
import it.localhost.trafficdroid.activity.WebViewActivity;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class GraphItem extends AbstractItem {
	private static final String firstUrl = "http://vai-cdn.stradeanas.it/Appscripts/sinotraffic.php?city=";
	private static final String secondUrl = "&ts=";
	private String date;
	private StreetDTO streetDTO;

	public GraphItem(Context context, StreetDTO streetDTO) {
		super(context);
		this.streetDTO = streetDTO;
		date = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
	}

	@Override
	public int getType() {
		return itemTypes[5];
	}

	@Override
	public View inflateView() {
		View view = inflater.inflate(R.layout.main_item_graph, null, false);
		return view;
	}

	@Override
	public void fillView(View view) {
	}

	@Override
	public void onClick() {
		EasyTracker.getTracker().sendEvent(AbstractActivity.eventCatGraph, AbstractActivity.eventActionOpen, streetDTO.getGraph(), (long) 0);
		Intent intent = new Intent(context, WebViewActivity.class);
		intent.putExtra(WebViewActivity.urlTag, firstUrl + streetDTO.getGraph() + secondUrl + date);
		context.startActivity(intent);
	}
}

package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.WebViewActivity;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdAnalytics;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class GraphItem extends AbstractChildItem {
	private static final String trafficGraphFirst = "<!DOCTYPE html><html><head><meta charset=\"utf-8\"><title>TrafficGraph</title></head><body style=\"background-color:black\"><img src=\"";
	private static final String trafficGraphSecond = ".gif\" alt=\"TrafficGraph\" /></body></html>";
	private StreetDTO streetDTO;

	public GraphItem(Context context, StreetDTO streetDTO) {
		super(context);
		this.streetDTO = streetDTO;
	}

	@Override
	public int getType() {
		return Const.itemTypes[2];
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
		TdAnalytics.trackEvent(TdAnalytics.eventCatGraph, TdAnalytics.eventActionOpen, streetDTO.getName(), 0);
		Intent intent = new Intent(context, WebViewActivity.class);
		intent.putExtra(Const.data, trafficGraphFirst + Const.http + TdApp.getPrefString(R.string.providerTrafficKey, R.string.providerTrafficDefault) + Const.slash + streetDTO.getGraph() + trafficGraphSecond);
		context.startActivity(intent);
	}
}

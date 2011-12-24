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
		TdAnalytics.trackEvent(Const.eventCatGraph, Const.eventActionOpen, streetDTO.getName(), 0);
		Intent intent = new Intent(context, WebViewActivity.class);
		intent.putExtra(Const.data, Const.trafficGraphFirst + Const.http + TdApp.getPrefString(R.string.providerTrafficKey, R.string.providerTrafficDefault) + Const.slash + streetDTO.getGraph() + Const.trafficGraphSecond);
		context.startActivity(intent);
	}
}

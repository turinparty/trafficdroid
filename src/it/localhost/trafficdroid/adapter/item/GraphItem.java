package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;

import java.io.Serializable;

import localhost.toolkit.widget.HeterogeneousItem;
import android.content.Context;
import android.view.View;

public class GraphItem extends HeterogeneousItem {
	public GraphItem(Context context, Serializable extra) {
		super(context, extra);
	}

	@Override
	public View inflate() {
		return View.inflate(context, R.layout.main_item_graph, null);
	}

	@Override
	public void fill(View view) {
	}
}

package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.adapter.item.AbstractItem;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class HeterogeneousArrayAdapter extends ArrayAdapter<AbstractItem> {
	private ArrayList<Integer> types;

	public HeterogeneousArrayAdapter(Context context, List<AbstractItem> objects) {
		super(context, 0, objects);
		types = new ArrayList<Integer>();
		for (AbstractItem abstractItem : objects)
			types.add(abstractItem.getClass().hashCode());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AbstractItem rowModel = getItem(position);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	@Override
	public int getItemViewType(int position) {
		return types.indexOf(getItem(position).getClass().hashCode());
	}

	@Override
	public int getViewTypeCount() {
		return types.size();
	}
}

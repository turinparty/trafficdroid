package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.item.AbstractItem;
import it.localhost.trafficdroid.adapter.item.BadNewsDialogItem;
import it.localhost.trafficdroid.adapter.item.BannerDialogItem;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.StreetDTO;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class BadNewsDialogAdapter extends ArrayAdapter<BadNewsDTO> {
	private ArrayList<AbstractItem> items;

	public BadNewsDialogAdapter(Context context, StreetDTO street) {
		super(context, 0);
		items = new ArrayList<AbstractItem>();
		for (BadNewsDTO badNews : street.getBadNews()) {
			if (Math.random() < 0.1)
				items.add(new BannerDialogItem(context, R.layout.iab_mrect));
			items.add(new BadNewsDialogItem(context, badNews));
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		AbstractItem rowModel = items.get(position);
		if (convertView == null)
			convertView = rowModel.inflateView();
		rowModel.fillView(convertView);
		return convertView;
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public int getItemViewType(int position) {
		return items.get(position).getType();
	}

	@Override
	public int getViewTypeCount() {
		return AbstractItem.itemTypes.length;
	}
}

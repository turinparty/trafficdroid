package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.BadNewsDTO;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BadNewsAdapter extends ArrayAdapter<BadNewsDTO> {
	private LayoutInflater inflater;

	public BadNewsAdapter(Context context, int textViewResourceId, ArrayList<BadNewsDTO> objects) {
		super(context, textViewResourceId, objects);
		inflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BadNewsDTO event = getItem(position);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.dialog_item_badnews, null);
		((TextView) convertView.findViewById(R.id.BNDText)).setText(event.getTitle() + " " + event.getDescription());
		return convertView;
	}
}

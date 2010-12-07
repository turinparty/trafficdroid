package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ZoneListAdapter extends ArrayAdapter<ZoneDTO> {
	private List<ZoneDTO> zones;
	private LayoutInflater layoutInflater;

	public ZoneListAdapter(Context context, List<ZoneDTO> zones) {
		super(context, R.layout.zone, zones);
		this.zones = zones;
		this.layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = layoutInflater.inflate(R.layout.zone, parent, false);
		ZoneDTO zona = zones.get(position);
		TextView centerTextView = (TextView) convertView.findViewById(R.id.zoneName);
		TextView leftTextView = (TextView) convertView.findViewById(R.id.speedLeft);
		TextView rightTextView = (TextView) convertView.findViewById(R.id.speedRight);
		centerTextView.setText(zona.getName());
		leftTextView.setText(zona.getSpeedLeft());
		rightTextView.setText(zona.getSpeedRight());
		leftTextView.setTextColor(Const.colorCat[zona.getCatLeft()]);
		rightTextView.setTextColor(Const.colorCat[zona.getCatRight()]);
		leftTextView.setTypeface((zona.getCatLeft() == 1) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		rightTextView.setTypeface((zona.getCatRight() == 1) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		return convertView;
	}
}
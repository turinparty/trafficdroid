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
	private List<ZoneDTO> zonesDTO;
	private LayoutInflater inflater;
	private TextView centerTextView;
	private TextView leftTextView;
	private TextView rightTextView;
	private ZoneDTO zona;

	public ZoneListAdapter(Context context, List<ZoneDTO> objects) {
		super(context, R.layout.zone, objects);
		this.zonesDTO = objects;
		this.inflater = LayoutInflater.from(context);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null)
			convertView = inflater.inflate(R.layout.zone, parent, false);
		zona = zonesDTO.get(position);
		centerTextView = (TextView) convertView.findViewById(R.id.zoneName);
		leftTextView = (TextView) convertView.findViewById(R.id.speedLeft);
		rightTextView = (TextView) convertView.findViewById(R.id.speedRight);
		centerTextView.setText(zona.getName());
		leftTextView.setText(zona.getSpeedLeft());
		leftTextView.setTextColor(Const.colorCat[zona.getCatLeft()]);
		leftTextView.setTypeface((zona.getCatLeft() == 1) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		rightTextView.setText(zona.getSpeedRight());
		rightTextView.setTypeface(Typeface.DEFAULT);
		rightTextView.setTextColor(Const.colorCat[zona.getCatRight()]);
		rightTextView.setTypeface((zona.getCatRight() == 1) ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		return convertView;
	}
}
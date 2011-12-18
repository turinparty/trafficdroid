package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.BadNewsDTO;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.dialog_item_badnews, null);
			convertView.setTag(R.id.badNewsTitle, convertView.findViewById(R.id.badNewsTitle));
			convertView.setTag(R.id.badNewsImage, convertView.findViewById(R.id.badNewsImage));
			convertView.setTag(R.id.badNewsDate, convertView.findViewById(R.id.badNewsDate));
			convertView.setTag(R.id.badNewsDescription, convertView.findViewById(R.id.badNewsDescription));
		}
		((TextView) convertView.getTag(R.id.badNewsDate)).setText(Const.sdfBnFormat.format(event.getDate()));
		((TextView) convertView.getTag(R.id.badNewsTitle)).setText(event.getTitle());
		((TextView) convertView.getTag(R.id.badNewsDescription)).setText(event.getDescription());
		ImageView badNewsImage = (ImageView) convertView.getTag(R.id.badNewsImage);
		if (event.getTitle().contains(Const.bn_acc))
			badNewsImage.setImageResource(R.drawable.bn_acc);
		else if (event.getTitle().contains(Const.bn_anh))
			badNewsImage.setImageResource(R.drawable.bn_anh);
		else if (event.getTitle().contains(Const.bn_los1) || event.getTitle().contains(Const.bn_los2))
			badNewsImage.setImageResource(R.drawable.bn_los);
		else if (event.getTitle().contains(Const.bn_pss))
			badNewsImage.setImageResource(R.drawable.bn_pss);
		else if (event.getTitle().contains(Const.bn_bkd))
			badNewsImage.setImageResource(R.drawable.bn_bkd);
		else if (event.getTitle().contains(Const.bn_fop))
			badNewsImage.setImageResource(R.drawable.bn_fop);
		else if (event.getTitle().contains(Const.bn_fod))
			badNewsImage.setImageResource(R.drawable.bn_fod);
		else if (event.getTitle().contains(Const.bn_ocm))
			badNewsImage.setImageResource(R.drawable.bn_ocm);
		else if (event.getTitle().contains(Const.bn_peo))
			badNewsImage.setImageResource(R.drawable.bn_peo);
		else if (event.getTitle().contains(Const.bn_pra))
			badNewsImage.setImageResource(R.drawable.bn_pra);
		else if (event.getTitle().contains(Const.bn_res))
			badNewsImage.setImageResource(R.drawable.bn_res);
		else if (event.getTitle().contains(Const.bn_sn))
			badNewsImage.setImageResource(R.drawable.bn_sn);
		else if (event.getTitle().contains(Const.bn_sm))
			badNewsImage.setImageResource(R.drawable.bn_sm);
		else if (event.getTitle().contains(Const.bn_rsr))
			badNewsImage.setImageResource(R.drawable.bn_rsr);
		else if (event.getTitle().contains(Const.bn_sab))
			badNewsImage.setImageResource(R.drawable.bn_sab);
		else if (event.getTitle().contains(Const.bn_sdc))
			badNewsImage.setImageResource(R.drawable.bn_sdc);
		else if (event.getTitle().contains(Const.bn_spc))
			badNewsImage.setImageResource(R.drawable.bn_spc);
		else if (event.getTitle().contains(Const.bn_win))
			badNewsImage.setImageResource(R.drawable.bn_win);
		else
			badNewsImage.setImageResource(android.R.drawable.ic_dialog_alert);
		return convertView;
	}
}
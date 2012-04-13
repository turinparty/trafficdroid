package it.localhost.trafficdroid.adapter;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.BadNewsDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BadNewsAdapter extends ArrayAdapter<BadNewsDTO> {
	private static final SimpleDateFormat sdfBnFormat = new SimpleDateFormat("HH:mm");
	private static final String bn_acc = "incidente";
	private static final String bn_anh = "animali";
	private static final String bn_bkd = "veicolo fermo o avaria";
	private static final String bn_fig = "incendio";
	private static final String bn_fod = "nebbia";
	private static final String bn_fop = "nebbia a banchi";
	private static final String bn_ibu = "nevischio";
	private static final String bn_los1 = "code";
	private static final String bn_los2 = "traffico";
	private static final String bn_ocm = "perdita di carico";
	private static final String bn_peo = "pedoni";
	private static final String bn_pra = "pioggia";
	private static final String bn_pss = "personale su strada";
	private static final String bn_res = "chius";
	private static final String bn_rsr = "riduzione di carreggiata";
	private static final String bn_sab = "area di servizio";
	private static final String bn_sdc = "scambio di carreggiata";
	private static final String bn_sm = "catene";
	private static final String bn_sn = "mezzi spargisale";
	private static final String bn_sne = "neve";
	private static final String bn_spc = "controllo velocità";
	private static final String bn_win = "vento forte";
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
		((TextView) convertView.getTag(R.id.badNewsTitle)).setText(event.getTitle());
		((TextView) convertView.getTag(R.id.badNewsDescription)).setText(event.getDescription());
		TextView badNewsDate = (TextView) convertView.getTag(R.id.badNewsDate);
		badNewsDate.setText(sdfBnFormat.format(event.getDate()));
		int drawable;
		if (event.getTitle().contains(bn_acc))
			drawable = R.drawable.bn_acc;
		else if (event.getTitle().contains(bn_anh))
			drawable = R.drawable.bn_anh;
		else if (event.getTitle().contains(bn_los1) || event.getTitle().contains(bn_los2))
			drawable = R.drawable.bn_los;
		else if (event.getTitle().contains(bn_pss))
			drawable = R.drawable.bn_pss;
		else if (event.getTitle().contains(bn_bkd))
			drawable = R.drawable.bn_bkd;
		else if (event.getTitle().contains(bn_fop))
			drawable = R.drawable.bn_fop;
		else if (event.getTitle().contains(bn_ibu))
			drawable = R.drawable.bn_ibu;
		else if (event.getTitle().contains(bn_fig))
			drawable = R.drawable.bn_fig;
		else if (event.getTitle().contains(bn_fod))
			drawable = R.drawable.bn_fod;
		else if (event.getTitle().contains(bn_ocm))
			drawable = R.drawable.bn_ocm;
		else if (event.getTitle().contains(bn_peo))
			drawable = R.drawable.bn_peo;
		else if (event.getTitle().contains(bn_pra))
			drawable = R.drawable.bn_pra;
		else if (event.getTitle().contains(bn_res))
			drawable = R.drawable.bn_res;
		else if (event.getTitle().contains(bn_sn))
			drawable = R.drawable.bn_sn;
		else if (event.getTitle().contains(bn_sne))
			drawable = R.drawable.bn_sne;
		else if (event.getTitle().contains(bn_sm))
			drawable = R.drawable.bn_sm;
		else if (event.getTitle().contains(bn_rsr))
			drawable = R.drawable.bn_rsr;
		else if (event.getTitle().contains(bn_sab))
			drawable = R.drawable.bn_sab;
		else if (event.getTitle().contains(bn_sdc))
			drawable = R.drawable.bn_sdc;
		else if (event.getTitle().contains(bn_spc))
			drawable = R.drawable.bn_spc;
		else if (event.getTitle().contains(bn_win))
			drawable = R.drawable.bn_win;
		else
			drawable = android.R.drawable.ic_dialog_alert;
		badNewsDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawable);
		return convertView;
	}
}
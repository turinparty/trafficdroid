package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.BadNewsDTO;

import java.text.DateFormat;

import localhost.toolkit.widget.HeterogeneousItem;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class BadNewsDialogItem extends HeterogeneousItem {
	private static final String bn_acc = "incidente";
	private static final String bn_anh = "animali";
	private static final String bn_bkd = "veicolo fermo";
	private static final String bn_emv = "colonnine SOS";
	private static final String bn_fig = "incendio";
	private static final String bn_fod = "nebbia";
	private static final String bn_fld = "straripamenti";
	private static final String bn_fop = "nebbia a banchi";
	private static final String bn_ibu = "nevischio";
	private static final String bn_los1 = "code";
	private static final String bn_los2 = "traffico";
	private static final String bn_ocm = "perdita di carico";
	private static final String bn_peo = "pedoni";
	private static final String bn_pra1 = "pioggia";
	private static final String bn_pra2 = "temporale";
	private static final String bn_pss = "personale su strada";
	private static final String bn_res = "chius";
	private static final String bn_rsr = "riduzione di carreggiata";
	private static final String bn_sab = "area di servizio";
	private static final String bn_sat = "senso unico alternato";
	private static final String bn_sdc = "scambio di carreggiata";
	private static final String bn_sm = "catene";
	private static final String bn_sn = "mezzi spargisale";
	private static final String bn_sne1 = "neve";
	private static final String bn_sne2 = "grandine";
	private static final String bn_spc = "controllo velocità";
	private static final String bn_vfr = "veicolo in fiamme";
	private static final String bn_win = "vento";
	private static final String bn_cls = "carburante chius";
	private static DateFormat sdfBnFormat;

	public BadNewsDialogItem(Context context, BadNewsDTO extra) {
		super(context, extra);
		sdfBnFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
	}

	@Override
	public View inflate() {
		View view = View.inflate(context, R.layout.dialog_item_badnews, null);
		view.setTag(R.id.badNewsTitle, view.findViewById(R.id.badNewsTitle));
		view.setTag(R.id.badNewsDate, view.findViewById(R.id.badNewsDate));
		view.setTag(R.id.badNewsDescription, view.findViewById(R.id.badNewsDescription));
		return view;
	}

	@Override
	public void fill(View view) {
		BadNewsDTO badNews = (BadNewsDTO) extra;
		((TextView) view.getTag(R.id.badNewsTitle)).setText(badNews.getTitle());
		((TextView) view.getTag(R.id.badNewsDescription)).setText(badNews.getDescription());
		TextView badNewsDate = (TextView) view.getTag(R.id.badNewsDate);
		badNewsDate.setText(sdfBnFormat.format(badNews.getDate()));
		int drawable;
		if (badNews.getTitle().contains(bn_los1) || badNews.getTitle().contains(bn_los2))
			drawable = R.drawable.bn_los;
		else if (badNews.getTitle().contains(bn_acc))
			drawable = R.drawable.bn_acc;
		else if (badNews.getTitle().contains(bn_anh))
			drawable = R.drawable.bn_anh;
		else if (badNews.getTitle().contains(bn_pss))
			drawable = R.drawable.bn_pss;
		else if (badNews.getTitle().contains(bn_bkd))
			drawable = R.drawable.bn_bkd;
		else if (badNews.getTitle().contains(bn_fop))
			drawable = R.drawable.bn_fop;
		else if (badNews.getTitle().contains(bn_ibu))
			drawable = R.drawable.bn_ibu;
		else if (badNews.getTitle().contains(bn_fig))
			drawable = R.drawable.bn_fig;
		else if (badNews.getTitle().contains(bn_emv))
			drawable = R.drawable.bn_emv;
		else if (badNews.getTitle().contains(bn_fld))
			drawable = R.drawable.bn_fld;
		else if (badNews.getTitle().contains(bn_fod))
			drawable = R.drawable.bn_fod;
		else if (badNews.getTitle().contains(bn_ocm))
			drawable = R.drawable.bn_ocm;
		else if (badNews.getTitle().contains(bn_peo))
			drawable = R.drawable.bn_peo;
		else if (badNews.getTitle().contains(bn_pra1) || badNews.getTitle().contains(bn_pra2))
			drawable = R.drawable.bn_pra;
		else if (badNews.getTitle().contains(bn_sn))
			drawable = R.drawable.bn_sn;
		else if (badNews.getTitle().contains(bn_sne1) || badNews.getTitle().contains(bn_sne2))
			drawable = R.drawable.bn_sne;
		else if (badNews.getTitle().contains(bn_sm))
			drawable = R.drawable.bn_sm;
		else if (badNews.getTitle().contains(bn_rsr))
			drawable = R.drawable.bn_rsr;
		else if (badNews.getTitle().contains(bn_sab))
			drawable = R.drawable.bn_sab;
		else if (badNews.getTitle().contains(bn_sat))
			drawable = R.drawable.bn_sat;
		else if (badNews.getTitle().contains(bn_sdc))
			drawable = R.drawable.bn_sdc;
		else if (badNews.getTitle().contains(bn_spc))
			drawable = R.drawable.bn_spc;
		else if (badNews.getTitle().contains(bn_win))
			drawable = R.drawable.bn_win;
		else if (badNews.getTitle().contains(bn_vfr))
			drawable = R.drawable.bn_vfr;
		else if (badNews.getTitle().contains(bn_cls))
			drawable = R.drawable.bn_cls;
		else if (badNews.getTitle().contains(bn_res))
			drawable = R.drawable.bn_res;
		else
			drawable = android.R.drawable.ic_dialog_alert;
		badNewsDate.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawable);
	}
}
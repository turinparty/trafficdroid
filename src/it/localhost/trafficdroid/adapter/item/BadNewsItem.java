package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.BadNewsAdapter;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdAnalytics;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class BadNewsItem extends AbstractChildItem {
	static final String badNewsLabel = "Bad News: ";
	private StreetDTO streetDTO;

	public BadNewsItem(Context context, StreetDTO streetDTO) {
		super(context);
		this.streetDTO = streetDTO;
	}

	public int getType() {
		return Const.itemTypes[0];
	}

	public View inflateView() {
		View view = inflater.inflate(R.layout.main_item_badnews, null, false);
		view.setTag(R.id.streetDirLeft, view.findViewById(R.id.streetDirLeft));
		view.setTag(R.id.streetDirRight, view.findViewById(R.id.streetDirRight));
		view.setTag(R.id.badNews, view.findViewById(R.id.badNews));
		return view;
	}

	public void fillView(View view) {
		view.setTag(R.id.zoneType, getType());
		((TextView) view.getTag(R.id.streetDirLeft)).setText(streetDTO.getDirectionLeft());
		((TextView) view.getTag(R.id.streetDirRight)).setText(streetDTO.getDirectionRight());
		TextView badNews = (TextView) view.getTag(R.id.badNews);
		if (streetDTO.getBadNews().size() != 0) {
			badNews.setText(badNewsLabel + streetDTO.getBadNews().size());
			badNews.setVisibility(View.VISIBLE);
		} else
			badNews.setVisibility(View.INVISIBLE);
	}

	public void onClick() {
		if (streetDTO.getBadNews().size() != 0) {
			TdAnalytics.trackEvent(TdAnalytics.eventCatBadNews, TdAnalytics.eventActionOpen, streetDTO.getName(), 0);
			Dialog dialog = new Dialog(context);
			dialog.setTitle(streetDTO.getName());
			ListView listview = (ListView) LayoutInflater.from(context).inflate(R.layout.dialog_badnews, null);
			listview.setAdapter(new BadNewsAdapter(context, R.layout.dialog_item_badnews, streetDTO.getBadNews()));
			dialog.setContentView(listview);
			dialog.show();
		}
	}
}
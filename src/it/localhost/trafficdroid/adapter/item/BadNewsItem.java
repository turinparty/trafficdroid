package it.localhost.trafficdroid.adapter.item;

import com.google.analytics.tracking.android.EasyTracker;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.AbstractActivity;
import it.localhost.trafficdroid.adapter.BadNewsDialogAdapter;
import it.localhost.trafficdroid.common.ViewTagger;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class BadNewsItem extends AbstractItem {
	static final String badNewsLabel = "Bad News: ";
	private StreetDTO streetDTO;

	public BadNewsItem(Context context, StreetDTO streetDTO) {
		super(context);
		this.streetDTO = streetDTO;
	}

	public int getType() {
		return itemTypes[1];
	}

	public View inflateView() {
		View view = inflater.inflate(R.layout.main_item_badnews, null, false);
		ViewTagger.setTag(view, R.id.streetDirLeft, view.findViewById(R.id.streetDirLeft));
		ViewTagger.setTag(view, R.id.streetDirRight, view.findViewById(R.id.streetDirRight));
		ViewTagger.setTag(view, R.id.badNews, view.findViewById(R.id.badNews));
		return view;
	}

	public void fillView(View view) {
		ViewTagger.setTag(view, R.id.zoneType, getType());
		((TextView) ViewTagger.getTag(view, R.id.streetDirLeft)).setText(streetDTO.getDirectionLeft());
		((TextView) ViewTagger.getTag(view, R.id.streetDirRight)).setText(streetDTO.getDirectionRight());
		TextView badNews = (TextView) ViewTagger.getTag(view, R.id.badNews);
		if (streetDTO.getBadNews().size() != 0) {
			badNews.setText(badNewsLabel + streetDTO.getBadNews().size());
			badNews.setVisibility(View.VISIBLE);
		} else
			badNews.setVisibility(View.INVISIBLE);
	}

	public void onClick() {
		if (streetDTO.getBadNews().size() != 0) {
			EasyTracker.getTracker().trackEvent(AbstractActivity.eventCatBadNews, AbstractActivity.eventActionOpen, streetDTO.getName(), (long) 0);
			Dialog dialog = new Dialog(context);
			dialog.setTitle(streetDTO.getName());
			ListView listview = (ListView) LayoutInflater.from(context).inflate(R.layout.dialog_badnews, null);
			listview.setAdapter(new BadNewsDialogAdapter(context, streetDTO));
			dialog.setContentView(listview);
			dialog.show();
		}
	}
}
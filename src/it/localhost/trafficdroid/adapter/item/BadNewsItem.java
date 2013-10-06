package it.localhost.trafficdroid.adapter.item;

import com.google.analytics.tracking.android.EasyTracker;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.AbstractActivity;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.fragment.BadnewsDialogFragment;
import android.app.Fragment;
import android.view.View;
import android.widget.TextView;

public class BadNewsItem extends AbstractItem {
	static final String badNewsLabel = "Bad News: ";
	private StreetDTO streetDTO;

	public BadNewsItem(Fragment fragment, StreetDTO streetDTO) {
		super(fragment);
		this.streetDTO = streetDTO;
	}

	public int getType() {
		return itemTypes[1];
	}

	public View inflateView() {
		View view = View.inflate(fragment.getActivity(), R.layout.main_item_badnews, null);
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
			EasyTracker.getTracker().sendEvent(AbstractActivity.EVENT_CAT_BADNEWS, AbstractActivity.EVENT_ACTION_OPEN, streetDTO.getName(), (long) 0);
			new BadnewsDialogFragment().show(fragment.getFragmentManager(), streetDTO);
		}
	}
}
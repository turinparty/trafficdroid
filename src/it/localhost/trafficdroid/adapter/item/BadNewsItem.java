package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.fragment.BadnewsDialogFragment;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;

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
		((ImageView) view.getTag(R.id.streetDirLeft)).setImageResource(streetDTO.getDirectionLeft());
		((ImageView) view.getTag(R.id.streetDirRight)).setImageResource(streetDTO.getDirectionRight());
		TextView badNews = (TextView) view.getTag(R.id.badNews);
		if (streetDTO.getBadNews().size() != 0) {
			badNews.setText(badNewsLabel + streetDTO.getBadNews().size());
			badNews.setVisibility(View.VISIBLE);
		} else
			badNews.setVisibility(View.INVISIBLE);
	}

	public void onClick() {
		if (streetDTO.getBadNews().size() != 0) {
			EasyTracker.getInstance(fragment.getActivity()).send(MapBuilder.createEvent(MainActivity.EVENT_CAT_BADNEWS, MainActivity.EVENT_ACTION_OPEN, streetDTO.getName(), (long) 0).build());
			new BadnewsDialogFragment().show(fragment.getFragmentManager(), streetDTO);
		}
	}
}
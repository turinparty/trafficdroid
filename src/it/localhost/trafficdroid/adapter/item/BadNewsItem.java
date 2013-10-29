package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BadNewsItem extends AbstractItem {
	static final String badNewsLabel = "Bad News: ";
	private StreetDTO streetDTO;

	public BadNewsItem(Fragment fragment, StreetDTO streetDTO) {
		super(fragment, streetDTO);
		this.streetDTO = streetDTO;
	}

	public View inflateView() {
		View view = View.inflate(fragment.getActivity(), R.layout.main_item_badnews, null);
		view.setTag(R.id.streetDirLeft, view.findViewById(R.id.streetDirLeft));
		view.setTag(R.id.streetDirRight, view.findViewById(R.id.streetDirRight));
		view.setTag(R.id.badNews, view.findViewById(R.id.badNews));
		return view;
	}

	public void fillView(View view) {
		((ImageView) view.getTag(R.id.streetDirLeft)).setImageResource(streetDTO.getDirectionLeft());
		((ImageView) view.getTag(R.id.streetDirRight)).setImageResource(streetDTO.getDirectionRight());
		TextView badNews = (TextView) view.getTag(R.id.badNews);
		if (streetDTO.getBadNews().size() != 0) {
			badNews.setText(badNewsLabel + streetDTO.getBadNews().size());
			badNews.setVisibility(View.VISIBLE);
		} else
			badNews.setVisibility(View.INVISIBLE);
	}
}
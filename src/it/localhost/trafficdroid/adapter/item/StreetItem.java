package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.ViewTagger;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class StreetItem extends AbstractItem {
	private StreetDTO streetDTO;

	public StreetItem(Context context, StreetDTO streetDTO) {
		super(context);
		this.streetDTO = streetDTO;
	}

	public View inflateView() {
		View view = inflater.inflate(android.R.layout.simple_expandable_list_item_2, null, false);
		ViewTagger.setTag(view, R.id.streetText1, view.findViewById(android.R.id.text1));
		ViewTagger.setTag(view, R.id.streetText2, view.findViewById(android.R.id.text2));
		return view;
	}

	public void fillView(View view) {
		ViewTagger.setTag(view, R.id.itemKey, Integer.toString(streetDTO.getId()));
		ViewTagger.setTag(view, R.id.itemName, streetDTO.getName());
		((TextView) ViewTagger.getTag(view, R.id.streetText1)).setText(streetDTO.getName());
		TextView streetText2 = (TextView) ViewTagger.getTag(view, R.id.streetText2);
		if (streetDTO.getBadNews().size() != 0) {
			streetText2.setText(BadNewsItem.badNewsLabel + streetDTO.getBadNews().size());
			streetText2.setVisibility(View.VISIBLE);
		} else
			streetText2.setVisibility(View.INVISIBLE);
	}
}
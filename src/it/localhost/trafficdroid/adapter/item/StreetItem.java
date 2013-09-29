package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
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

	@Override
	public int getType() {
		return itemTypes[3];
	}

	@Override
	public View inflateView() {
		View view = inflater.inflate(android.R.layout.simple_expandable_list_item_2, null, false);
		view.setTag(R.id.text1, view.findViewById(android.R.id.text1));
		view.setTag(R.id.text2, view.findViewById(android.R.id.text2));
		return view;
	}

	@Override
	public void fillView(View view) {
		view.setTag(R.id.itemKey, streetDTO.getId());
		view.setTag(R.id.itemName, streetDTO.getName());
		((TextView) view.getTag(R.id.text1)).setText(streetDTO.getTag() + " " + streetDTO.getName());
		TextView streetText2 = (TextView) view.getTag(R.id.text2);
		if (streetDTO.getBadNews().size() != 0) {
			streetText2.setText(BadNewsItem.badNewsLabel + streetDTO.getBadNews().size());
			streetText2.setVisibility(View.VISIBLE);
		} else {
			streetText2.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onClick() {
	}
}
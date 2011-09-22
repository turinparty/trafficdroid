package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.StreetDTO;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class StreetItem extends AbstractItem {
	public StreetDTO streetDTO;

	public StreetItem(Context context, StreetDTO streetDTO) {
		super(context);
		this.streetDTO = streetDTO;
	}

	public int getType() {
		return Const.itemTypes[0];
	}

	public View inflateView() {
		return inflater.inflate(android.R.layout.simple_expandable_list_item_2, null, false);
	}

	public void fillView(View view) {
		((TextView) view.findViewById(android.R.id.text1)).setText(streetDTO.getName());
		if (streetDTO.getBadNews().size() != 0) {
			((TextView) view.findViewById(android.R.id.text2)).setText(Const.badNews + streetDTO.getBadNews().size());
			((TextView) view.findViewById(android.R.id.text2)).setVisibility(View.VISIBLE);
		} else
			((TextView) view.findViewById(android.R.id.text2)).setVisibility(View.INVISIBLE);
	}
}
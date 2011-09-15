package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
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
		return inflater.inflate(R.layout.main_item_street, null, false);
	}

	public void fillView(View view) {
		((TextView) view.findViewById(R.id.streetName)).setText(streetDTO.getName());
		if (streetDTO.getBadNews().size() != 0) {
			((TextView) view.findViewById(R.id.BNTNumber)).setText(Integer.toString(streetDTO.getBadNews().size()));
			((TextView) view.findViewById(R.id.BNTNumber)).setVisibility(View.VISIBLE);
		} else
			((TextView) view.findViewById(R.id.BNTNumber)).setVisibility(View.INVISIBLE);
	}


}
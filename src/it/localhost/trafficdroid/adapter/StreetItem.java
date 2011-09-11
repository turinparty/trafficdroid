package it.localhost.trafficdroid.adapter;

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
		super.fillView(view);
		((TextView) view.findViewById(R.id.streetName)).setText(streetDTO.getName());
		((TextView) view.findViewById(R.id.streetDirLeft)).setText(streetDTO.getDirectionLeft());
		((TextView) view.findViewById(R.id.streetDirRight)).setText(streetDTO.getDirectionRight());
	}

	public void onClick() {
		//TODO comprimere righe
	}
}
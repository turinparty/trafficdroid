package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.adapter.BadNewsDialogAdapter;
import android.content.Context;
import android.view.View;

public class BannerDialogItem extends AbstractItem {
	public BannerDialogItem(Context context) {
		super(context);
	}

	public int getType() {
		return BadNewsDialogAdapter.itemTypes[1];
	}

	public View inflateView() {
		View view = inflater.inflate(R.layout.banner, null);
		return view;
	}

	public void fillView(View view) {
	}

	public void onClick() {
	}
}
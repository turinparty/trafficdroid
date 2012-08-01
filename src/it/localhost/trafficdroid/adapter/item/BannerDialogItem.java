package it.localhost.trafficdroid.adapter.item;

import android.content.Context;
import android.view.View;

public class BannerDialogItem extends AbstractItem {
	private int layout;

	public BannerDialogItem(Context context, int layout) {
		super(context);
		this.layout = layout;
	}

	public int getType() {
		return itemTypes[2];
	}

	public View inflateView() {
		View view = inflater.inflate(layout, null);
		return view;
	}

	public void fillView(View view) {
	}

	public void onClick() {
	}
}
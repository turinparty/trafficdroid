package it.localhost.trafficdroid.adapter.item;

import android.app.Fragment;
import android.view.View;

public class BannerDialogItem extends AbstractItem {
	private int layout;

	public BannerDialogItem(Fragment fragment, int layout) {
		super(fragment);
		this.layout = layout;
	}

	public int getType() {
		return itemTypes[2];
	}

	public View inflateView() {
		View v = View.inflate(fragment.getActivity(), layout, null);
		System.err.println(v);
		return v;
	}

	public void fillView(View view) {
	}

	public void onClick() {
	}
}
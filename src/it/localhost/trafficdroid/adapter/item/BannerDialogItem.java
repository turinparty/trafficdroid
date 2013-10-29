package it.localhost.trafficdroid.adapter.item;

import android.app.Fragment;
import android.view.View;

public class BannerDialogItem extends AbstractItem {
	private int layout;

	public BannerDialogItem(Fragment fragment, int layout) {
		super(fragment, layout);
		this.layout = layout;
	}

	public View inflateView() {
		return View.inflate(fragment.getActivity(), layout, null);
	}

	public void fillView(View view) {
	}
}
package it.localhost.trafficdroid.adapter.item;

import android.app.Fragment;
import android.view.View;

public abstract class AbstractItem {
	public static final byte[] itemTypes = new byte[] { 0, 1, 2, 3, 4, 5 };
	protected Fragment fragment;

	public abstract View inflateView();

	public abstract void fillView(View view);

	public abstract int getType();

	public abstract void onClick();

	AbstractItem(Fragment fragment) {
		this.fragment = fragment;
	}
}
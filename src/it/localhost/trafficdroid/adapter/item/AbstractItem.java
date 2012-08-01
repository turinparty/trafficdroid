package it.localhost.trafficdroid.adapter.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class AbstractItem {
	public static final byte[] itemTypes = new byte[] { 0, 1, 2, 3, 4 };
	protected Context context;
	protected LayoutInflater inflater;

	public abstract View inflateView();

	public abstract void fillView(View view);

	public abstract int getType();

	public abstract void onClick();

	AbstractItem(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
}
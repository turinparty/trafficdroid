package it.localhost.trafficdroid.adapter.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class AbstractItem {
	protected Context context;
	protected LayoutInflater inflater;

	public abstract int getType();

	public abstract View inflateView();

	public abstract void onClick();

	public abstract void fillView(View view);
	//	view.setTag(this);
	

	public AbstractItem(Context context) {
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
}
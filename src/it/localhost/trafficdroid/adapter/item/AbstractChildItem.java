package it.localhost.trafficdroid.adapter.item;

import android.content.Context;

public abstract class AbstractChildItem extends AbstractItem {
	public abstract void onClick();

	public abstract int getType();

	public AbstractChildItem(Context context) {
		super(context);
	}
}
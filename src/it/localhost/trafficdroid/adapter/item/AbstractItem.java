package it.localhost.trafficdroid.adapter.item;

import java.io.Serializable;

import android.app.Fragment;
import android.view.View;

public abstract class AbstractItem {
	protected Fragment fragment;
	protected Serializable extra;

	public abstract View inflateView();

	public abstract void fillView(View view);

	public AbstractItem(Fragment fragment, Serializable extra) {
		this.fragment = fragment;
		this.extra = extra;
	}

	public boolean onClick() {
		return ((OnAbstractItemClickListener) fragment).onAbstractItemClick(extra);
	}

	public interface OnAbstractItemClickListener {
		public boolean onAbstractItemClick(Serializable extra);
	}
}
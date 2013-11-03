package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import android.app.Fragment;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdViewItem extends AbstractItem {
	private int layout;
	private AdRequest adRequest;

	public AdViewItem(Fragment fragment, int layout) {
		super(fragment, layout);
		this.layout = layout;
		adRequest = new AdRequest.Builder().build();
	}

	public View inflateView() {
		return View.inflate(fragment.getActivity(), layout, null);
	}

	public void fillView(View view) {
		((AdView) view.findViewById(R.id.adView)).loadAd(adRequest);
	}
}
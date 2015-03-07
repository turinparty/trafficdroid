package it.localhost.trafficdroid.ethereogeneousItem;

import it.localhost.trafficdroid.R;
import localhost.toolkit.widget.HeterogeneousItem;
import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AdViewItem extends HeterogeneousItem {
	private AdRequest adRequest;

	public AdViewItem(Context context, int extra) {
		super(context, extra);
		adRequest = new AdRequest.Builder().build();
	}

	@Override
	public View inflate() {
		return View.inflate(context, (Integer) extra, null);
	}

	@Override
	public void fill(View view) {
		((AdView) view.findViewById(R.id.adView)).loadAd(adRequest);
	}
}
package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.R;
import android.app.Activity;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;

public class InterstitialAd implements AdListener {
	public InterstitialAd(Activity act) {
		if (!Utility.isInterstitialFree(act) && Math.random() < 0.5) {
			com.google.ads.InterstitialAd interstitial = new com.google.ads.InterstitialAd(act, act.getString(R.string.adUnitId));
			interstitial.setAdListener(this);
			interstitial.loadAd(new AdRequest());
		}
	}

	@Override
	public void onDismissScreen(Ad ad) {
	}

	@Override
	public void onFailedToReceiveAd(Ad ad, ErrorCode errorCode) {
	}

	@Override
	public void onLeaveApplication(Ad ad) {
	}

	@Override
	public void onPresentScreen(Ad ad) {
	}

	@Override
	public void onReceiveAd(Ad ad) {
		if (ad instanceof com.google.ads.InterstitialAd)
			((com.google.ads.InterstitialAd) ad).show();
	}
}

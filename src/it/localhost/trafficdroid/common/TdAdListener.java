package it.localhost.trafficdroid.common;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.InterstitialAd;
import com.google.ads.AdRequest.ErrorCode;

public class TdAdListener implements AdListener {
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
		if (ad instanceof InterstitialAd)
			((InterstitialAd) ad).show();
	}
}

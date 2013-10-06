package it.localhost.trafficdroid.tabFragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.TdAdListener;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.AnasTvService;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.ads.AdRequest;
import com.google.ads.InterstitialAd;

public class VideoFragment extends Fragment implements TabListener {
	private static final String MP4 = ".mp4";
	private static final String URL = "http://www.stradeanas.tv/video/news/";
	private VideoView videoView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		videoView = (VideoView) inflater.inflate(R.layout.video, null);
		MediaController mediaController = new MediaController(getActivity());
		mediaController.setAnchorView(videoView);
		videoView.setMediaController(mediaController);
		new AnasNewsAsyncTask().execute();
		if (!Utility.isInterstitialFree(getActivity())) {
			InterstitialAd interstitial = new InterstitialAd(getActivity(), getString(R.string.adUnitId));
			interstitial.setAdListener(new TdAdListener());
			interstitial.loadAd(new AdRequest());
		}
		return videoView;
	}

	@Override
	public void onResume() {
		super.onResume();
		videoView.start();
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(android.R.id.content, this);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	private class AnasNewsAsyncTask extends AnasTvService {
		@Override
		protected void onPostExecute(final String[] result) {
			super.onPostExecute(result);
			videoView.setVideoPath(URL + result[0] + MP4);
			videoView.start();
		}
	}
}

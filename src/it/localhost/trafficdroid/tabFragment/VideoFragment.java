package it.localhost.trafficdroid.tabFragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.AdManager;
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

import com.google.android.gms.ads.AdView;

public class VideoFragment extends Fragment implements TabListener {
	private static final String MP4 = ".mp4";
	private static final String URL = "http://www.stradeanas.tv/video/news/";
	private VideoView v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = (VideoView) inflater.inflate(R.layout.video, container, false);
		MediaController mediaController = new MediaController(getActivity());
		mediaController.setAnchorView(v);
		v.setMediaController(mediaController);
		new AnasNewsAsyncTask().execute();
		((MainActivity) getActivity()).sendScreenName(VideoFragment.class.getSimpleName());
		new AdManager().load(getActivity(), ((AdView) v.findViewById(R.id.adView)), true);
		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		v.start();
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
			v.setVideoPath(URL + result[0] + MP4);
			v.start();
		}
	}
}

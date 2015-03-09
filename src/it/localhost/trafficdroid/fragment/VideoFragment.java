package it.localhost.trafficdroid.fragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.AdManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.gms.ads.AdView;

public class VideoFragment extends Fragment {
	private static final String PATH = "http://www.stradeanas.tv/video/news/%s_italia.mp4";
	private static final String PATTERN = "dd_MM_y";
	private VideoView v;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = (VideoView) inflater.inflate(R.layout.video, container, false);
		MediaController mediaController = new MediaController(getActivity());
		mediaController.setAnchorView(v);
		v.setMediaController(mediaController);
		v.setVideoPath(String.format(PATH, new SimpleDateFormat(PATTERN, Locale.getDefault()).format(new Date())));
		v.start();
		getActivity().getActionBar().setSubtitle(R.string.update);
		v.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				getActivity().getActionBar().setSubtitle(null);
			}
		});
		((MainActivity) getActivity()).setScreenName(1);
		new AdManager().load(getActivity(), ((AdView) v.findViewById(R.id.adView)), true);
		return v;
	}
}

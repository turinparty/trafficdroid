package it.localhost.trafficdroid.fragment;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dao.AnasTvService;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoFragment extends Fragment implements OnClickListener {
	private static final String MP4 = ".mp4";
	private static final String URL = "http://www.stradeanas.tv/video/news/";
	private static final String AFTER = " - ";
	private static final String BEFORE = "_";
	private String[] anastv;
	private VideoView videoView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.video, null);
		videoView = (VideoView) v.findViewById(R.id.videoView);
		new AnasNewsAsyncTask().execute();
		return v;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		MediaController mediaController = new MediaController(getActivity());
		mediaController.setAnchorView(videoView);
		videoView.setMediaController(mediaController);
		videoView.setVideoPath(URL + anastv[which] + MP4);
		videoView.start();
	}

	private class AnasNewsAsyncTask extends AnasTvService {
		@Override
		protected void onPostExecute(final String[] result) {
			super.onPostExecute(result);
			anastv = result;
			String[] item = new String[result.length];
			for (int i = 0; i < result.length; i++)
				item[i] = result[i].replaceAll(BEFORE, AFTER);
			new ListDialogFragment().show(getFragmentManager(), R.string.anasTv, item, VideoFragment.this);
		}
	}
}

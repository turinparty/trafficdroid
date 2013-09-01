package it.localhost.trafficdroid.activity;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dao.AnasTvService;
import it.localhost.trafficdroid.fragment.ListDialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AbstractActivity implements OnClickListener { // NO_UCD
	private static final String MP4 = ".mp4";
	private static final String URL = "http://www.stradeanas.tv/video/news/";
	private static final String AFTER = " - ";
	private static final String BEFORE = "_";
	private String[] anastv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		new AnasNewsAsyncTask().execute();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		VideoView videoView = (VideoView) findViewById(R.id.videoView);
		MediaController mediaController = new MediaController(this);
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
			new ListDialogFragment().show(getFragmentManager(), R.string.anasTv, item);
		}
	}
}
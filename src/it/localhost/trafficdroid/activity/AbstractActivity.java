package it.localhost.trafficdroid.activity;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;
import android.os.Bundle;

public abstract class AbstractActivity extends Activity {
	public static final String eventCatWebcam = "Webcam";
	public static final String eventCatBadNews = "BadNews";
	public static final String eventCatGraph = "Graph";
	public static final String eventActionRequest = "Request";
	public static final String eventActionOpen = "Open";
	public static final String eventActionNone = "None";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}
}
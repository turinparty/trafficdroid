package it.localhost.trafficdroid.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class TdAnalytics {
	private static final String ID = "UA-12243941-5";
	public static final String eventCatWebcam = "Webcam";
	public static final String eventCatBadNews = "BadNews";
	public static final String eventCatGraph = "Graph";
	public static final String eventActionRequest = "Request";
	public static final String eventActionOpen = "Open";
	public static final String eventActionNone = "None";
	private static GoogleAnalyticsTracker tracker = GoogleAnalyticsTracker.getInstance();
	private static ExecutorService executor = Executors.newSingleThreadExecutor();

	public static void startNewSession() {
		executor.submit(new Runnable() {
			public void run() {
				tracker.startNewSession(ID, TdApp.getContext());
			}
		});
	}

	public static void trackPageView(final String page) {
		executor.submit(new Runnable() {
			public void run() {
				tracker.trackPageView(page);
			}
		});
	}

	public static void dispatch() {
		executor.submit(new Runnable() {
			public void run() {
				tracker.dispatch();
			}
		});
	}

	public static void stopSession() {
		executor.submit(new Runnable() {
			public void run() {
				tracker.stopSession();
			}
		});
	}

	public static void trackEvent(final String category, final String action, final String label, final int value) {
		executor.submit(new Runnable() {
			public void run() {
				tracker.trackEvent(category, action, label, value);
			}
		});
	}
}

package it.localhost.trafficdroid.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class TdAnalytics {
	private static GoogleAnalyticsTracker tracker = GoogleAnalyticsTracker.getInstance();
	private static ExecutorService executor = Executors.newSingleThreadExecutor();

	public static void startNewSession(final String arg0) {
		executor.submit(new Runnable() {
			public void run() {
				tracker.startNewSession(arg0, TdApp.getContext());
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

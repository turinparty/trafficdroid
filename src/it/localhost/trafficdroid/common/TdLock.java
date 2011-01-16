package it.localhost.trafficdroid.common;

import android.content.Context;
import android.os.PowerManager;

public class TdLock {
	private static PowerManager.WakeLock lockStatic = null;

	synchronized public static PowerManager.WakeLock getLock(Context context) {
		if (lockStatic == null) {
			lockStatic = ((PowerManager) context.getSystemService(Context.POWER_SERVICE)).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, Const.wakeLock);
			lockStatic.setReferenceCounted(true);
		}
		return lockStatic;
	}
}
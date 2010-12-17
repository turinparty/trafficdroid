package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.preference.PreferenceManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class CookieDAO {
	public static void setCookie(Context context) throws TdException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		String url = PreferenceManager.getDefaultSharedPreferences(context).getString(context.getResources().getString(R.string.providerCamKey), Const.emptyString);
		try {
			httpclient.execute(new HttpGet(Const.http + url + Const.cookie));
		} catch (ClientProtocolException e) {
			throw new TdException(TdException.ClientProtocolException, e.getMessage());
		} catch (IOException e) {
			throw new TdException(TdException.IOException, e.getMessage());
		}
		List<Cookie> cookies = httpclient.getCookieStore().getCookies();
		httpclient.getConnectionManager().shutdown();
		try {
			Cookie sessionCookie = cookies.get(0);
			CookieSyncManager.createInstance(context);
			CookieManager.getInstance().setCookie(sessionCookie.getDomain(), sessionCookie.getName() + Const.equal + sessionCookie.getValue() + Const.domain + sessionCookie.getDomain());
			CookieSyncManager.getInstance().sync();
		} catch (IndexOutOfBoundsException e) {
			throw new TdException(TdException.IndexOutOfBoundsException, e.getMessage());
		}
	}
}
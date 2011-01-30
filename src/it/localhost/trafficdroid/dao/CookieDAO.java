package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

public class CookieDAO {
	public static void setCookie(Context context, String url) throws TdException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			httpclient.execute(new HttpGet(Const.http + url + Const.cookie));
		} catch (ClientProtocolException e) {
			throw new TdException(TdException.ClientProtocolException, e.getMessage());
		} catch (IOException e) {
			throw new TdException(TdException.IOException, e.getMessage());
		}
	
		Cookie sessionCookie = httpclient.getCookieStore().getCookies().get(0);
		httpclient.getConnectionManager().shutdown();
		CookieSyncManager.createInstance(context);
		CookieManager.getInstance().setCookie(sessionCookie.getDomain(), sessionCookie.getName() + Const.equal + sessionCookie.getValue() + Const.domain + sessionCookie.getDomain());
		CookieSyncManager.getInstance().sync();
	}
}
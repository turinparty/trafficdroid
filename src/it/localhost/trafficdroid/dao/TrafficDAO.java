package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.xml.sax.InputSource;

public class TrafficDAO {
	public static InputSource getData(int mapId, String baseUrl) throws GenericException, BadConfException,
			ConnectionException {
		InputSource doc = null;
//		try {
			int numTries = 3;
			while (true) {
				try {
					URL url = new URL(Const.http + baseUrl + Const.slash + mapId + Const.xml);
					doc = new InputSource(url.openStream());
				} catch (MalformedURLException e) {
					throw new BadConfException(e);
				} catch (IOException e) {
					if (--numTries == 0)
						throw new ConnectionException(e);
				}
				break;
			}
//		} catch (NullPointerException e) {
//			throw new BadConfException(e);
//		}
		return doc;
	}
}

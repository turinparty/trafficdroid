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
	public static InputSource getData(int mapId, String baseUrl) throws GenericException, BadConfException, ConnectionException {
		InputSource inputSource = null;
		int numTries = 3;
		while (true) {
			try {
				inputSource = new InputSource(new URL(Const.http + baseUrl + Const.slash + mapId + Const.xml).openStream());
			} catch (MalformedURLException e) {
				throw new BadConfException(e);
			} catch (IOException e) {
				if (--numTries == 0)
					throw new ConnectionException(e);
			}
			break;
		}
		return inputSource;
	}
}

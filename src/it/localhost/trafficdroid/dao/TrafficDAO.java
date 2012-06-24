package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.activity.WebViewActivity;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TrafficDAO {
	private static final String path = "/engine/traffic_server.php?type=4&sq=1&roa=";
	private static final String traffic = "traffic.";

	public static Document getData(int mapId, String baseUrl) throws GenericException, BadConfException, ConnectionException {
		Document doc = null;
		try {
			int numTries = 3;
			while (true) {
				try {
					doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(WebViewActivity.http + traffic + baseUrl + path + mapId);
					break;
				} catch (SAXException e) {
					if (--numTries == 0)
						throw new ConnectionException(e);
				}
			}
		} catch (IOException e) {
			throw new BadConfException(e);
		} catch (NullPointerException e) {
			throw new BadConfException(e);
		} catch (ParserConfigurationException e) {
			throw new GenericException(e);
		}
		return doc;
	}
}
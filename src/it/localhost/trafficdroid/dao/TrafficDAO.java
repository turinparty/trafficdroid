package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TrafficDAO {
	public static Document getData(int mapId, String url) throws GenericException, BadConfException, ConnectionException {
		Document doc = null;
		try {
			int numTries = 3;
			while (true) {
				try {
					doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Const.http + url + Const.dyn + mapId + Const.html);
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

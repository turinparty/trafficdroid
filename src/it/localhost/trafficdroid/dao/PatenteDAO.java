package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class PatenteDAO {
	private static final String PATENTE_URL = "https://www.ilportaledellautomobilista.it/http://voas.ilportaledellautomobilista.it:7777/PortaleFacade/SmartphonePuntiPatente?invoke=saldo&param0=";

	public static Document getData(String usr, String pwd) throws GenericException, BadConfException, ConnectionException {
		Document doc = null;
		try {
			int numTries = 3;
			while (true) {
				try {
					doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(PATENTE_URL + usr + "%23" + pwd + "%23" + Integer.valueOf((int) Math.pow(usr.length() + pwd.length(), 2D) % 93) + "%2325");
					break;
				} catch (SAXException e) {
					if (--numTries == 0)
						throw new ConnectionException(e);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new BadConfException(e);
		} catch (ParserConfigurationException e) {
			throw new GenericException(e);
		}
		return doc;
	}
}
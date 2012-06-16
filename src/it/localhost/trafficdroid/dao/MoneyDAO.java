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

public class MoneyDAO {
	private static final String path = "/autostrade/ricercaPercorso.do?tipo=G&dtxpDa=";
	private static final String arg = "&dtxpA=";

	public static Document getData(int da, int a, String baseUrl) throws GenericException, BadConfException, ConnectionException {
		Document doc = null;
		try {
			int numTries = 3;
			while (true) {
				try {
					System.err.println(Const.http + baseUrl + path + da + arg + a);
					doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Const.http + baseUrl + path + da + arg + a);
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
package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.exception.TdException;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TrafficDAO {
	public static Document getData(int mapId, String url) throws TdException {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Const.http + url + Const.dyn + mapId + Const.html);
		} catch (MalformedURLException e) {
			throw new TdException(TdException.MalformedURLException, e.getMessage());
		} catch (SAXException e) {
			throw new TdException(TdException.SAXException, e.getMessage());
		} catch (IOException e) {
			throw new TdException(TdException.IOException, e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new TdException(TdException.ParserConfigurationException, e.getMessage());
		} catch (FactoryConfigurationError e) {
			throw new TdException(TdException.FactoryConfigurationError, e.getMessage());
		}
	}
}

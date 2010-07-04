package com.google.code.trafficdroid.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.code.trafficdroid.exception.DaoException;

public class ContentDAO {
	public static final String url = "http://traffico.octotelematics.com/dyn/";
	public static final String page = ".html?ts=1";

	public static Document getContent(int mapId) throws DaoException {
		Document content = null;
		try {
			URL u = new URL(url + mapId + page);
			URLConnection uc = u.openConnection();
			content = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(uc.getInputStream());
		} catch (MalformedURLException e) {
			throw new DaoException(DaoException.MalformedURLException, e.getMessage());
		} catch (SAXException e) {
			throw new DaoException(DaoException.SAXException, e.getMessage());
		} catch (IOException e) {
			throw new DaoException(DaoException.IOException, e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new DaoException(DaoException.ParserConfigurationException, e.getMessage());
		} catch (FactoryConfigurationError e) {
			throw new DaoException(DaoException.FactoryConfigurationError, e.getMessage());
		}
		return content;
	}
}

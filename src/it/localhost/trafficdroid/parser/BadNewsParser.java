package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.dao.EventDAO;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class BadNewsParser extends DefaultHandler {
	private static final String BADNEWS_TITLE = "title";
	private static final String BADNEWS_DESCRIPTION = "description";
	private static final String BADNEWS_PUBDATE = "pubDate";
	private static final String item = "item";
	private static final SimpleDateFormat sdfBnParse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private static final String badNewsStreetDelim = "A -";
	private static final String badNewsDelim = "\n";
	private static final char charAutostrade = 'A';
	private MainDTO dto;
	private String url;
	private boolean inItem;
	private boolean badnewsDaAutostrada;
	private StringBuilder buf;
	private String xml_title;
	private String xml_description;
	private Date date;

	public BadNewsParser(MainDTO dto, String url) {
		this.dto = dto;
		this.url = url;
	}

	public void parse() throws GenericException, BadConfException, ConnectionException {
		try {
			XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			xmlReader.setContentHandler(this);
			InputSource inputSource = EventDAO.getData(url);
			xmlReader.parse(inputSource);
		} catch (ParserConfigurationException e) {
			throw new GenericException(e);
		} catch (SAXException e) {
			throw new ConnectionException(e);
		} catch (IOException e) {
			throw new ConnectionException(e);
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		buf = new StringBuilder();
		if (localName.equals(item)) {
			inItem = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		buf.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (buf.length() != 0 && inItem) {
			if (localName.equals(BADNEWS_TITLE)) {
				xml_title = buf.toString();
				badnewsDaAutostrada = (xml_title.charAt(0) == charAutostrade);
			} else if (badnewsDaAutostrada && localName.equals(BADNEWS_DESCRIPTION))
				xml_description = buf.toString();
			else if (badnewsDaAutostrada && localName.equals(BADNEWS_PUBDATE))
				try {
					date = sdfBnParse.parse(buf.toString());
				} catch (ParseException e) {
					date = new Date();
				}
			else if (badnewsDaAutostrada && localName.equals(item)) {
				inItem = false;
				try {
					StreetDTO streetDTO = dto.getStreet(Integer.parseInt(new StringTokenizer(xml_title, badNewsStreetDelim).nextToken()));
					if (streetDTO != null) {
						StringTokenizer descST = new StringTokenizer(xml_description, badNewsDelim);
						streetDTO.addBadNews(new BadNewsDTO(descST.nextToken(), descST.nextToken(), date));
					}
				} catch (NumberFormatException e) {
				}
			}
		}
	}
}
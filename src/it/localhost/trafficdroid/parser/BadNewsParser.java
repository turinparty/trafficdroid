package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dao.EventDAO;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.IOException;
import java.text.ParseException;
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
		if (localName.equals(Const.item)) {
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
			if (localName.equals(Const.BADNEWS_TITLE)) {
				String s = buf.toString();
				badnewsDaAutostrada = (s.charAt(0) == Const.charAutostrade);
				if (badnewsDaAutostrada)
					xml_title = s;
			} else if (badnewsDaAutostrada && localName.equals(Const.BADNEWS_DESCRIPTION))
				xml_description = buf.toString();
			else if (badnewsDaAutostrada && localName.equals(Const.BADNEWS_PUBDATE))
				try {
					date = Const.sdfBnParse.parse(buf.toString());
				} catch (ParseException e) {
					date = new Date();
				}
			else if (badnewsDaAutostrada && localName.equals(Const.item)) {
				try {
					StreetDTO streetDTO = dto.getStreet(Integer.parseInt(new StringTokenizer(xml_title, Const.badNewsStreetDelim).nextToken()));
					if (streetDTO != null) {
						StringTokenizer descST = new StringTokenizer(xml_description, Const.badNewsDelim);
						streetDTO.addBadNews(new BadNewsDTO(descST.nextToken(), descST.nextToken(), date));
					}
				} catch (NumberFormatException e) {
				}
				inItem = false;
			}
		}
	}
}
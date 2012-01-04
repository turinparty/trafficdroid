package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class TrafficParser extends DefaultHandler {
	private MainDTO dto;
	private String url;
	private StreetDTO street;
	private int zoneCounter;
	private StringBuilder buf;
	private boolean inSector = false;
	private boolean rightZone = false;

	public TrafficParser(MainDTO dto, String url) {
		this.dto = dto;
		this.url = url;
	}

	public void parse() throws GenericException, BadConfException, ConnectionException {
		try {
			XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
			xmlReader.setContentHandler(this);
			for (StreetDTO street : dto.getStreets()) {
				this.street = street;
				zoneCounter = 0;
				InputSource inputSource = TrafficDAO.getData(street.getId(), url);
				if (inputSource == null)
					throw new BadConfException(Const.badTrafficProvider);
				try {
					xmlReader.parse(inputSource);
				} catch (SAXException e) {
				}
			}
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
		if (zoneCounter >= street.getZones().size())
			throw new SAXException();
		if (localName.equals(Const.SECTOR_ELEMENT))
			inSector = true;
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		buf.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (buf.length() != 0) {
			if (inSector) {
				ZoneDTO zone = street.getZones().get(zoneCounter);
				if (localName.equals(Const.LABEL_ELEMENT)) {
					rightZone = buf.toString().equalsIgnoreCase(zone.getName());
				} else if (rightZone && localName.equals(Const.KM_ELEMENT)) {
					zone.setKm(buf.toString());
				} else if (rightZone && localName.equals(Const.DIRA_ELEMENT)) {
					try {
						zone.setSpeedLeft(Short.parseShort(buf.toString()));
					} catch (Exception e) {
						zone.setSpeedLeft((short) 0);
					}
				} else if (rightZone && localName.equals(Const.DIRB_ELEMENT)) {
					try {
						zone.setSpeedRight(Short.parseShort(buf.toString()));
					} catch (Exception e) {
						zone.setSpeedRight((short) 0);
					}
				} else if (localName.equals(Const.SECTOR_ELEMENT)) {
					inSector = false;
					if (rightZone)
						zoneCounter++;
				}
			} else {
				if (localName.equals(Const.STARTDIR_ELEMENT))
					street.setDirectionLeft(buf.toString());
				else if (localName.equals(Const.ENDDIR_ELEMENT))
					street.setDirectionRight(buf.toString());
			}
		}
	}
}
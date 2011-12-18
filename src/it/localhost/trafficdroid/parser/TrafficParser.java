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
	private boolean zonaGiusta = false;

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
					zonaGiusta = buf.toString().equalsIgnoreCase(zone.getName());
				} else if (zonaGiusta && localName.equals(Const.KM_ELEMENT)) {
					zone.setKm(buf.toString());
				} else if (zonaGiusta && localName.equals(Const.DIRA_ELEMENT)) {
					try {
						zone.setSpeedLeft(Short.parseShort(buf.toString()));
					} catch (Exception e) {
						zone.setSpeedLeft((short) 0);
					}
					if (zone.getSpeedLeft() == 0)
						zone.setCatLeft((byte) 0);
					else if (zone.getSpeedLeft() < 11)
						zone.setCatLeft((byte) 1);
					else if (zone.getSpeedLeft() < 31)
						zone.setCatLeft((byte) 2);
					else if (zone.getSpeedLeft() < 51)
						zone.setCatLeft((byte) 3);
					else if (zone.getSpeedLeft() < 71)
						zone.setCatLeft((byte) 4);
					else if (zone.getSpeedLeft() < 91)
						zone.setCatLeft((byte) 5);
					else
						zone.setCatLeft((byte) 6);
				} else if (zonaGiusta && localName.equals(Const.DIRB_ELEMENT)) {
					try {
						zone.setSpeedRight(Short.parseShort(buf.toString()));
					} catch (Exception e) {
						zone.setSpeedRight((short) 0);
					}
					if (zone.getSpeedRight() == 0)
						zone.setCatRight((byte) 0);
					else if (zone.getSpeedRight() < 11)
						zone.setCatRight((byte) 1);
					else if (zone.getSpeedRight() < 31)
						zone.setCatRight((byte) 2);
					else if (zone.getSpeedRight() < 51)
						zone.setCatRight((byte) 3);
					else if (zone.getSpeedRight() < 71)
						zone.setCatRight((byte) 4);
					else if (zone.getSpeedRight() < 91)
						zone.setCatRight((byte) 5);
					else
						zone.setCatRight((byte) 6);
				} else if (localName.equals(Const.SECTOR_ELEMENT)) {
					inSector = false;
					boolean congestionLeft = zone.getCatLeft() > 0 && zone.getCatLeft() <= dto.getCongestionThreshold();
					boolean congestionRight = zone.getCatRight() > 0 && zone.getCatRight() <= dto.getCongestionThreshold();
					if (congestionLeft && congestionRight)
						dto.addCongestedZone(zone.getName());
					else if (congestionLeft)
						dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionLeft() + Const.closeRound);
					else if (congestionRight)
						dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionRight() + Const.closeRound);
					if (zonaGiusta)
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
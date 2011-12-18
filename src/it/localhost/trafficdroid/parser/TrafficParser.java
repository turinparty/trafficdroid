package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class TrafficParser extends DefaultHandler {
	private static MainDTO dto;
	private static final String STARTDIR_ELEMENT = "startdir";
	private static final String ENDDIR_ELEMENT = "enddir";
	private static final String SECTOR_ELEMENT = "sector";
	private static final String LABEL_ELEMENT = "label";
	private static final String KM_ELEMENT = "km";
	private static final String DIRA_ELEMENT = "dirA";
	private static final String DIRB_ELEMENT = "dirB";

	private static class FineDatiException extends SAXException {
	}

	public static void parse(MainDTO dto, String url) throws GenericException, BadConfException, ConnectionException {
		TrafficParser.dto = dto;
		try {
			SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			TrafficParser parser = new TrafficParser();
			xmlReader.setContentHandler(parser);
			for (StreetDTO street : dto.getStreets()) {
				TrafficParser.street = street;
				zoneCounter = 0;
				InputSource document = TrafficDAO.getData(street.getId(), url);
				try {
					xmlReader.parse(document);
				} catch (FineDatiException e) {
					Log.i("TD", "fine dati");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static StreetDTO street;
	private static int zoneCounter;
	private StringBuilder buf = null;
	private boolean inSector = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws FineDatiException {
		buf = new StringBuilder();
		if (zoneCounter >= street.getZones().size()) {
			throw new FineDatiException();
		}
		if (localName.equals(SECTOR_ELEMENT)) {
			inSector = true;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		buf.append(ch, start, length);
	}

	private static boolean zonaGiusta = false;

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (buf.length() == 0) {
			return;
		}
		if (!inSector) {
			if (localName.equals(STARTDIR_ELEMENT)) {
				street.setDirectionLeft(buf.toString());
			} else if (localName.equals(ENDDIR_ELEMENT)) {
				street.setDirectionRight(buf.toString());
			}
		} else {
			ZoneDTO zone = street.getZones().get(zoneCounter);
			if (localName.equals(LABEL_ELEMENT)) {
				zonaGiusta = buf.toString().equalsIgnoreCase(zone.getName());
			} else if (zonaGiusta && localName.equals(KM_ELEMENT)) {
				zone.setKm(buf.toString());
			} else if (zonaGiusta && localName.equals(DIRA_ELEMENT)) {
				zone.setSpeedLeft(Byte.parseByte(buf.toString()));
			} else if (zonaGiusta && localName.equals(DIRB_ELEMENT)) {
				zone.setSpeedRight(Byte.parseByte(buf.toString()));
			} else if (localName.equals(SECTOR_ELEMENT)) {
				inSector = false;
				boolean congestionLeft = zone.getCatLeft() > 0 && zone.getCatLeft() <= dto.getCongestionThreshold();
				boolean congestionRight = zone.getCatRight() > 0 && zone.getCatRight() <= dto.getCongestionThreshold();
				if (congestionLeft && congestionRight)
					dto.addCongestedZone(zone.getName());
				else if (congestionLeft)
					dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionLeft() + Const.closeRound);
				else if (congestionRight)
					dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionRight() + Const.closeRound);
				if (zonaGiusta) {
					zoneCounter++;
				}
			}
		}
	}
}
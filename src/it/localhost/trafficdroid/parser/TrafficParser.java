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

	private static final String ID_ELEMENT = "id";
	private static final String NAME_ELEMENT = "name";
	private static final String CODE_ELEMENT = "code";
	private static final String TOTKM_ELEMENT = "totkm";
	private static final String STARTDIR_ELEMENT = "startdir";
	private static final String ENDDIR_ELEMENT = "enddir";
	private static final String SECTORS_ELEMENT = "sectors";
	private static final String STARTNAME_ELEMENT = "startname";
	private static final String ENDNAME_ELEMENT = "endname";

	private static final String SECTOR_ELEMENT = "sector";

	private static final String IDSECTOR_ELEMENT = "idsector";
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
	public void startElement(String uri, String localName, String qName, Attributes attributes)
			throws FineDatiException {
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

		Log.d("TD", "localName = [" + localName + "], inSector = " + inSector);

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
					dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionLeft()
							+ Const.closeRound);
				else if (congestionRight)
					dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionRight()
							+ Const.closeRound);
				if (zonaGiusta) {
					zoneCounter++;
				}
			}
		}

	}

	//	private void doo() {
	//		Document document = null;
	//		NodeList trDirection = document.getElementsByTagName(Const.codeTable).item(2).getChildNodes();
	//		street.setDirectionLeft(trDirection.item(0).getChildNodes().item(0).getTextContent());
	//		street.setDirectionRight(trDirection.item(1).getChildNodes().item(0).getTextContent());
	//		NodeList divsZoneA = document.getElementsByTagName(Const.codeDiv);
	//		for (int i = 0; i < divsZoneA.getLength(); i++) {
	//			Node idZone = divsZoneA.item(i).getAttributes().getNamedItem(Const.codeId);
	//			if (idZone != null && idZone.getNodeValue().equalsIgnoreCase(Const.codeSection)) {
	//				NodeList divsZoneB = divsZoneA.item(i).getChildNodes();
	//				for (int y = 0; y < divsZoneB.getLength(); y++)
	//					if (divsZoneB.item(y).getAttributes().getNamedItem(Const.codeId).getNodeValue()
	//							.equalsIgnoreCase(Const.codeRoadbox)) {
	//						NodeList trZone = divsZoneB.item(y).getFirstChild().getChildNodes();
	//						for (int z = 0; z < trZone.getLength() - 1 && zoneCounter < street.getZones().size(); z += 2) {
	//							ZoneDTO zone = street.getZones().get(zoneCounter);
	//							zone.setKm(new StringTokenizer(trZone.item(z).getChildNodes().item(0).getTextContent())
	//									.nextToken());
	//							if (trZone.item(z).getChildNodes().item(1).getChildNodes().item(2).getTextContent().trim()
	//									.equalsIgnoreCase(zone.getName())) {
	//								zone.setCatLeft(Byte.parseByte(trZone.item(z + 1).getChildNodes().item(1)
	//										.getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
	//								String speedLeft = trZone.item(z + 1).getChildNodes().item(0).getTextContent();
	//								zone.setCatRight(Byte.parseByte(trZone.item(z + 1).getChildNodes().item(2)
	//										.getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
	//								String speedRight = trZone.item(z + 1).getChildNodes().item(3).getTextContent();
	//								if (zone.getCatLeft() == 6)
	//									zone.setSpeedLeft(Byte.parseByte(speedLeft.substring(6, speedLeft.length() - 5)));
	//								else if (zone.getCatLeft() == 0)
	//									zone.setSpeedLeft((byte) 0);
	//								else
	//									zone.setSpeedLeft(Byte.parseByte(speedLeft.substring(0, speedLeft.length() - 5)));
	//								if (zone.getCatRight() == 6)
	//									zone.setSpeedRight(Byte.parseByte(speedRight.substring(6, speedRight.length() - 5)));
	//								else if (zone.getCatRight() == 0)
	//									zone.setSpeedRight((byte) 0);
	//								else
	//									zone.setSpeedRight(Byte.parseByte(speedRight.substring(0, speedRight.length() - 5)));
	//								boolean congestionLeft = zone.getCatLeft() > 0
	//										&& zone.getCatLeft() <= dto.getCongestionThreshold();
	//								boolean congestionRight = zone.getCatRight() > 0
	//										&& zone.getCatRight() <= dto.getCongestionThreshold();
	//								if (congestionLeft && congestionRight)
	//									dto.addCongestedZone(zone.getName());
	//								else if (congestionLeft)
	//									dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionLeft()
	//											+ Const.closeRound);
	//								else if (congestionRight)
	//									dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionRight()
	//											+ Const.closeRound);
	//								zoneCounter++;
	//							}
	//						}
	//					}
	//			}
	//
	//		}
	//	}
}
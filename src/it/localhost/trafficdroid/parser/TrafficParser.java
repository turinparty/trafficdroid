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
				try {
					xmlReader.parse(TrafficDAO.getData(street.getId(), url));
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
/*public static void parse(MainDTO dto, String url) throws GenericException, BadConfException, ConnectionException, SAXException, IOException, ParserConfigurationException {
	for (StreetDTO street : dto.getStreets()) {
		InputStream is = TrafficDAO.getData(street.getId(), url);
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		Element document = doc.getDocumentElement();
		street.setDirectionLeft(document.getElementsByTagName("startdir").item(0).getTextContent());
		street.setDirectionRight(document.getElementsByTagName("enddir").item(0).getTextContent());
		NodeList sectors = document.getElementsByTagName("sector");
		int zoneCounter = 0;
		for (int i = 0; i < sectors.getLength() - 1; i++) {
			String label = sectors.item(i).getChildNodes().item(1).getTextContent();
			if (zoneCounter < street.getZones().size()) {
				ZoneDTO zone = street.getZones().get(zoneCounter);
				if (zone.getName().equals(label)) {
					zone.setKm(sectors.item(i).getChildNodes().item(2).getTextContent());
					try {
						zone.setSpeedLeft(Short.parseShort(sectors.item(i).getChildNodes().item(3).getTextContent()));
					} catch (Exception e) {
						zone.setSpeedLeft((short) 0);
					}
					try {
						zone.setSpeedRight(Short.parseShort(sectors.item(i).getChildNodes().item(4).getTextContent()));
					} catch (Exception e) {
						zone.setSpeedRight((short) 0);
					}
					zoneCounter++;
				}
			}
		}
	}
}*/
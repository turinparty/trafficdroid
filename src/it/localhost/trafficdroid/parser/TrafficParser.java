package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class TrafficParser {
	public static void parse(MainDTO dto, String url) throws GenericException, BadConfException, ConnectionException {
		for (StreetDTO street : dto.getStreets()) {
			Element document = TrafficDAO.getData(street.getId(), url).getDocumentElement();
			NodeList segments = document.getLastChild().getFirstChild().getChildNodes();
			for (int i = 0; i < segments.getLength(); i++) {
				NodeList segChildrens = segments.item(i).getChildNodes();
				int from = Integer.parseInt(segChildrens.item(0).getTextContent());
				int to = Integer.parseInt(segChildrens.item(1).getTextContent());
				String speed = segChildrens.item(2).getTextContent();
				for (ZoneDTO zone : street.getZones()) {
					if (zone.getId() >= from && zone.getId() < to)
						zone.setSpeedLeft(speed);
					else if (zone.getId() <= from && zone.getId() > to)
						zone.setSpeedRight(speed);
				}
			}
		}
	}
}
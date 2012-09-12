package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import org.w3c.dom.NodeList;

public class TrafficParser {
	public static void parse(MainDTO dto, String url) throws GenericException, BadConfException, ConnectionException {
		for (StreetDTO street : dto.getStreets()) {
			NodeList segments = TrafficDAO.getData(street.getId(), url).getDocumentElement().getLastChild().getFirstChild().getChildNodes();
			for (int i = 0; i < segments.getLength(); i++) {
				NodeList segChildrens = segments.item(i).getChildNodes();
				int from = Integer.parseInt(segChildrens.item(0).getTextContent());
				int to = Integer.parseInt(segChildrens.item(1).getTextContent());
				short speed = Short.parseShort(segChildrens.item(2).getTextContent());
				int fromIndex = street.getAllZonesId().indexOf(from);
				int toIndex = street.getAllZonesId().indexOf(to);
				if (fromIndex != -1 && toIndex != -1) {
					if (fromIndex < toIndex) {
						for (int id : street.getAllZonesId().subList(fromIndex, toIndex))
							if (street.getZone(id) != null)
								street.getZone(id).setSpeedLeft(speed);
					} else {
						for (int id : street.getAllZonesId().subList(toIndex, fromIndex))
							if (street.getZone(id) != null)
								street.getZone(id).setSpeedRight(speed);
					}
				}
			}
		}
	}
}
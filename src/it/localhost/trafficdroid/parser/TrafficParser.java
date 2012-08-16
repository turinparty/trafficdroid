package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.util.ArrayList;

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
				short speed = Short.parseShort(segChildrens.item(2).getTextContent());
				ArrayList<ZoneDTO> zones = street.getZones();
				int start = 0, end = 0;
				for (int j = 0; j < zones.size() - 1; j++)
					if (from == zones.get(j).getId())
						start = j;
					else if (to == zones.get(j).getId())
						end = j;
				if (start < end)
					for (int j = start; j <= end; j++)
						zones.get(j).setSpeedLeft(speed);
				else
					for (int j = end; j <= start; j++)
						zones.get(j).setSpeedRight(speed);
			}
		}
	}
}
package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TrafficParser {
	public static void parse(MainDTO dto, String url) throws TdException {
		try {
			for (StreetDTO street : dto.getStreets()) {
				int zoneCounter = 0;
				Element document = TrafficDAO.getData(street.getId(), url).getDocumentElement();
				NodeList trDirection = document.getElementsByTagName(Const.codeTable).item(2).getChildNodes();
				street.setDirectionLeft(trDirection.item(0).getChildNodes().item(0).getTextContent());
				street.setDirectionRight(trDirection.item(1).getChildNodes().item(0).getTextContent());
				NodeList divsZoneA = document.getElementsByTagName(Const.codeDiv);
				for (int i = 0; i < divsZoneA.getLength(); i++) {
					Node idZone = divsZoneA.item(i).getAttributes().getNamedItem(Const.codeId);
					if (idZone != null && idZone.getNodeValue().equalsIgnoreCase(Const.codeSection)) {
						NodeList divsZoneB = divsZoneA.item(i).getChildNodes();
						for (int y = 0; y < divsZoneB.getLength(); y++)
							if (divsZoneB.item(y).getAttributes().getNamedItem(Const.codeId).getNodeValue().equalsIgnoreCase(Const.codeRoadbox)) {
								NodeList trZone = divsZoneB.item(y).getFirstChild().getChildNodes();
								for (int z = 0; z < trZone.getLength() - 1 && zoneCounter < street.getZones().size(); z += 2) {
									ZoneDTO zone = street.getZones().get(zoneCounter);
									if (trZone.item(z).getChildNodes().item(1).getChildNodes().item(2).getTextContent().trim().equalsIgnoreCase(zone.getName())) {
										zone.setCatLeft(Integer.parseInt(trZone.item(z + 1).getChildNodes().item(1).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
										zone.setSpeedLeft(trZone.item(z + 1).getChildNodes().item(0).getTextContent());
										zone.setCatRight(Integer.parseInt(trZone.item(z + 1).getChildNodes().item(2).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
										zone.setSpeedRight(trZone.item(z + 1).getChildNodes().item(3).getTextContent());
										boolean congestionLeft = zone.getCatLeft() > 0 && zone.getCatLeft() <= dto.getCongestionThreshold();
										boolean congestionRight = zone.getCatRight() > 0 && zone.getCatRight() <= dto.getCongestionThreshold();
										if (congestionLeft && congestionRight)
											dto.addCongestedZone(zone.getName());
										else if (congestionLeft)
											dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionLeft() + Const.closeRound);
										else if (congestionRight)
											dto.addCongestedZone(zone.getName() + Const.openRound + street.getDirectionRight() + Const.closeRound);
										zoneCounter++;
									}
								}
							}
					}
				}
			}
		} catch (TdException e) {
			throw new TdException(e.getKey(), e.getMessage());
		} catch (Exception e) {
			throw new TdException(TdException.ParsingException, e.getMessage());
		}
	}
}
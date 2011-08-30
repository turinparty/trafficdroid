package it.localhost.trafficdroid.parser;

import java.util.StringTokenizer;

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
									zone.setKm(new StringTokenizer(trZone.item(z).getChildNodes().item(0).getTextContent()).nextToken());
									if (trZone.item(z).getChildNodes().item(1).getChildNodes().item(2).getTextContent().trim().equalsIgnoreCase(zone.getName())) {
										zone.setCatLeft(Byte.parseByte(trZone.item(z + 1).getChildNodes().item(1).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
										String speedLeft = trZone.item(z + 1).getChildNodes().item(0).getTextContent();
										zone.setCatRight(Byte.parseByte(trZone.item(z + 1).getChildNodes().item(2).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
										String speedRight = trZone.item(z + 1).getChildNodes().item(3).getTextContent();
										if (zone.getCatLeft() == 6)
											zone.setSpeedLeft(Byte.parseByte(speedLeft.substring(6, speedLeft.length() - 5)));
										else if (zone.getCatLeft() == 0)
											zone.setSpeedLeft((byte) 0);
										else
											zone.setSpeedLeft(Byte.parseByte(speedLeft.substring(0, speedLeft.length() - 5)));
										if (zone.getCatRight() == 6)
											zone.setSpeedRight(Byte.parseByte(speedRight.substring(6, speedRight.length() - 5)));
										else if (zone.getCatRight() == 0)
											zone.setSpeedRight((byte) 0);
										else
											zone.setSpeedRight(Byte.parseByte(speedRight.substring(0, speedRight.length() - 5)));
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
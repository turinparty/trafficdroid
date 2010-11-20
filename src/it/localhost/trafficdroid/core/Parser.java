package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.Date;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	private static Element document;
	private static NodeList trDirection;
	private static NodeList divsZoneA;
	private static NodeList divsZoneB;
	private static NodeList trZone;
	private static Node idZone;
	private static ZoneDTO zone;
	private static int zoneCounter;

	public static DLCTaskDTO parse(DLCTaskDTO dto) throws TdException {
		for (StreetDTO street : dto.getStreets()) {
			zoneCounter = 0;
			document = TrafficDAO.getData(street.getCode(), dto.getUrl()).getDocumentElement();
			trDirection = document.getElementsByTagName(Const.codeTable).item(2).getChildNodes();
			street.setDirectionLeft(trDirection.item(0).getChildNodes().item(0).getFirstChild().getNodeValue());
			street.setDirectionRight(trDirection.item(1).getChildNodes().item(0).getFirstChild().getNodeValue());
			divsZoneA = document.getElementsByTagName(Const.codeDiv);
			for (int i = 0; i < divsZoneA.getLength(); i++) {
				idZone = divsZoneA.item(i).getAttributes().getNamedItem(Const.codeId);
				if (idZone != null && idZone.getNodeValue().equalsIgnoreCase(Const.codeSection)) {
					divsZoneB = divsZoneA.item(i).getChildNodes();
					for (int y = 0; y < divsZoneB.getLength(); y++) {
						if (divsZoneB.item(y).getAttributes().getNamedItem(Const.codeId).getNodeValue().equalsIgnoreCase(Const.codeRoadbox)) {
							trZone = divsZoneB.item(y).getFirstChild().getChildNodes();
							for (int z = 0; z < trZone.getLength() - 1 && zoneCounter < street.getZones().size(); z += 2) {
								zone = street.getZones().get(zoneCounter);
								if (trZone.item(z).getChildNodes().item(1).getChildNodes().item(2).getFirstChild().getNodeValue().trim().equalsIgnoreCase(zone.getName())) {
									zone.setCatLeft(Integer.parseInt(trZone.item(z + 1).getChildNodes().item(1).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
									zone.setSpeedLeft(trZone.item(z + 1).getChildNodes().item(0).getFirstChild().getNodeValue());
									zone.setCatRight(Integer.parseInt(trZone.item(z + 1).getChildNodes().item(2).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
									zone.setSpeedRight(trZone.item(z + 1).getChildNodes().item(3).getFirstChild().getNodeValue());
									boolean congestionLeft = (zone.getCatLeft() > 0 && zone.getCatLeft() < dto.getCongestionThreshold());
									boolean congestionRight = (zone.getCatRight() > 0 && zone.getCatRight() < dto.getCongestionThreshold());
									if (congestionLeft && congestionRight)
										dto.addCongestedZone(zone.getName());
									else if (congestionLeft)
										dto.addCongestedZone(zone.getName() + " (" + street.getDirectionLeft() + ")");
									else if (congestionRight)
										dto.addCongestedZone(zone.getName() + " (" + street.getDirectionRight() + ")");
									zoneCounter++;
								}
							}
						}
					}
				}
			}
		}
		dto.setTrafficTime(new Date());
		return dto;
	}
}
/*
	if (zone.getCatLeft() == 6)
		zone.setSpeedLeft(zone.getSpeedLeft().substring(6, zone.getSpeedLeft().length() - 5) + "+");
	else
		zone.setSpeedLeft(zone.getSpeedLeft().substring(0, zone.getSpeedLeft().length() - 5));
	if (zone.getCatRight() == 6)
		zone.setSpeedRight(zone.getSpeedRight().substring(6, zone.getSpeedRight().length() - 5) + "+");
	else
		zone.setSpeedRight(zone.getSpeedRight().substring(0, zone.getSpeedRight().length() - 5));
 */
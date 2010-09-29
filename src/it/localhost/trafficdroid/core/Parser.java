package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.CoreException;
import it.localhost.trafficdroid.exception.DaoException;

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

	public static StreetDTO parse(StreetDTO street, String url) throws CoreException {
		try {
			document = TrafficDAO.get(street.getCode(), url).getDocumentElement();
		} catch (DaoException e) {
			throw new CoreException(CoreException.DaoException, e.getMessage());
		}
		trDirection = document.getElementsByTagName(Const.codeTable).item(2).getChildNodes();
		street.setDirections(new String[] { trDirection.item(0).getChildNodes().item(0).getFirstChild().getNodeValue(), trDirection.item(1).getChildNodes().item(0).getFirstChild().getNodeValue() });
		divsZoneA = document.getElementsByTagName(Const.codeDiv);
		for (int i = 0; i < divsZoneA.getLength(); i++) {
			idZone = divsZoneA.item(i).getAttributes().getNamedItem(Const.codeId);
			if (idZone != null && idZone.getNodeValue().equalsIgnoreCase(Const.codeSection)) {
				divsZoneB = divsZoneA.item(i).getChildNodes();
				for (int y = 0; y < divsZoneB.getLength(); y++) {
					if (divsZoneB.item(y).getAttributes().getNamedItem(Const.codeId).getNodeValue().equalsIgnoreCase(Const.codeRoadbox)) {
						trZone = divsZoneB.item(y).getFirstChild().getChildNodes();
						for (int z = 0; z < trZone.getLength() - 1; z += 2) {
							zone = new ZoneDTO(trZone.item(z).getChildNodes().item(1).getChildNodes().item(2).getFirstChild().getNodeValue());
							zone.setCatLeft(Integer.parseInt(trZone.item(z + 1).getChildNodes().item(1).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
							zone.setSpeedLeft(trZone.item(z + 1).getChildNodes().item(0).getFirstChild().getNodeValue());
							zone.setCatRight(Integer.parseInt(trZone.item(z + 1).getChildNodes().item(2).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
							zone.setSpeedRight(trZone.item(z + 1).getChildNodes().item(3).getFirstChild().getNodeValue());
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
							street.addZone(zone);
						}
					}
				}
			}
		}
		return street;
	}
}
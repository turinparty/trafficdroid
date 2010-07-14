package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dao.TrafficDAO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.CoreException;
import it.localhost.trafficdroid.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
	private Element document;
	private NodeList divsA;
	private NodeList divsB;
	private NodeList trs;
	private Node id;
	private List<ZoneDTO> zones;
	private ZoneDTO zone;

	public Parser(int mapId, String url) throws CoreException {
		zones = new ArrayList<ZoneDTO>();
		if (url.equals(Const.emptyString))
			throw new CoreException(CoreException.EmptyUrlException, Const.msgMissingUrl);
		try {
			document = TrafficDAO.get(mapId, url).getDocumentElement();
		} catch (DaoException e) {
			throw new CoreException(CoreException.DaoException, e.getMessage());
		}
	}

	public List<ZoneDTO> getZones() {
		divsA = document.getElementsByTagName(Const.codeDiv);
		for (int i = 0; i < divsA.getLength(); i++) {
			id = divsA.item(i).getAttributes().getNamedItem(Const.codeId);
			if (id != null && id.getNodeValue().equalsIgnoreCase(Const.codeSection)) {
				divsB = divsA.item(i).getChildNodes();
				for (int y = 0; y < divsB.getLength(); y++) {
					if (divsB.item(y).getAttributes().getNamedItem(Const.codeId).getNodeValue().equalsIgnoreCase(Const.codeRoadbox)) {
						trs = divsB.item(y).getFirstChild().getChildNodes();
						for (int z = 0; z < trs.getLength() - 1; z += 2) {
							zone = new ZoneDTO();
							zone.setName(trs.item(z).getChildNodes().item(1).getChildNodes().item(2).getFirstChild().getNodeValue());
							zone.setSpeedLeft(trs.item(z + 1).getChildNodes().item(0).getFirstChild().getNodeValue());
							zone.setCatLeft(Integer.parseInt(trs.item(z + 1).getChildNodes().item(1).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
							zone.setCatRight(Integer.parseInt(trs.item(z + 1).getChildNodes().item(2).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
							zone.setSpeedRight(trs.item(z + 1).getChildNodes().item(3).getFirstChild().getNodeValue());
							zones.add(zone);
						}
					}
				}
			}
		}
		return zones;
	}

	public List<String> getDirections() {
		ArrayList<String> directions = new ArrayList<String>();
		NodeList nodesDirections = document.getElementsByTagName(Const.codeTable).item(2).getChildNodes();
		directions.add(nodesDirections.item(0).getChildNodes().item(0).getFirstChild().getNodeValue());
		directions.add(nodesDirections.item(1).getChildNodes().item(0).getFirstChild().getNodeValue());
		return directions;
	}
}
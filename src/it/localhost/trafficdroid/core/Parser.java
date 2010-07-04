package it.localhost.trafficdroid.core;

import it.localhost.trafficdroid.dao.ContentDAO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.CoreException;
import it.localhost.trafficdroid.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Parser {
	private static final String DIV = "div";
	private static final String ID = "id";
	private static final String SECTION = "section";
	private static final String ROADBOX = "roadbox";
	private static NodeList divsA;
	private static NodeList divsB;
	private static NodeList trs;
	private static Node id;
	private static List<ZoneDTO> zones;
	private static ZoneDTO zone;

	public static List<ZoneDTO> parse(int mapId) throws CoreException {
		zones = new ArrayList<ZoneDTO>();
		try {
			divsA = ContentDAO.getContent(mapId).getDocumentElement().getElementsByTagName(DIV);
		} catch (DaoException e) {
			throw new CoreException(CoreException.DaoException, e.getMessage());
		}
		for (int i = 0; i < divsA.getLength(); i++) {
			id = divsA.item(i).getAttributes().getNamedItem(ID);
			if (id != null && id.getNodeValue().equalsIgnoreCase(SECTION)) {
				divsB = divsA.item(i).getChildNodes();
				for (int y = 0; y < divsB.getLength(); y++) {
					if (divsB.item(y).getAttributes().getNamedItem(ID).getNodeValue().equalsIgnoreCase(ROADBOX)) {
						trs = divsB.item(y).getFirstChild().getChildNodes();
						for (int z = 0; z < trs.getLength() - 1; z += 2) {
							zone = new ZoneDTO();
							zone.setName(trs.item(z).getChildNodes().item(1).getChildNodes().item(2).getFirstChild().getNodeValue());
							zone.setSpeedLeft(trs.item(z + 1).getChildNodes().item(0).getFirstChild().getNodeValue());
							zone.setCatLeft(Integer.parseInt(trs.item(z + 1).getChildNodes().item(1).getAttributes().getNamedItem("class").getNodeValue().substring(2, 3)));
							zone.setCatRight(Integer.parseInt(trs.item(z + 1).getChildNodes().item(2).getAttributes().getNamedItem("class").getNodeValue().substring(2, 3)));
							zone.setSpeedRight(trs.item(z + 1).getChildNodes().item(3).getFirstChild().getNodeValue());
							zones.add(zone);
						}
					}
				}
			}
		}
		return zones;
	}
}
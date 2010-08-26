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
	private static NodeList divsA;
	private static NodeList divsB;
	private static NodeList trsA;
	private static NodeList trsB;
	private static Node id;
	private static ZoneDTO zone;

	public static StreetDTO parse(StreetDTO street, String url) throws CoreException {
		try {
			document = TrafficDAO.get(street.getCode(), url).getDocumentElement();
		} catch (DaoException e) {
			throw new CoreException(CoreException.DaoException, e.getMessage());
		}
		trsA = document.getElementsByTagName(Const.codeTable).item(2).getChildNodes();
		String[] directions = { trsA.item(0).getChildNodes().item(0).getFirstChild().getNodeValue(), trsA.item(1).getChildNodes().item(0).getFirstChild().getNodeValue() };
		street.setDirections(directions);
		divsA = document.getElementsByTagName(Const.codeDiv);
		for (int i = 0; i < divsA.getLength(); i++) {
			id = divsA.item(i).getAttributes().getNamedItem(Const.codeId);
			if (id != null && id.getNodeValue().equalsIgnoreCase(Const.codeSection)) {
				divsB = divsA.item(i).getChildNodes();
				for (int y = 0; y < divsB.getLength(); y++) {
					if (divsB.item(y).getAttributes().getNamedItem(Const.codeId).getNodeValue().equalsIgnoreCase(Const.codeRoadbox)) {
						trsB = divsB.item(y).getFirstChild().getChildNodes();
						for (int z = 0; z < trsB.getLength() - 1; z += 2) {
							zone = new ZoneDTO();
							zone.setName(trsB.item(z).getChildNodes().item(1).getChildNodes().item(2).getFirstChild().getNodeValue());
							zone.setSpeedLeft(trsB.item(z + 1).getChildNodes().item(0).getFirstChild().getNodeValue());
							zone.setCatLeft(Integer.parseInt(trsB.item(z + 1).getChildNodes().item(1).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
							zone.setCatRight(Integer.parseInt(trsB.item(z + 1).getChildNodes().item(2).getAttributes().getNamedItem(Const.codeClass).getNodeValue().substring(2, 3)));
							zone.setSpeedRight(trsB.item(z + 1).getChildNodes().item(3).getFirstChild().getNodeValue());
							street.addZone(zone);
						}
					}
				}
			}
		}
		return street;
	}
}
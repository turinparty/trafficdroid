package com.google.code.trafficdroid.core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.code.trafficdroid.dto.Zone;

public class Parser {
	private static final String DIV = "div";
	private static final String ID = "id";
	private static final String SECTION = "section";
	private static final String ROADBOX = "roadbox";
	private static NodeList divsA;
	private static NodeList divsB;
	private static NodeList trs;
	private static Node id;
	private static List<Zone> zones;
	private static Zone zone;

	public static List<Zone> parse(InputStream is) throws Exception {
		zones = new ArrayList<Zone>();
		divsA = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is).getDocumentElement().getElementsByTagName(DIV);
		for (int i = 0; i < divsA.getLength(); i++) {
			try {
				id = divsA.item(i).getAttributes().getNamedItem(ID);
			} catch (NullPointerException e) {
				id = null;
			}
			if (id != null && id.getNodeValue().equalsIgnoreCase(SECTION)) {
				divsB = divsA.item(i).getChildNodes();
				for (int y = 0; y < divsB.getLength(); y++) {
					if (divsB.item(y).getAttributes().getNamedItem(ID).getNodeValue().equalsIgnoreCase(ROADBOX)) {
						trs = divsB.item(y).getFirstChild().getChildNodes();
						for (int z = 0; z < trs.getLength() - 1; z += 2) {
							zone = new Zone();
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
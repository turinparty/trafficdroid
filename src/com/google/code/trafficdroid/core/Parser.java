package com.google.code.trafficdroid.core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

import com.google.code.trafficdroid.dto.Tratta;

public class Parser {
	public static List<Tratta> parse(InputStream is) throws Exception {
		Log.e("", "start");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		List<Tratta> tratte = new ArrayList<Tratta>();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document dom = builder.parse(is);
		Element body = dom.getDocumentElement();
		NodeList divsA = body.getElementsByTagName("div");
		for (int i = 0; i < divsA.getLength(); i++) {
			if (divsA.item(i).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase("section")) {
				NodeList divsB = divsA.item(i).getChildNodes();
				for (int y = 0; y < divsB.getLength(); y++) {
					if (divsB.item(y).getAttributes().getNamedItem("id").getNodeValue().equalsIgnoreCase("roadbox")) {
						Node table = divsB.item(y).getFirstChild();
						NodeList tr = table.getChildNodes();
						for (int z = 0; z < tr.getLength(); z++) {
							Log.e("", "" + tr.item(z).getNodeName());
						}
					}
				}
			}
		}
		Log.e("", "end");
		return null;
	}
}

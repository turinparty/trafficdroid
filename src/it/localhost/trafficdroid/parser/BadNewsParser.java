package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dao.EventDAO;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;

import java.util.StringTokenizer;

import org.w3c.dom.NodeList;

public class BadNewsParser {
	public static void parse(MainDTO dto, String url) {
		try {
			NodeList items = EventDAO.getData(url).getDocumentElement().getElementsByTagName(Const.item);
			for (int i = 0; i < items.getLength(); i++) {
				NodeList item = items.item(i).getChildNodes();
				String street = new StringTokenizer(item.item(1).getTextContent(), Const.badNewsStreetDelim).nextToken();
				StringTokenizer descST = new StringTokenizer(item.item(3).getTextContent(), Const.badNewsDelim);
				if (street.charAt(0) == Const.charAutostrade)
					try {
						for (StreetDTO streetDTO : dto.getStreets())
							if (Integer.parseInt(street.substring(1, street.length())) == streetDTO.getId())
								streetDTO.addBadNews(new BadNewsDTO(descST.nextToken(), descST.nextToken(), Const.sdfBnParse.parse(item.item(7).getTextContent())));
					} catch (Exception e) {
						// Do nothing
					}
			}
		} catch (Exception e1) {
			// Do nothing
		}
	}
}
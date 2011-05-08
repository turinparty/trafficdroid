package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.EventDAO;
import it.localhost.trafficdroid.dto.EventDTO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;

import java.text.SimpleDateFormat;
import java.util.StringTokenizer;

import org.w3c.dom.NodeList;

public class EventParser {
	public static void parse(MainDTO dto, String url) throws TdException {
		SimpleDateFormat sdf = new SimpleDateFormat(Const.formatDateEventi);
		NodeList items = EventDAO.getData(url).getDocumentElement().getElementsByTagName(Const.item);
		for (int i = 0; i < items.getLength(); i++) {
			try {
				NodeList item = items.item(i).getChildNodes();
				String title = new StringTokenizer(item.item(1).getTextContent()).nextToken();
				StringTokenizer descST = new StringTokenizer(item.item(3).getTextContent(), "\n");
				if (title.charAt(0) == Const.A)
					for (StreetDTO streetDTO : dto.getStreets())
						if (Integer.parseInt(title.substring(1, title.length())) == streetDTO.getId())
							streetDTO.addEvent(new EventDTO(descST.nextToken(), descST.nextToken(), sdf.parse(item.item(7).getTextContent())));
			} catch (Exception e) {
				// Do nothing
			}
		}
	}
}

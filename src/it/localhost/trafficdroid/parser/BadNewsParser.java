package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dao.EventDAO;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import org.w3c.dom.NodeList;

public class BadNewsParser {
	public static void parse(MainDTO dto, String url) throws TdException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(Const.formatDateEventi);
			NodeList items = EventDAO.getData(url).getDocumentElement().getElementsByTagName(Const.item);
			for (int i = 0; i < items.getLength(); i++) {
				NodeList item = items.item(i).getChildNodes();
				String title = new StringTokenizer(item.item(1).getTextContent()).nextToken();
				StringTokenizer descST = new StringTokenizer(item.item(3).getTextContent(), "\n");
				if (title.charAt(0) == Const.charAutostrade) {
					boolean found = false;
					for (StreetDTO streetDTO : dto.getStreets())
						if (Integer.parseInt(title.substring(1, title.length())) == streetDTO.getId()) {
							streetDTO.addBadNews(new BadNewsDTO(descST.nextToken(), descST.nextToken(), sdf.parse(item.item(7).getTextContent())));
							found = true;
						}
					if (!found)
						dto.addOtherBadNews(new BadNewsDTO(descST.nextToken(), descST.nextToken(), sdf.parse(item.item(7).getTextContent())));
				} else
					dto.addOtherBadNews(new BadNewsDTO(descST.nextToken(), descST.nextToken(), sdf.parse(item.item(7).getTextContent())));
			}
			dto.setTrafficTime(new Date());
		} catch (TdException e) {
			throw new TdException(e.getKey(), e.getMessage());
		} catch (Exception e) {
			throw new TdException(TdException.ParsingException, e.getMessage());
		}
	}
}

package it.localhost.trafficdroid.parser;

import it.localhost.trafficdroid.dao.PatenteDAO;
import it.localhost.trafficdroid.dto.PatenteDTO;
import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;

public class PatenteParser {
	private static final String ERROR = "Username e Password errati";
	private static final String SALDO_PUNTI_PATENTE = "//saldoPuntiPatente/*";

	public static PatenteDTO parse(String usr, String pwd) throws GenericException, BadConfException, ConnectionException {
		try {
			NodeList info = (NodeList) XPathFactory.newInstance().newXPath().compile(SALDO_PUNTI_PATENTE).evaluate(PatenteDAO.getData(usr, pwd), XPathConstants.NODESET);
			if (info.getLength() == 0)
				throw new BadConfException(ERROR);
			PatenteDTO patente = new PatenteDTO();
			for (int i = 0; i < 3; i++)
				if (info.item(i).getNodeName().equals("numeoPatente"))
					patente.setNumeoPatente(info.item(i).getTextContent());
				else if (info.item(i).getNodeName().equals("saldo"))
					patente.setSaldo(info.item(i).getTextContent());
				else if (info.item(i).getNodeName().equals("scadenzaPatente"))
					patente.setScadenzaPatente(info.item(i).getTextContent());
			return patente;
		} catch (XPathExpressionException e) {
			throw new GenericException(e);
		}
	}
}
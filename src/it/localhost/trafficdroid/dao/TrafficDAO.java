package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.exception.BadConfException;
import it.localhost.trafficdroid.exception.ConnectionException;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TrafficDAO {
	private static final String path = "/engine/traffic_server.php";
	private static final String traffic = "https://etraffic.";
	private static final String userKey = "user";
	private static final String pwdKey = "pwd";
	private static final String sqKey = "sq";
	private static final String typeKey = "type";
	private static final String roaKey = "roa";
	private static final String user = "robAnd_ev3nt5.appU";
	private static final String pwd = "daP-2012_00005,android.ev";
	private static final String sq = "1";
	private static final String type = "4";

	public static Document getData(int mapId, String baseUrl) throws GenericException, BadConfException, ConnectionException {
		Document doc = null;
		try {
			int numTries = 3;
			while (true) {
				try {
					List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
					params.add(new BasicNameValuePair(userKey, user));
					params.add(new BasicNameValuePair(pwdKey, pwd));
					params.add(new BasicNameValuePair(sqKey, sq));
					params.add(new BasicNameValuePair(typeKey, type));
					params.add(new BasicNameValuePair(roaKey, Integer.toString(mapId)));
					HttpPost post = new HttpPost(traffic + baseUrl + path);
					post.setEntity(new UrlEncodedFormEntity(params));
					doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new GZIPInputStream(new DefaultHttpClient().execute(post).getEntity().getContent()));
					break;
				} catch (SAXException e) {
					if (--numTries == 0)
						throw new ConnectionException(e);
				}
			}
		} catch (IOException e) {
			throw new BadConfException(e);
		} catch (NullPointerException e) {
			throw new BadConfException(e);
		} catch (ParserConfigurationException e) {
			throw new GenericException(e);
		}
		return doc;
	}
}
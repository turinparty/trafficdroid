package it.localhost.trafficdroid.service;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.MainActivity;
import it.localhost.trafficdroid.common.ListZoneResId;
import it.localhost.trafficdroid.common.ListZoneResName;
import it.localhost.trafficdroid.common.ListZoneResWebcam;
import it.localhost.trafficdroid.common.Utility;
import it.localhost.trafficdroid.dao.PersistanceService;
import it.localhost.trafficdroid.dto.BadNewsDTO;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.commonsware.cwac.wakeful.WakefulIntentService;

public class TdService extends WakefulIntentService { // NO_UCD
	public static final String beginUpdate = "it.localhost.trafficdroid.BEGIN_UPDATE";
	public static final String endUpdate = "it.localhost.trafficdroid.END_UPDATE";
	private static final Intent beginUpdateIntent = new Intent(beginUpdate);
	private static final Intent endUpdateIntent = new Intent(endUpdate);
	private static final String disconnectedMessage = "Connessione di rete inesistente";
	public static final int notificationId = 1;
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
	private static final String N = "N";
	private static final String E = "E";
	private static final String S = "S";
	private static final String O = "O";
	private static final String I = "I";
	private static final String EXT = "Ext";
	private static final String INT = "Int";
	private static final String OVEST = "Ovest";
	private static final String EST = "Est";
	private static final String SUD = "Sud";
	private static final String NORD = "Nord";
	private static final String NEWLINE = "\n";
	private static final String SPACE = " ";
	private static final String DC_DATE = "dc:date";
	private static final String DESCRIPTION = "description";
	private static final String TITLE = "title";
	private static final String ITEM = "item";
	private static final String TEMPLATE = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	private static final String URL = "http://www.cciss.it/portale/rss?rsstype=traffic";

	public TdService() {
		super(PersistanceService.tdData);
	}

	@Override
	public void doWakefulWork(Intent arg0) {
		sendBroadcast(beginUpdateIntent);
		MainDTO currDTO = getMainDTO();
		MainDTO pastDTO;
		try {
			pastDTO = PersistanceService.retrieve(this);
		} catch (Exception e) {
			pastDTO = null;
		}
		NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
		if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
			try {
				parseTraffic(currDTO);
				configureDTO(currDTO, pastDTO);
				setupNotification(currDTO.getCongestedZones());
			} catch (Exception e) {
				Utility.setExCheck(this, true);
				Utility.setExMsg(this, e.getMessage());
			}
			try {
				if (Utility.isBadnewsEnabler(this))
					parseBadnews(currDTO);
			} catch (Exception e) {
				Utility.setExCheck(this, true);
				Utility.setExMsg(this, e.getMessage());
			}
			try {
				currDTO.setTrafficTime(new Date());
				PersistanceService.store(this, currDTO);
			} catch (Exception e) {
				Utility.setExCheck(this, true);
				Utility.setExMsg(this, e.getMessage());
			}
		} else {
			Utility.setExCheck(this, true);
			Utility.setExMsg(this, disconnectedMessage);
		}
		sendBroadcast(endUpdateIntent);
	}

	private MainDTO getMainDTO() {
		MainDTO mainDto = new MainDTO();
		mainDto.setVersionCode(Utility.getVersionCode(this));
		mainDto.setCongestionThreshold(Byte.parseByte(Utility.getNotificationSpeed(this)));
		int[] streetsId = getResources().getIntArray(R.array.streetId);
		String[] streetsName = getResources().getStringArray(R.array.streetName);
		String[] streetsTag = getResources().getStringArray(R.array.streetTag);
		String[] graphTag = getResources().getStringArray(R.array.graphTag);
		String[] streetsDir = getResources().getStringArray(R.array.streetDir);
		int[] autoveloxStreet = getResources().getIntArray(R.array.autoveloxStreet);
		int[] autoveloxFrom = getResources().getIntArray(R.array.autoveloxFrom);
		int[] autoveloxTo = getResources().getIntArray(R.array.autoveloxTo);
		boolean allStreets = Utility.isAllStreets(this);
		for (int i = 0; i < streetsId.length; i++) {
			int[] zonesId = getResources().getIntArray(ListZoneResId.getInstance().get((streetsId[i])));
			StreetDTO street = new StreetDTO(streetsId[i], zonesId);
			boolean streetEnabled = Utility.isEnabled(this, Integer.toString(street.getId()));
			String[] zonesWebcam = getResources().getStringArray(ListZoneResWebcam.getInstance().get((streetsId[i])));
			String[] zonesName = getResources().getStringArray(ListZoneResName.getInstance().get(streetsId[i]));
			for (int j = 0; j < zonesId.length; j++)
				if (allStreets || streetEnabled || Utility.isEnabled(this, Integer.toString(zonesId[j]))) {
					ZoneDTO zone = new ZoneDTO(zonesId[j], zonesName[j], zonesWebcam[j]);
					for (int k = 0; k < autoveloxStreet.length; k++) {
						if (autoveloxStreet[k] == streetsId[i] && zonesId[j] >= autoveloxFrom[k] && zonesId[j] < autoveloxTo[k])
							zone.setAutoveloxLeft();
						if (autoveloxStreet[k] == streetsId[i] && zonesId[j] >= autoveloxTo[k] && zonesId[j] < autoveloxFrom[k])
							zone.setAutoveloxRight();
					}
					street.putZone(zone);
				}
			if (street.getZonesSize() > 0) {
				street.setName(streetsName[i]);
				street.setTag(streetsTag[i]);
				street.setGraph(graphTag[i]);
				if (streetsDir[i].equals(N)) {
					street.setDirectionLeft(NORD);
					street.setDirectionRight(SUD);
				} else if (streetsDir[i].equals(E)) {
					street.setDirectionLeft(EST);
					street.setDirectionRight(OVEST);
				} else if (streetsDir[i].equals(S)) {
					street.setDirectionLeft(SUD);
					street.setDirectionRight(NORD);
				} else if (streetsDir[i].equals(O)) {
					street.setDirectionLeft(OVEST);
					street.setDirectionRight(EST);
				} else if (streetsDir[i].equals(I)) {
					street.setDirectionLeft(INT);
					street.setDirectionRight(EXT);
				}
				mainDto.putStreet(street);
			}
		}
		return mainDto;
	}

	private void parseTraffic(MainDTO dto) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(5);
		params.add(new BasicNameValuePair(userKey, user));
		params.add(new BasicNameValuePair(pwdKey, pwd));
		params.add(new BasicNameValuePair(sqKey, sq));
		params.add(new BasicNameValuePair(typeKey, type));
		params.add(null);
		for (StreetDTO street : dto.getStreets())
			try {
				params.set(4, new BasicNameValuePair(roaKey, Integer.toString(street.getId())));
				HttpPost post = new HttpPost(traffic + Utility.getProviderTraffic(this) + path);
				post.setEntity(new UrlEncodedFormEntity(params));
				NodeList segments = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new GZIPInputStream(new DefaultHttpClient().execute(post).getEntity().getContent())).getDocumentElement().getLastChild().getFirstChild().getChildNodes();
				for (int i = 0; i < segments.getLength(); i++) {
					NodeList segChildrens = segments.item(i).getChildNodes();
					int from = Integer.parseInt(segChildrens.item(0).getTextContent());
					int to = Integer.parseInt(segChildrens.item(1).getTextContent());
					short speed = Short.parseShort(segChildrens.item(2).getTextContent());
					int fromIndex = street.getAllZonesId().indexOf(from);
					int toIndex = street.getAllZonesId().indexOf(to);
					if (fromIndex != -1 && toIndex != -1)
						if (fromIndex < toIndex) {
							for (int id : street.getAllZonesId().subList(fromIndex, toIndex))
								if (street.getZone(id) != null)
									street.getZone(id).setSpeedLeft(speed);
						} else
							for (int id : street.getAllZonesId().subList(toIndex, fromIndex))
								if (street.getZone(id) != null)
									street.getZone(id).setSpeedRight(speed);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	private void parseBadnews(MainDTO dto) throws Exception {
		SimpleDateFormat sdfBnParse = new SimpleDateFormat(TEMPLATE, Locale.ITALY);
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(URL);
		NodeList items = doc.getElementsByTagName(ITEM);
		for (int i = 0; i < items.getLength(); i++) {
			NodeList values = items.item(i).getChildNodes();
			String[] tris = new String[3];
			for (int j = 0; j < values.getLength(); j++) {
				if (values.item(j).getNodeName().equals(TITLE))
					tris[0] = values.item(j).getTextContent().split(SPACE)[0];
				else if (values.item(j).getNodeName().equals(DESCRIPTION))
					tris[1] = values.item(j).getTextContent();
				else if (values.item(j).getNodeName().equals(DC_DATE))
					tris[2] = values.item(j).getTextContent();
			}
			for (StreetDTO street : dto.getStreets())
				if (street.getTag().equals(tris[0])) {
					String[] desc = tris[1].split(NEWLINE);
					try {
						street.addBadNews(new BadNewsDTO(desc[0], desc[1] + desc[2], sdfBnParse.parse(tris[2])));
					} catch (Exception e) {
						street.addBadNews(new BadNewsDTO(desc[0], desc[1], new Date()));
					}
				}
		}
	}

	private void configureDTO(MainDTO currDTO, MainDTO pastDTO) {
		for (StreetDTO currStreet : currDTO.getStreets()) {
			byte availableLeft = 0, availableRight = 0;
			List<ZoneDTO> currZones = currStreet.getZones();
			for (ZoneDTO currZone : currZones) {
				if (currZone.getSpeedLeft() < 1)
					currZone.setCatLeft((byte) 0);
				else if (currZone.getSpeedLeft() < 11)
					currZone.setCatLeft((byte) 1);
				else if (currZone.getSpeedLeft() < 31)
					currZone.setCatLeft((byte) 2);
				else if (currZone.getSpeedLeft() < 51)
					currZone.setCatLeft((byte) 3);
				else if (currZone.getSpeedLeft() < 71)
					currZone.setCatLeft((byte) 4);
				else if (currZone.getSpeedLeft() < 91)
					currZone.setCatLeft((byte) 5);
				else
					currZone.setCatLeft((byte) 6);
				if (currZone.getSpeedRight() < 1)
					currZone.setCatRight((byte) 0);
				else if (currZone.getSpeedRight() < 11)
					currZone.setCatRight((byte) 1);
				else if (currZone.getSpeedRight() < 31)
					currZone.setCatRight((byte) 2);
				else if (currZone.getSpeedRight() < 51)
					currZone.setCatRight((byte) 3);
				else if (currZone.getSpeedRight() < 71)
					currZone.setCatRight((byte) 4);
				else if (currZone.getSpeedRight() < 91)
					currZone.setCatRight((byte) 5);
				else
					currZone.setCatRight((byte) 6);
				boolean congestionLeft = currZone.getCatLeft() > 0 && currZone.getCatLeft() <= currDTO.getCongestionThreshold();
				boolean congestionRight = currZone.getCatRight() > 0 && currZone.getCatRight() <= currDTO.getCongestionThreshold();
				if (congestionLeft && congestionRight)
					currDTO.addCongestedZone(currZone.getName());
				else if (congestionLeft)
					currDTO.addCongestedZone(currZone.getName());
				else if (congestionRight)
					currDTO.addCongestedZone(currZone.getName());
				if (pastDTO != null) {
					StreetDTO pastStreet = pastDTO.getStreet(currStreet.getId());
					if (pastStreet != null) {
						ZoneDTO pastZone = pastStreet.getZone(currZone.getId());
						if (pastZone != null) {
							int trendSpeed = Integer.parseInt(Utility.getTrendSpeed(this));
							if (currZone.getCatLeft() == 0 || pastZone.getCatLeft() == 0)
								currZone.setTrendLeft(0);
							else if (currZone.getSpeedLeft() - pastZone.getSpeedLeft() >= trendSpeed)
								currZone.setTrendLeft(R.drawable.speed_up);
							else if (pastZone.getSpeedLeft() - currZone.getSpeedLeft() >= trendSpeed)
								currZone.setTrendLeft(R.drawable.speed_down);
							else
								currZone.setTrendLeft(0);
							if (currZone.getCatRight() == 0 || pastZone.getCatRight() == 0)
								currZone.setTrendRight(0);
							else if (currZone.getSpeedRight() - pastZone.getSpeedRight() >= trendSpeed)
								currZone.setTrendRight(R.drawable.speed_up);
							else if (pastZone.getSpeedRight() - currZone.getSpeedRight() >= trendSpeed)
								currZone.setTrendRight(R.drawable.speed_down);
							else
								currZone.setTrendRight(0);
						}
					}
				}
				if (currZone.getCatLeft() != 0) {
					currStreet.addSpeedLeft(currZone.getSpeedLeft());
					availableLeft++;
				}
				if (currZone.getCatRight() != 0) {
					currStreet.addSpeedRight(currZone.getSpeedRight());
					availableRight++;
				}
			}
			if (availableLeft != 0)
				currStreet.setSpeedLeft((short) (currStreet.getSpeedLeft() / availableLeft));
			else
				currStreet.setSpeedLeft((short) 0);
			if (availableRight != 0)
				currStreet.setSpeedRight((short) (currStreet.getSpeedRight() / availableRight));
			else
				currStreet.setSpeedRight((short) 0);
			if (pastDTO != null) {
				StreetDTO pastStreet = pastDTO.getStreet(currStreet.getId());
				if (pastStreet != null) {
					int trendSpeed = Integer.parseInt(Utility.getTrendSpeed(this));
					if (currStreet.getSpeedLeft() == 0 || pastStreet.getSpeedLeft() == 0)
						currStreet.setTrendLeft(0);
					else if (currStreet.getSpeedLeft() - pastStreet.getSpeedLeft() >= trendSpeed)
						currStreet.setTrendLeft(R.drawable.speed_up);
					else if (pastStreet.getSpeedLeft() - currStreet.getSpeedLeft() >= trendSpeed)
						currStreet.setTrendLeft(R.drawable.speed_down);
					else
						currStreet.setTrendLeft(0);
					if (currStreet.getSpeedRight() == 0 || pastStreet.getSpeedRight() == 0)
						currStreet.setTrendRight(0);
					else if (currStreet.getSpeedRight() - pastStreet.getSpeedRight() >= trendSpeed)
						currStreet.setTrendRight(R.drawable.speed_up);
					else if (pastStreet.getSpeedRight() - currStreet.getSpeedRight() >= trendSpeed)
						currStreet.setTrendRight(R.drawable.speed_down);
					else
						currStreet.setTrendRight(0);
				}
			}
		}
	}

	private void setupNotification(String congestedZones) {
		if (congestedZones != null) {
			Builder bui = new Builder(this);
			bui.setDefaults(Notification.DEFAULT_ALL);
			bui.setSmallIcon(R.drawable.ic_stat_notify_trafficdroid);
			bui.setTicker(congestedZones);
			bui.setContentTitle(getString(R.string.notificationTitle));
			bui.setContentText(congestedZones);
			Intent intent = new Intent(this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
			bui.setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
			((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(TdService.notificationId, bui.getNotification());
		} else {
			((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(TdService.notificationId);
		}
	}
}
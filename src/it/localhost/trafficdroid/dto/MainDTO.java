package it.localhost.trafficdroid.dto;

import it.localhost.trafficdroid.common.Const;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<StreetDTO> streets;
	private Date trafficTime;
	private StringBuilder congestedZones;
	private int congestionThreshold;
	private List<BadNewsDTO> otherBadNews;

	public MainDTO() {
		streets = new ArrayList<StreetDTO>();
		otherBadNews = new ArrayList<BadNewsDTO>();
		congestedZones = new StringBuilder();
	}

	public Date getTrafficTime() {
		return trafficTime;
	}

	public void setTrafficTime(Date trafficTime) {
		this.trafficTime = trafficTime;
	}

	public void addStreet(StreetDTO street) {
		streets.add(street);
	}

	public List<StreetDTO> getStreets() {
		return streets;
	}

	public String getCongestedZones() {
		if (congestedZones.length() > 0)
			return congestedZones.substring(0, congestedZones.length() - 2);
		else
			return null;
	}

	public void addCongestedZone(String zone) {
		congestedZones.append(zone);
		congestedZones.append(Const.separator);
	}

	public int getCongestionThreshold() {
		return congestionThreshold;
	}

	public void setCongestionThreshold(int congestionThreshold) {
		this.congestionThreshold = congestionThreshold;
	}

	public List<BadNewsDTO> getOtherBadNews() {
		return otherBadNews;
	}

	public void addOtherBadNews(BadNewsDTO event) {
		otherBadNews.add(event);
	}
}

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
	private byte congestionThreshold;

	public MainDTO() {
		streets = new ArrayList<StreetDTO>();
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

	public byte getCongestionThreshold() {
		return congestionThreshold;
	}

	public void setCongestionThreshold(byte congestionThreshold) {
		this.congestionThreshold = congestionThreshold;
	}
}
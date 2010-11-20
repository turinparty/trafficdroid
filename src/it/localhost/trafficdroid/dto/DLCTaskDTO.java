package it.localhost.trafficdroid.dto;

import java.util.Date;
import java.util.List;

public class DLCTaskDTO {
	private List<StreetDTO> streets;
	private String url;
	private Date trafficTime;
	private StringBuilder congestedZones;
	private int congestionThreshold;

	public DLCTaskDTO(List<StreetDTO> streets, String url) {
		super();
		this.streets = streets;
		this.url = url;
		congestedZones = new StringBuilder();
	}

	public Date getTrafficTime() {
		return trafficTime;
	}

	public void setTrafficTime(Date trafficTime) {
		this.trafficTime = trafficTime;
	}

	public List<StreetDTO> getStreets() {
		return streets;
	}

	public String getUrl() {
		return url;
	}

	public String getCongestedZones() {
		if (congestedZones.length() > 0)
			return congestedZones.substring(0, congestedZones.length() - 2);
		else
			return "";
	}

	public void addCongestedZone(String zone) {
		congestedZones.append(zone);
		congestedZones.append("; ");
	}

	public int getCongestionThreshold() {
		return congestionThreshold;
	}

	public void setCongestionThreshold(int congestionThreshold) {
		this.congestionThreshold = congestionThreshold;
	}
}

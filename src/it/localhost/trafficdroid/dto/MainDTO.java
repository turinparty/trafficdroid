package it.localhost.trafficdroid.dto;

import it.localhost.trafficdroid.common.Const;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MainDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<StreetDTO> streets;
	private String url;
	private Date trafficTime;
	private StringBuilder congestedZones;
	private int congestionThreshold;

	public MainDTO(List<StreetDTO> streets, String url) {
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
			return null;
	}

	public void resetCongestedZones() {
		congestedZones.setLength(0);
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
}

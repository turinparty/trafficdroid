package it.localhost.trafficdroid.dto;

import java.util.Calendar;
import java.util.List;

public class DLCTaskDTO {
	private List<StreetDTO> streets;
	private String url;
	private Calendar now;

	public Calendar getNow() {
		return now;
	}

	public void setNow(Calendar now) {
		this.now = now;
	}

	public DLCTaskDTO(List<StreetDTO> streets, String url) {
		super();
		this.streets = streets;
		this.url = url;
	}

	public List<StreetDTO> getStreets() {
		return streets;
	}

	public String getUrl() {
		return url;
	}
}

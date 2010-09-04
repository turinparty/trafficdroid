package it.localhost.trafficdroid.dto;

import java.util.List;

public class DLCTaskDTO {
	private List<StreetDTO> streets;
	private String url;

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

package it.localhost.trafficdroid.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DLCTaskDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<StreetDTO> streets;
	private String url;
	private Date now;

	public DLCTaskDTO(List<StreetDTO> streets, String url) {
		super();
		this.streets = streets;
		this.url = url;
	}

	public Date getNow() {
		return now;
	}

	public void setNow(Date now) {
		this.now = now;
	}

	public List<StreetDTO> getStreets() {
		return streets;
	}

	public String getUrl() {
		return url;
	}
}

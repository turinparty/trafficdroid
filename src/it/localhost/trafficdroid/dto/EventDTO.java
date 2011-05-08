package it.localhost.trafficdroid.dto;

import java.io.Serializable;
import java.util.Date;

public class EventDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String description;
	private Date date;

	public EventDTO(String title, String description, Date date) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}
}

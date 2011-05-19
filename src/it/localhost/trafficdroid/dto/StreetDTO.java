package it.localhost.trafficdroid.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StreetDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	private List<ZoneDTO> zones;
	private String[] directions;
	private List<BadNewsDTO> events;

	public StreetDTO(int id) {
		super();
		this.id = id;
		zones = new ArrayList<ZoneDTO>();
		events = new ArrayList<BadNewsDTO>();
		directions = new String[2];
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public List<ZoneDTO> getZones() {
		return zones;
	}

	public void addEvent(BadNewsDTO event) {
		events.add(event);
	}

	public void addZone(ZoneDTO zone) {
		zones.add(zone);
	}

	public String getDirectionLeft() {
		return directions[0];
	}

	public String getDirectionRight() {
		return directions[1];
	}

	public void setDirectionLeft(String direction) {
		this.directions[0] = direction;
	}

	public void setDirectionRight(String direction) {
		this.directions[1] = direction;
	}

	public List<BadNewsDTO> getEvents() {
		return events;
	}

	@Override
	public String toString() {
		return getName();
	}
}
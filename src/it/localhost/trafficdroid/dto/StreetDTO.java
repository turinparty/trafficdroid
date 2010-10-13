package it.localhost.trafficdroid.dto;

import java.util.ArrayList;
import java.util.List;

public class StreetDTO {
	private String name;
	private int code;
	private int id;
	private List<ZoneDTO> zones;
	private String[] directions;

	public StreetDTO(int id, int code) {
		super();
		this.id = id;
		this.code = code;
		zones = new ArrayList<ZoneDTO>();
		directions = new String[2];
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

	public int getId() {
		return id;
	}

	public String getKey() {
		return "s" + code;
	}

	public List<ZoneDTO> getZones() {
		return zones;
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

	@Override
	public String toString() {
		return getName();
	}
}
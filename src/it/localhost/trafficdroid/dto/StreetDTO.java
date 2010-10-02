package it.localhost.trafficdroid.dto;

import java.util.ArrayList;
import java.util.List;

public class StreetDTO {
	private String name;
	private int code;
	private List<ZoneDTO> zones;
	private String[] directions;

	public StreetDTO(String name, int code) {
		super();
		this.name = name;
		this.code = code;
		zones = new ArrayList<ZoneDTO>();
		directions = new String[2];
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

	public List<ZoneDTO> getZones() {
		return zones;
	}

	public void addZone(ZoneDTO zone) {
		zones.add(zone);
	}

	public String getDirectionOne() {
		return directions[0];
	}

	public String getDirectionTwo() {
		return directions[1];
	}

	public void setDirectionOne(String direction) {
		this.directions[0] = direction;
	}

	public void setDirectionTwo(String direction) {
		this.directions[1] = direction;
	}

	@Override
	public String toString() {
		return getName();
	}
}
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

	public void setZones(List<ZoneDTO> zones) {
		this.zones = zones;
	}

	public void addZone(ZoneDTO zone) {
		zones.add(zone);
	}

	public String[] getDirections() {
		return directions;
	}

	public void setDirections(String[] directions) {
		this.directions = directions;
	}

	@Override
	public String toString() {
		return getName();
	}
}
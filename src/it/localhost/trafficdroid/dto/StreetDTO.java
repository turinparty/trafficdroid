package it.localhost.trafficdroid.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class StreetDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private String tag;
	private int id;
	private LinkedHashMap<String, ZoneDTO> zones;
	private ArrayList<BadNewsDTO> badNews;
	private String[] directions;

	public StreetDTO(int id) {
		this.id = id;
		zones = new LinkedHashMap<String, ZoneDTO>();
		badNews = new ArrayList<BadNewsDTO>();
		directions = new String[2];
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public ArrayList<ZoneDTO> getZones() {
		return new ArrayList<ZoneDTO>(zones.values());
	}

	public ZoneDTO getZone(String key) {
		return zones.get(key);
	}

	public int getZonesSize() {
		return zones.size();
	}

	public void addBadNews(BadNewsDTO event) {
		badNews.add(event);
	}

	public void putZone(ZoneDTO zone) {
		zones.put(zone.getName(), zone);
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

	public ArrayList<BadNewsDTO> getBadNews() {
		return badNews;
	}

	@Override
	public String toString() {
		return getName();
	}
}
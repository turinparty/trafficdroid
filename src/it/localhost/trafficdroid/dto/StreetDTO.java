package it.localhost.trafficdroid.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class StreetDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int id;
	private int graph;
	private ArrayList<ZoneDTO> zones;
	private String[] directions;
	private ArrayList<BadNewsDTO> badNews;

	public StreetDTO(int id) {
		this.id = id;
		zones = new ArrayList<ZoneDTO>();
		badNews = new ArrayList<BadNewsDTO>();
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

	public ArrayList<ZoneDTO> getZones() {
		return zones;
	}

	public void addBadNews(BadNewsDTO event) {
		badNews.add(event);
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

	public ArrayList<BadNewsDTO> getBadNews() {
		return badNews;
	}

	public int getGraph() {
		return graph;
	}

	public void setGraph(int graph) {
		this.graph = graph;
	}

	@Override
	public String toString() {
		return getName();
	}
}
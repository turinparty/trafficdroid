package it.localhost.trafficdroid.dto;

import java.io.Serializable;

public class ZoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String km;
	private byte speedLeft;
	private byte speedRight;
	private byte catLeft;
	private byte catRight;
	private int trendLeft;
	private int trendRight;

	public ZoneDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public byte getSpeedLeft() {
		return speedLeft;
	}

	public void setSpeedLeft(byte speedLeft) {
		this.speedLeft = speedLeft;
	}

	public byte getSpeedRight() {
		return speedRight;
	}

	public void setSpeedRight(byte speedRight) {
		this.speedRight = speedRight;
	}

	public byte getCatLeft() {
		return catLeft;
	}

	public void setCatLeft(byte catLeft) {
		this.catLeft = catLeft;
	}

	public byte getCatRight() {
		return catRight;
	}

	public void setCatRight(byte catRight) {
		this.catRight = catRight;
	}

	public int getTrendLeft() {
		return trendLeft;
	}

	public void setTrendLeft(int trendLeft) {
		this.trendLeft = trendLeft;
	}

	public int getTrendRight() {
		return trendRight;
	}

	public void setTrendRight(int trendRight) {
		this.trendRight = trendRight;
	}

	public String getKm() {
		return km;
	}

	public void setKm(String km) {
		this.km = km;
	}


}
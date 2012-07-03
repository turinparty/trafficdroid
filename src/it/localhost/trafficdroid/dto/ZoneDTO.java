package it.localhost.trafficdroid.dto;

import java.io.Serializable;

public class ZoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean[] autovelox = { false, false };
	private String name;
	private String km;
	private String webcam;
	private short speedLeft;
	private short speedRight;
	private byte catLeft;
	private byte catRight;
	private int trendLeft;
	private int trendRight;

	public ZoneDTO(int id, String name, String webcam) {
		this.id = id;
		this.name = name;
		this.webcam = webcam;
	}

	public void setAutoveloxLeft() {
		autovelox[0] = true;
	}

	public void setAutoveloxRight() {
		autovelox[1] = true;
	}

	public boolean isAutoveloxLeft() {
		return autovelox[0];
	}

	public boolean isAutoveloxRight() {
		return autovelox[1];
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public short getSpeedLeft() {
		return speedLeft;
	}

	public void setSpeedLeft(Short speedLeft) {
		this.speedLeft = speedLeft;
	}

	public short getSpeedRight() {
		return speedRight;
	}

	public void setSpeedRight(short speedRight) {
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

	public String getWebcam() {
		return webcam;
	}
}
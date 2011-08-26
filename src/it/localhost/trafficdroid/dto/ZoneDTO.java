package it.localhost.trafficdroid.dto;

import java.io.Serializable;

public class ZoneDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private int speedLeft;
	private int speedRight;
	private int catLeft;
	private int catRight;

	public ZoneDTO(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getSpeedLeft() {
		return speedLeft;
	}

	public void setSpeedLeft(int speedLeft) {
		this.speedLeft = speedLeft;
	}

	public int getSpeedRight() {
		return speedRight;
	}

	public void setSpeedRight(int speedRight) {
		this.speedRight = speedRight;
	}

	public int getCatLeft() {
		return catLeft;
	}

	public void setCatLeft(int catLeft) {
		this.catLeft = catLeft;
	}

	public int getCatRight() {
		return catRight;
	}

	public void setCatRight(int catRight) {
		this.catRight = catRight;
	}
}
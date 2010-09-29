package it.localhost.trafficdroid.dto;

public class ZoneDTO {
	private String name;
	private String speedLeft;
	private String speedRight;
	private int catLeft;
	private int catRight;

	public ZoneDTO(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getSpeedLeft() {
		return speedLeft;
	}

	public void setSpeedLeft(String speedLeft) {
		this.speedLeft = speedLeft;
	}

	public String getSpeedRight() {
		return speedRight;
	}

	public void setSpeedRight(String speedRight) {
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

	public String toString() {
		return getName() + ": " + getSpeedLeft() + " (" + getCatLeft() + "), " + getSpeedRight() + " (" + getCatRight() + ")";
	}
}

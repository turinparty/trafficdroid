package it.localhost.trafficdroid.dto;

public class StreetDTO {
	private String name;
	private boolean active;
	private int code;

	public StreetDTO(String name, boolean active, int code) {
		super();
		this.name = name;
		this.active = active;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return name;
	}
}

package it.localhost.trafficdroid.dto;

public class StreetDTO {
	private String name;
	private int code;

	public StreetDTO(String name, int code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return name;
	}
}
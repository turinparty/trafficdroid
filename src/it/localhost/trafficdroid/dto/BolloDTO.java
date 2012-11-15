package it.localhost.trafficdroid.dto;

public class BolloDTO {
	private String bollo;
	private String euro;

	public BolloDTO(String bollo, String euro) {
		super();
		this.bollo = bollo;
		this.euro = euro;
	}

	public String getBollo() {
		return bollo;
	}

	public String getEuro() {
		return euro;
	}
}

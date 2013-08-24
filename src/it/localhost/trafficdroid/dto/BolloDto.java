package it.localhost.trafficdroid.dto;

public class BolloDto extends BaseDto {
	String bollo;

	public BolloDto(boolean success, String bollo) {
		super(success);
		this.bollo = bollo;
	}

	public String getBollo() {
		return bollo;
	}
}

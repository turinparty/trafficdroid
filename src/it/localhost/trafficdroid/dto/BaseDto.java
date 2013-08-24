package it.localhost.trafficdroid.dto;

public class BaseDto {
	private boolean success;
	private String message;

	protected BaseDto(boolean success) {
		this.success = success;
	}

	public BaseDto(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}

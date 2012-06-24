package it.localhost.trafficdroid.exception;

public class GenericException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final String exceptionCheck = "exceptionCheck";
	public static final String exceptionMsg = "exceptionMsg";

	public GenericException(Throwable throwable) {
		super(throwable);
	}
}

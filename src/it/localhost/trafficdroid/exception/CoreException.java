package it.localhost.trafficdroid.exception;

public class CoreException extends Exception {
	public static final int DaoException = 0;
	private static final long serialVersionUID = 1L;
	private int key;

	public CoreException(int key, String msg) {
		super(msg);
		this.key = key;
	}

	public int getKey() {
		return key;
	}
}

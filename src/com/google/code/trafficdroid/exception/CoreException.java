package com.google.code.trafficdroid.exception;

public class CoreException extends Exception {
	public static final int DaoException = 0;
	private static final long serialVersionUID = 1L;
	private int key;
	private String msg;

	public CoreException(int key, String msg) {
		this.key = key;
		this.msg = msg;
	}

	public int getKey() {
		return key;
	}

	public String getMsg() {
		return msg;
	}
}

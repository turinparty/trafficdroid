package com.google.code.trafficdroid.exception;

public class DaoException extends Exception {
	public static final int SAXException = 0;
	public static final int IOException = 1;
	public static final int ParserConfigurationException = 2;
	public static final int FactoryConfigurationError = 3;
	public static final int MalformedURLException = 4;
	private static final long serialVersionUID = 1L;
	private int key;
	private String msg;

	public DaoException(int key, String msg) {
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

package it.localhost.trafficdroid.exception;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final int SAXException = 0;
	public static final int IOException = 1;
	public static final int ParserConfigurationException = 2;
	public static final int FactoryConfigurationError = 3;
	public static final int MalformedURLException = 4;
	public static final int FileNotFoundException = 5;
	public static final int ClassNotFoundException = 6;
	public static final int OptionalDataException = 7;
	public static final int StreamCorruptedException = 8;
	private int key;

	public DaoException(int key, String msg) {
		super(msg);
		this.key = key;
	}

	public int getKey() {
		return key;
	}
}

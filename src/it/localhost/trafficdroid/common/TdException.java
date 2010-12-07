package it.localhost.trafficdroid.common;

public class TdException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final int SAXException = 0;
	public static final int IOException = 1;
	public static final int ParserConfigurationException = 2;
	public static final int FactoryConfigurationError = 3;
	public static final int MalformedURLException = 4;
	public static final int FileNotFoundException = 5;
	public static final int OptionalDataException = 6;
	public static final int StreamCorruptedException = 7;
	public static final int ClassNotFoundException = 8;
	private int key;

	public TdException(int key, String msg) {
		super(msg);
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public String getName() {
		switch (key) {
		case SAXException:
			return "SAXException";
		case IOException:
			return "IOException";
		case ParserConfigurationException:
			return "ParserConfigurationException";
		case FactoryConfigurationError:
			return "FactoryConfigurationError";
		case MalformedURLException:
			return "MalformedURLException";
		case FileNotFoundException:
			return "FileNotFoundException";
		case OptionalDataException:
			return "OptionalDataException";
		case StreamCorruptedException:
			return "StreamCorruptedException";
		case ClassNotFoundException:
			return "ClassNotFoundException";
		default:
			return "Unknown Exception";
		}
	}
}

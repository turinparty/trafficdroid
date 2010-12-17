package it.localhost.trafficdroid.common;

public class TdException extends Exception {
	private static final long serialVersionUID = 1L;
	public static final byte SAXException = 0;
	public static final byte IOException = 1;
	public static final byte ParserConfigurationException = 2;
	public static final byte FactoryConfigurationError = 3;
	public static final byte MalformedURLException = 4;
	public static final byte FileNotFoundException = 5;
	public static final byte OptionalDataException = 6;
	public static final byte StreamCorruptedException = 7;
	public static final byte ClassNotFoundException = 8;
	public static final byte ClientProtocolException = 9;
	public static final byte IndexOutOfBoundsException = 10;
	private byte key;

	public TdException(byte key, String msg) {
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
		case ClientProtocolException:
			return "ClientProtocolException";
		case IndexOutOfBoundsException:
			return "IndexOutOfBoundsException";
		default:
			return "Unknown Exception";
		}
	}
}

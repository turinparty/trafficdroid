package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dto.DLCTaskDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.content.Context;

public class TrafficDAO {
	public static Document getData(int mapId, String url) throws TdException {
		try {
			URL u = new URL(Const.http + url + Const.dyn + mapId + Const.html);
			InputStream is = u.openConnection().getInputStream();
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		} catch (MalformedURLException e) {
			throw new TdException(TdException.MalformedURLException, e.getMessage());
		} catch (SAXException e) {
			throw new TdException(TdException.SAXException, e.getMessage());
		} catch (IOException e) {
			throw new TdException(TdException.IOException, e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new TdException(TdException.ParserConfigurationException, e.getMessage());
		} catch (FactoryConfigurationError e) {
			throw new TdException(TdException.FactoryConfigurationError, e.getMessage());
		}
	}

//	public static void storeData(DLCTaskDTO dto, Context ctx) throws TdException {
//		FileOutputStream fos;
//		try {
//			fos = ctx.openFileOutput(Const.tdData, Context.MODE_PRIVATE);
//			ObjectOutputStream out = new ObjectOutputStream(fos);
//			out.writeObject(dto);
//			out.close();
//			fos.close();
//		} catch (FileNotFoundException e) {
//			throw new TdException(TdException.FileNotFoundException, e.getMessage());
//		} catch (IOException e) {
//			throw new TdException(TdException.IOException, e.getMessage());
//		}
//	}
//
//	public static DLCTaskDTO retrieveData(Context ctx) throws TdException {
//		DLCTaskDTO dlctask = null;
//		try {
//			FileInputStream fis = ctx.openFileInput(Const.tdData);
//			ObjectInputStream in = new ObjectInputStream(fis);
//			dlctask = (DLCTaskDTO) in.readObject();
//			in.close();
//			fis.close();
//		} catch (StreamCorruptedException e) {
//			throw new TdException(TdException.StreamCorruptedException, e.getMessage());
//		} catch (OptionalDataException e) {
//			throw new TdException(TdException.OptionalDataException, e.getMessage());
//		} catch (FileNotFoundException e) {
//			throw new TdException(TdException.FileNotFoundException, e.getMessage());
//		} catch (IOException e) {
//			throw new TdException(TdException.IOException, e.getMessage());
//		} catch (ClassNotFoundException e) {
//			throw new TdException(TdException.ClassNotFoundException, e.getMessage());
//		}
//		return dlctask;
//	}
}

package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.DLCTaskDTO;
import it.localhost.trafficdroid.exception.DaoException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.content.Context;

public class TrafficDAO {
	public static Document getData(int mapId, String url) throws DaoException {
		try {
			URL u = new URL(Const.http + url + Const.dyn + mapId + Const.html);
			URLConnection uc = u.openConnection();
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(uc.getInputStream());
		} catch (MalformedURLException e) {
			throw new DaoException(DaoException.MalformedURLException, e.getMessage());
		} catch (SAXException e) {
			throw new DaoException(DaoException.SAXException, e.getMessage());
		} catch (IOException e) {
			throw new DaoException(DaoException.IOException, e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new DaoException(DaoException.ParserConfigurationException, e.getMessage());
		} catch (FactoryConfigurationError e) {
			throw new DaoException(DaoException.FactoryConfigurationError, e.getMessage());
		}
	}

	public static void storeData(DLCTaskDTO dto, Context ctx) throws DaoException {
		FileOutputStream fos;
		try {
			fos = ctx.openFileOutput(Const.tdData, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(dto);
			out.close();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new DaoException(DaoException.FileNotFoundException, e.getMessage());
		} catch (IOException e) {
			throw new DaoException(DaoException.IOException, e.getMessage());
		}
	}

	public static DLCTaskDTO retrieveData(Context ctx) throws DaoException {
		DLCTaskDTO dlctask = null;
		try {
			FileInputStream fis = ctx.openFileInput(Const.tdData);
			ObjectInputStream in = new ObjectInputStream(fis);
			dlctask = (DLCTaskDTO) in.readObject();
			in.close();
			fis.close();
		} catch (StreamCorruptedException e) {
			throw new DaoException(DaoException.StreamCorruptedException, e.getMessage());
		} catch (OptionalDataException e) {
			throw new DaoException(DaoException.OptionalDataException, e.getMessage());
		} catch (FileNotFoundException e) {
			throw new DaoException(DaoException.FileNotFoundException, e.getMessage());
		} catch (IOException e) {
			throw new DaoException(DaoException.IOException, e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new DaoException(DaoException.ClassNotFoundException, e.getMessage());
		}
		return dlctask;
	}
}

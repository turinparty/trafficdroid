package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.GenericException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import android.content.Context;
import android.content.res.Resources;

public class MainDAO {
	public static MainDTO create() {
		MainDTO mainDto = new MainDTO();
		Resources resources = TdApp.getContext().getResources();
		mainDto.setCongestionThreshold(Byte.parseByte(TdApp.getPrefString(R.string.notificationSpeedKey, R.string.notificationSpeedDefault)));
		int[] streetsId = resources.getIntArray(R.array.streetsId);
		int[] streetsGraph = resources.getIntArray(R.array.streetsGraph);
		String[] streetsName = resources.getStringArray(R.array.streetsName);
		for (int i = 0; i < streetsId.length; i++) {
			StreetDTO street = new StreetDTO(streetsId[i]);
			boolean streetEnabled = TdApp.getPrefBoolean(Integer.toString(street.getId()), false);
			String[] zonesId = resources.getStringArray(Const.zonesResId.get((streetsId[i])));
			String[] zonesName = resources.getStringArray(Const.zonesResName.get(streetsId[i]));
			String[] zonesAutovelox = resources.getStringArray(Const.zonesResAutovelox.get(streetsId[i]));
			for (int j = 0; j < zonesId.length; j++)
				if (streetEnabled || TdApp.getPrefBoolean(zonesId[j], false))
					street.addZone(new ZoneDTO(zonesId[j], zonesName[j], zonesAutovelox[j]));
			if (street.getZones().size() > 0) {
				street.setName(streetsName[i]);
				street.setGraph(streetsGraph[i]);
				mainDto.addStreet(street);
			}
		}
		return mainDto;
	}

	public static void store(MainDTO dto) throws GenericException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = TdApp.getContext().openFileOutput(Const.tdData, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(dto);
		} catch (FileNotFoundException e) {
			throw new GenericException(e);
		} catch (IOException e) {
			throw new GenericException(e);
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				// Do nothing
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				// Do nothing
			}
		}
	}

	public static MainDTO retrieve() throws GenericException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = TdApp.getContext().openFileInput(Const.tdData);
			ois = new ObjectInputStream(fis);
			MainDTO dlctask = (MainDTO) ois.readObject();
			return dlctask;
		} catch (FileNotFoundException e) {
			throw new GenericException(e);
		} catch (StreamCorruptedException e) {
			throw new GenericException(e);
		} catch (IOException e) {
			throw new GenericException(e);
		} catch (ClassNotFoundException e) {
			throw new GenericException(e);
		}
	}
}
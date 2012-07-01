package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.common.ZoneArray;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;
import it.localhost.trafficdroid.exception.BadConfException;
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
	public static final String tdData = "trafficData";
	private static final String versionMismatch = "Version Mismatch";

	public static MainDTO create() {
		MainDTO mainDto = new MainDTO();
		mainDto.setVersionCode(TdApp.getVersionCode());
		Resources resources = TdApp.getContext().getResources();
		mainDto.setCongestionThreshold(Byte.parseByte(TdApp.getPrefString(R.string.notificationSpeedKey, R.string.notificationSpeedDefault)));
		int[] streetsId = resources.getIntArray(R.array.streetId);
		String[] streetsName = resources.getStringArray(R.array.streetName);
		String[] streetsTag = resources.getStringArray(R.array.streetTag);
		int[] autoveloxStreet = resources.getIntArray(R.array.autoveloxStreet);
		int[] autoveloxFrom = resources.getIntArray(R.array.autoveloxFrom);
		int[] autoveloxTo = resources.getIntArray(R.array.autoveloxTo);
		boolean allStreets = TdApp.getPrefBoolean(TdApp.getContext().getString(R.string.allStreetsKey), false);
		for (int i = 0; i < streetsId.length; i++) {
			StreetDTO street = new StreetDTO(streetsId[i]);
			boolean streetEnabled = TdApp.getPrefBoolean(Integer.toString(street.getId()), false);
			int[] zonesId = resources.getIntArray(ZoneArray.zonesResId.get((streetsId[i])));
			String[] zonesWebcam = resources.getStringArray(ZoneArray.zonesResWebcam.get((streetsId[i])));
			String[] zonesName = resources.getStringArray(ZoneArray.zonesResName.get(streetsId[i]));
			for (int j = 0; j < zonesId.length; j++)
				if (allStreets || streetEnabled || TdApp.getPrefBoolean(Integer.toString(zonesId[j]), false)) {
					ZoneDTO zone = new ZoneDTO(zonesId[j], zonesName[j], zonesWebcam[j]);
					for (int k = 0; k < autoveloxStreet.length; k++) {
						if (autoveloxStreet[k] == streetsId[i] && zonesId[j] >= autoveloxFrom[k] && zonesId[j] < autoveloxTo[k])
							zone.setAutoveloxLeft();
						if (autoveloxStreet[k] == streetsId[i] && zonesId[j] >= autoveloxTo[k] && zonesId[j] < autoveloxFrom[k])
							zone.setAutoveloxRight();
					}
					street.putZone(zone);
				}
			if (street.getZonesSize() > 0) {
				street.setName(streetsName[i]);
				street.setTag(streetsTag[i]);
				mainDto.putStreet(street);
			}
		}
		return mainDto;
	}

	public static void store(MainDTO dto) throws GenericException {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = TdApp.getContext().openFileOutput(tdData, Context.MODE_PRIVATE);
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

	public static MainDTO retrieve() throws GenericException, BadConfException {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = TdApp.getContext().openFileInput(tdData);
			ois = new ObjectInputStream(fis);
			MainDTO dlctask = (MainDTO) ois.readObject();
			if (dlctask.getVersionCode() != TdApp.getVersionCode())
				throw new BadConfException(versionMismatch);
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
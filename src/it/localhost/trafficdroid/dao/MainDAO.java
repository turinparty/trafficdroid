package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdException;
import it.localhost.trafficdroid.dto.MainDTO;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;

public class MainDAO {
	public static MainDTO create(Context ctx) {
		MainDTO mainDto = new MainDTO();
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
		Resources resources = ctx.getResources();
		mainDto.setPrefCount(sharedPreferences.getInt(Const.prefCountKey, 0));
		mainDto.setCongestionThreshold(Byte.parseByte(sharedPreferences.getString(resources.getString(R.string.notificationSpeedKey), resources.getString(R.string.notificationSpeedDefault))));
		int[] streetsId = resources.getIntArray(R.array.streetsId);
		String[] streetsName = resources.getStringArray(R.array.streetsName);
		for (int i = 0; i < streetsId.length; i++) {
			StreetDTO street = new StreetDTO(streetsId[i]);
			boolean streetEnabled = sharedPreferences.getBoolean(Integer.toString(street.getId()), false);
			String[][] zones = new String[2][];
			zones[0] = resources.getStringArray(Const.zonesRes.get((streetsId[i])));
			zones[1] = resources.getStringArray(Const.zonesRes.get(0 - streetsId[i]));
			for (int j = 0; j < zones[0].length; j++)
				if (streetEnabled || sharedPreferences.getBoolean(zones[0][j], false))
					street.addZone(new ZoneDTO(zones[0][j], zones[1][j]));
			if (street.getZones().size() > 0) {
				street.setName(streetsName[i]);
				mainDto.addStreet(street);
			}
		}
		return mainDto;
	}

	public static void store(MainDTO dto, Context context) throws TdException {
		try {
			FileOutputStream fos = context.openFileOutput(Const.tdData, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dto);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			throw new TdException(TdException.FileNotFoundException, e.getMessage());
		} catch (IOException e) {
			throw new TdException(TdException.IOException, e.getMessage());
		}
	}

	public static MainDTO retrieve(Context context) {
		try {
			FileInputStream fis = context.openFileInput(Const.tdData);
			ObjectInputStream ois = new ObjectInputStream(fis);
			MainDTO dlctask = (MainDTO) ois.readObject();
			ois.close();
			fis.close();
			return dlctask;
		} catch (Exception e) {
			return null;
		}
	}
}
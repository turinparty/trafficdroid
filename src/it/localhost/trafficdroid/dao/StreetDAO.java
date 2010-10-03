package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.content.res.Resources;

public class StreetDAO {
	private static ArrayList<StreetDTO> streets;
	private static StreetDTO street;
	private static boolean allStreet;
	private static int[][] zones;

	public static ArrayList<StreetDTO> getAllEnabled(SharedPreferences settings, Resources resources) {
		streets = new ArrayList<StreetDTO>();
		zones = new int[2][];
		zones[0] = new int[] { 1, R.string.s1, R.string.s1z1, R.string.s1z2, R.string.s1z3, R.string.s1z4, R.string.s1z5, R.string.s1z6, R.string.s1z7, R.string.s1z8, R.string.s1z9, R.string.s1z10, R.string.s1z11, R.string.s1z12, R.string.s1z13, R.string.s1z14, R.string.s1z15, R.string.s1z16, R.string.s1z17, R.string.s1z18, R.string.s1z19, R.string.s1z20, R.string.s1z21, R.string.s1z22, R.string.s1z23, R.string.s1z24, R.string.s1z25, R.string.s1z26, R.string.s1z27, R.string.s1z28, R.string.s1z29, R.string.s1z30, R.string.s1z31, R.string.s1z32, R.string.s1z33, R.string.s1z34, R.string.s1z35, R.string.s1z36, R.string.s1z37, R.string.s1z38, R.string.s1z39, R.string.s1z40, R.string.s1z41, R.string.s1z42, R.string.s1z43, R.string.s1z44, R.string.s1z45, R.string.s1z46, R.string.s1z47, R.string.s1z48, R.string.s1z49, R.string.s1z50, R.string.s1z51, R.string.s1z52, R.string.s1z53, R.string.s1z54, R.string.s1z55, R.string.s1z56, R.string.s1z57, R.string.s1z58, R.string.s1z59, R.string.s1z60, R.string.s1z61, R.string.s1z62 };
		zones[1] = new int[] { 10, R.string.s10, R.string.s10z1, R.string.s10z2, R.string.s10z3, R.string.s10z4, R.string.s10z5, R.string.s10z6, R.string.s10z7, R.string.s10z8, R.string.s10z9, R.string.s10z10, R.string.s10z11, R.string.s10z12, R.string.s10z13, R.string.s10z14, R.string.s10z15, R.string.s10z16, R.string.s10z17, R.string.s10z18, R.string.s10z19, R.string.s10z20, R.string.s10z21, R.string.s10z22, R.string.s10z23, R.string.s10z24 };
		for (int s = 0; s < zones.length; s++) {
			street = new StreetDTO(resources.getString(zones[s][1]), zones[s][0]);
			allStreet = settings.getBoolean(resources.getString(zones[s][1]), false);
			for (int i = 2; i < zones[s].length; i++)
				if (allStreet || settings.getBoolean(resources.getString(zones[s][i]), false))
					street.addZone(new ZoneDTO(resources.getString(zones[s][i])));
			if (street.getZones().size() > 0)
				streets.add(street);
		}
		return streets;
	}
}
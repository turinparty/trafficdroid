package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.StreetDTO;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.content.res.Resources;

public class StreetDAO {
	private static ArrayList<StreetDTO> streets;

	public static ArrayList<StreetDTO> getAllEnabled(SharedPreferences settings, Resources resources) {
		streets = new ArrayList<StreetDTO>();
		if (settings.getBoolean(resources.getString(R.string.s1), false))
			streets.add(new StreetDTO(resources.getString(R.string.s1), 1));
		if (settings.getBoolean(resources.getString(R.string.s2), false))
			streets.add(new StreetDTO(resources.getString(R.string.s2), 2));
		if (settings.getBoolean(resources.getString(R.string.s3), false))
			streets.add(new StreetDTO(resources.getString(R.string.s3), 3));
		if (settings.getBoolean(resources.getString(R.string.s4), false))
			streets.add(new StreetDTO(resources.getString(R.string.s4), 4));
		if (settings.getBoolean(resources.getString(R.string.s5), false))
			streets.add(new StreetDTO(resources.getString(R.string.s5), 5));
		if (settings.getBoolean(resources.getString(R.string.s6), false))
			streets.add(new StreetDTO(resources.getString(R.string.s6), 6));
		if (settings.getBoolean(resources.getString(R.string.s7), false))
			streets.add(new StreetDTO(resources.getString(R.string.s7), 7));
		if (settings.getBoolean(resources.getString(R.string.s8), false))
			streets.add(new StreetDTO(resources.getString(R.string.s8), 8));
		if (settings.getBoolean(resources.getString(R.string.s9), false))
			streets.add(new StreetDTO(resources.getString(R.string.s9), 9));
		if (settings.getBoolean(resources.getString(R.string.s10), false))
			streets.add(new StreetDTO(resources.getString(R.string.s10), 10));
		if (settings.getBoolean(resources.getString(R.string.s11), false))
			streets.add(new StreetDTO(resources.getString(R.string.s11), 11));
		if (settings.getBoolean(resources.getString(R.string.s121), false))
			streets.add(new StreetDTO(resources.getString(R.string.s121), 121));
		if (settings.getBoolean(resources.getString(R.string.s12), false))
			streets.add(new StreetDTO(resources.getString(R.string.s12), 12));
		if (settings.getBoolean(resources.getString(R.string.s13), false))
			streets.add(new StreetDTO(resources.getString(R.string.s13), 13));
		if (settings.getBoolean(resources.getString(R.string.s14), false))
			streets.add(new StreetDTO(resources.getString(R.string.s14), 14));
		if (settings.getBoolean(resources.getString(R.string.s15), false))
			streets.add(new StreetDTO(resources.getString(R.string.s15), 15));
		if (settings.getBoolean(resources.getString(R.string.s16), false))
			streets.add(new StreetDTO(resources.getString(R.string.s16), 16));
		if (settings.getBoolean(resources.getString(R.string.s18), false))
			streets.add(new StreetDTO(resources.getString(R.string.s18), 18));
		if (settings.getBoolean(resources.getString(R.string.s19), false))
			streets.add(new StreetDTO(resources.getString(R.string.s19), 19));
		if (settings.getBoolean(resources.getString(R.string.s20), false))
			streets.add(new StreetDTO(resources.getString(R.string.s20), 20));
		if (settings.getBoolean(resources.getString(R.string.s21), false))
			streets.add(new StreetDTO(resources.getString(R.string.s21), 21));
		if (settings.getBoolean(resources.getString(R.string.s22), false))
			streets.add(new StreetDTO(resources.getString(R.string.s22), 22));
		if (settings.getBoolean(resources.getString(R.string.s23), false))
			streets.add(new StreetDTO(resources.getString(R.string.s23), 23));
		if (settings.getBoolean(resources.getString(R.string.s24), false))
			streets.add(new StreetDTO(resources.getString(R.string.s24), 24));
		if (settings.getBoolean(resources.getString(R.string.s241), false))
			streets.add(new StreetDTO(resources.getString(R.string.s241), 241));
		if (settings.getBoolean(resources.getString(R.string.s25), false))
			streets.add(new StreetDTO(resources.getString(R.string.s25), 25));
		if (settings.getBoolean(resources.getString(R.string.s26), false))
			streets.add(new StreetDTO(resources.getString(R.string.s26), 26));
		if (settings.getBoolean(resources.getString(R.string.s27), false))
			streets.add(new StreetDTO(resources.getString(R.string.s27), 27));
		if (settings.getBoolean(resources.getString(R.string.s28), false))
			streets.add(new StreetDTO(resources.getString(R.string.s28), 28));
		if (settings.getBoolean(resources.getString(R.string.s29), false))
			streets.add(new StreetDTO(resources.getString(R.string.s29), 29));
		if (settings.getBoolean(resources.getString(R.string.s30), false))
			streets.add(new StreetDTO(resources.getString(R.string.s30), 30));
		if (settings.getBoolean(resources.getString(R.string.s31), false))
			streets.add(new StreetDTO(resources.getString(R.string.s31), 31));
		if (settings.getBoolean(resources.getString(R.string.s32), false))
			streets.add(new StreetDTO(resources.getString(R.string.s32), 32));
		if (settings.getBoolean(resources.getString(R.string.s50), false))
			streets.add(new StreetDTO(resources.getString(R.string.s50), 50));
		if (settings.getBoolean(resources.getString(R.string.s51), false))
			streets.add(new StreetDTO(resources.getString(R.string.s51), 51));
		if (settings.getBoolean(resources.getString(R.string.s52), false))
			streets.add(new StreetDTO(resources.getString(R.string.s52), 52));
		if (settings.getBoolean(resources.getString(R.string.s55), false))
			streets.add(new StreetDTO(resources.getString(R.string.s55), 55));
		if (settings.getBoolean(resources.getString(R.string.s90), false))
			streets.add(new StreetDTO(resources.getString(R.string.s90), 90));
		if (settings.getBoolean(resources.getString(R.string.s91), false))
			streets.add(new StreetDTO(resources.getString(R.string.s91), 91));
		if (settings.getBoolean(resources.getString(R.string.s101), false))
			streets.add(new StreetDTO(resources.getString(R.string.s101), 101));
		if (settings.getBoolean(resources.getString(R.string.s102), false))
			streets.add(new StreetDTO(resources.getString(R.string.s102), 102));
		if (settings.getBoolean(resources.getString(R.string.s143), false))
			streets.add(new StreetDTO(resources.getString(R.string.s143), 143));
		if (settings.getBoolean(resources.getString(R.string.s103), false))
			streets.add(new StreetDTO(resources.getString(R.string.s103), 103));
		if (settings.getBoolean(resources.getString(R.string.s302), false))
			streets.add(new StreetDTO(resources.getString(R.string.s302), 302));
		if (settings.getBoolean(resources.getString(R.string.s303), false))
			streets.add(new StreetDTO(resources.getString(R.string.s303), 303));
		if (settings.getBoolean(resources.getString(R.string.s501), false))
			streets.add(new StreetDTO(resources.getString(R.string.s501), 501));
		if (settings.getBoolean(resources.getString(R.string.s701), false))
			streets.add(new StreetDTO(resources.getString(R.string.s701), 701));
		if (settings.getBoolean(resources.getString(R.string.s111), false))
			streets.add(new StreetDTO(resources.getString(R.string.s111), 111));
		if (settings.getBoolean(resources.getString(R.string.s131), false))
			streets.add(new StreetDTO(resources.getString(R.string.s131), 131));
		if (settings.getBoolean(resources.getString(R.string.s142), false))
			streets.add(new StreetDTO(resources.getString(R.string.s142), 142));
		if (settings.getBoolean(resources.getString(R.string.s141), false))
			streets.add(new StreetDTO(resources.getString(R.string.s141), 141));
		if (settings.getBoolean(resources.getString(R.string.s144), false))
			streets.add(new StreetDTO(resources.getString(R.string.s144), 144));
		if (settings.getBoolean(resources.getString(R.string.s161), false))
			streets.add(new StreetDTO(resources.getString(R.string.s161), 161));
		if (settings.getBoolean(resources.getString(R.string.s181), false))
			streets.add(new StreetDTO(resources.getString(R.string.s181), 181));
		if (settings.getBoolean(resources.getString(R.string.s211), false))
			streets.add(new StreetDTO(resources.getString(R.string.s211), 211));
		if (settings.getBoolean(resources.getString(R.string.s261), false))
			streets.add(new StreetDTO(resources.getString(R.string.s261), 261));
		if (settings.getBoolean(resources.getString(R.string.s262), false))
			streets.add(new StreetDTO(resources.getString(R.string.s262), 262));
		if (settings.getBoolean(resources.getString(R.string.s263), false))
			streets.add(new StreetDTO(resources.getString(R.string.s263), 263));
		if (settings.getBoolean(resources.getString(R.string.s291), false))
			streets.add(new StreetDTO(resources.getString(R.string.s291), 291));
		if (settings.getBoolean(resources.getString(R.string.s552), false))
			streets.add(new StreetDTO(resources.getString(R.string.s552), 552));
		if (settings.getBoolean(resources.getString(R.string.s551), false))
			streets.add(new StreetDTO(resources.getString(R.string.s551), 551));
		if (settings.getBoolean(resources.getString(R.string.s553), false))
			streets.add(new StreetDTO(resources.getString(R.string.s553), 553));
		if (settings.getBoolean(resources.getString(R.string.s56), false))
			streets.add(new StreetDTO(resources.getString(R.string.s56), 56));
		if (settings.getBoolean(resources.getString(R.string.s301), false))
			streets.add(new StreetDTO(resources.getString(R.string.s301), 301));
		return streets;
	}
}
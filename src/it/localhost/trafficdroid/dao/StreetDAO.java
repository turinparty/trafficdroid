package it.localhost.trafficdroid.dao;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.StreetDTO;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.content.res.Resources;

public class StreetDAO {
	private static ArrayList<StreetDTO> streets;
	private static int[] streetsId;
	private static String[] streetsName;
	private static String[][][] zones;

	public static ArrayList<StreetDTO> getAllEnabled(SharedPreferences settings, Resources resources) {
		initializze(resources);
		streets = new ArrayList<StreetDTO>();
		StreetDTO street;
		for (int i = 0; i < streetsId.length; i++) {
			street = new StreetDTO(streetsId[i]);
			boolean streetEnabled = settings.getBoolean(Integer.toString(street.getId()), false);
			for (int j = 0; j < zones[0][i].length; j++)
				if (streetEnabled || settings.getBoolean(zones[0][i][j], false))
					street.addZone(new ZoneDTO(zones[1][i][j]));
			if (street.getZones().size() > 0) {
				street.setName(streetsName[i]);
				streets.add(street);
			}
		}
		return streets;
	}

	private static void initializze(Resources resources) {
		streetsId = resources.getIntArray(R.array.streetsId);
		streetsName = resources.getStringArray(R.array.streetsName);
		zones = new String[64][2][];
		zones[0][0] = resources.getStringArray(R.array.zones1Id);
		zones[0][1] = resources.getStringArray(R.array.zones2Id);
		zones[0][2] = resources.getStringArray(R.array.zones3Id);
		zones[0][3] = resources.getStringArray(R.array.zones4Id);
		zones[0][4] = resources.getStringArray(R.array.zones5Id);
		zones[0][5] = resources.getStringArray(R.array.zones6Id);
		zones[0][6] = resources.getStringArray(R.array.zones7Id);
		zones[0][7] = resources.getStringArray(R.array.zones8Id);
		zones[0][8] = resources.getStringArray(R.array.zones9Id);
		zones[0][9] = resources.getStringArray(R.array.zones10Id);
		zones[0][10] = resources.getStringArray(R.array.zones11Id);
		zones[0][11] = resources.getStringArray(R.array.zones12Id);
		zones[0][12] = resources.getStringArray(R.array.zones13Id);
		zones[0][13] = resources.getStringArray(R.array.zones14Id);
		zones[0][14] = resources.getStringArray(R.array.zones15Id);
		zones[0][15] = resources.getStringArray(R.array.zones16Id);
		zones[0][16] = resources.getStringArray(R.array.zones18Id);
		zones[0][17] = resources.getStringArray(R.array.zones19Id);
		zones[0][18] = resources.getStringArray(R.array.zones20Id);
		zones[0][19] = resources.getStringArray(R.array.zones21Id);
		zones[0][20] = resources.getStringArray(R.array.zones22Id);
		zones[0][21] = resources.getStringArray(R.array.zones23Id);
		zones[0][22] = resources.getStringArray(R.array.zones24Id);
		zones[0][23] = resources.getStringArray(R.array.zones25Id);
		zones[0][24] = resources.getStringArray(R.array.zones26Id);
		zones[0][25] = resources.getStringArray(R.array.zones27Id);
		zones[0][26] = resources.getStringArray(R.array.zones28Id);
		zones[0][27] = resources.getStringArray(R.array.zones29Id);
		zones[0][28] = resources.getStringArray(R.array.zones30Id);
		zones[0][29] = resources.getStringArray(R.array.zones31Id);
		zones[0][30] = resources.getStringArray(R.array.zones32Id);
		zones[0][31] = resources.getStringArray(R.array.zones50Id);
		zones[0][32] = resources.getStringArray(R.array.zones51Id);
		zones[0][33] = resources.getStringArray(R.array.zones52Id);
		zones[0][34] = resources.getStringArray(R.array.zones55Id);
		zones[0][35] = resources.getStringArray(R.array.zones56Id);
		zones[0][36] = resources.getStringArray(R.array.zones90Id);
		zones[0][37] = resources.getStringArray(R.array.zones91Id);
		zones[0][38] = resources.getStringArray(R.array.zones101Id);
		zones[0][39] = resources.getStringArray(R.array.zones102Id);
		zones[0][40] = resources.getStringArray(R.array.zones103Id);
		zones[0][41] = resources.getStringArray(R.array.zones111Id);
		zones[0][42] = resources.getStringArray(R.array.zones121Id);
		zones[0][43] = resources.getStringArray(R.array.zones131Id);
		zones[0][44] = resources.getStringArray(R.array.zones141Id);
		zones[0][45] = resources.getStringArray(R.array.zones142Id);
		zones[0][46] = resources.getStringArray(R.array.zones143Id);
		zones[0][47] = resources.getStringArray(R.array.zones144Id);
		zones[0][48] = resources.getStringArray(R.array.zones161Id);
		zones[0][49] = resources.getStringArray(R.array.zones181Id);
		zones[0][50] = resources.getStringArray(R.array.zones211Id);
		zones[0][51] = resources.getStringArray(R.array.zones241Id);
		zones[0][52] = resources.getStringArray(R.array.zones261Id);
		zones[0][53] = resources.getStringArray(R.array.zones262Id);
		zones[0][54] = resources.getStringArray(R.array.zones263Id);
		zones[0][55] = resources.getStringArray(R.array.zones291Id);
		zones[0][56] = resources.getStringArray(R.array.zones301Id);
		zones[0][57] = resources.getStringArray(R.array.zones302Id);
		zones[0][58] = resources.getStringArray(R.array.zones303Id);
		zones[0][59] = resources.getStringArray(R.array.zones501Id);
		zones[0][60] = resources.getStringArray(R.array.zones551Id);
		zones[0][61] = resources.getStringArray(R.array.zones552Id);
		zones[0][62] = resources.getStringArray(R.array.zones553Id);
		zones[0][63] = resources.getStringArray(R.array.zones701Id);
		zones[1][0] = resources.getStringArray(R.array.zones1Name);
		zones[1][1] = resources.getStringArray(R.array.zones2Name);
		zones[1][2] = resources.getStringArray(R.array.zones3Name);
		zones[1][3] = resources.getStringArray(R.array.zones4Name);
		zones[1][4] = resources.getStringArray(R.array.zones5Name);
		zones[1][5] = resources.getStringArray(R.array.zones6Name);
		zones[1][6] = resources.getStringArray(R.array.zones7Name);
		zones[1][7] = resources.getStringArray(R.array.zones8Name);
		zones[1][8] = resources.getStringArray(R.array.zones9Name);
		zones[1][9] = resources.getStringArray(R.array.zones10Name);
		zones[1][10] = resources.getStringArray(R.array.zones11Name);
		zones[1][11] = resources.getStringArray(R.array.zones12Name);
		zones[1][12] = resources.getStringArray(R.array.zones13Name);
		zones[1][13] = resources.getStringArray(R.array.zones14Name);
		zones[1][14] = resources.getStringArray(R.array.zones15Name);
		zones[1][15] = resources.getStringArray(R.array.zones16Name);
		zones[1][16] = resources.getStringArray(R.array.zones18Name);
		zones[1][17] = resources.getStringArray(R.array.zones19Name);
		zones[1][18] = resources.getStringArray(R.array.zones20Name);
		zones[1][19] = resources.getStringArray(R.array.zones21Name);
		zones[1][20] = resources.getStringArray(R.array.zones22Name);
		zones[1][21] = resources.getStringArray(R.array.zones23Name);
		zones[1][22] = resources.getStringArray(R.array.zones24Name);
		zones[1][23] = resources.getStringArray(R.array.zones25Name);
		zones[1][24] = resources.getStringArray(R.array.zones26Name);
		zones[1][25] = resources.getStringArray(R.array.zones27Name);
		zones[1][26] = resources.getStringArray(R.array.zones28Name);
		zones[1][27] = resources.getStringArray(R.array.zones29Name);
		zones[1][28] = resources.getStringArray(R.array.zones30Name);
		zones[1][29] = resources.getStringArray(R.array.zones31Name);
		zones[1][30] = resources.getStringArray(R.array.zones32Name);
		zones[1][31] = resources.getStringArray(R.array.zones50Name);
		zones[1][32] = resources.getStringArray(R.array.zones51Name);
		zones[1][33] = resources.getStringArray(R.array.zones52Name);
		zones[1][34] = resources.getStringArray(R.array.zones55Name);
		zones[1][35] = resources.getStringArray(R.array.zones56Name);
		zones[1][36] = resources.getStringArray(R.array.zones90Name);
		zones[1][37] = resources.getStringArray(R.array.zones91Name);
		zones[1][38] = resources.getStringArray(R.array.zones101Name);
		zones[1][39] = resources.getStringArray(R.array.zones102Name);
		zones[1][40] = resources.getStringArray(R.array.zones103Name);
		zones[1][41] = resources.getStringArray(R.array.zones111Name);
		zones[1][42] = resources.getStringArray(R.array.zones121Name);
		zones[1][43] = resources.getStringArray(R.array.zones131Name);
		zones[1][44] = resources.getStringArray(R.array.zones141Name);
		zones[1][45] = resources.getStringArray(R.array.zones142Name);
		zones[1][46] = resources.getStringArray(R.array.zones143Name);
		zones[1][47] = resources.getStringArray(R.array.zones144Name);
		zones[1][48] = resources.getStringArray(R.array.zones161Name);
		zones[1][49] = resources.getStringArray(R.array.zones181Name);
		zones[1][50] = resources.getStringArray(R.array.zones211Name);
		zones[1][51] = resources.getStringArray(R.array.zones241Name);
		zones[1][52] = resources.getStringArray(R.array.zones261Name);
		zones[1][53] = resources.getStringArray(R.array.zones262Name);
		zones[1][54] = resources.getStringArray(R.array.zones263Name);
		zones[1][55] = resources.getStringArray(R.array.zones291Name);
		zones[1][56] = resources.getStringArray(R.array.zones301Name);
		zones[1][57] = resources.getStringArray(R.array.zones302Name);
		zones[1][58] = resources.getStringArray(R.array.zones303Name);
		zones[1][59] = resources.getStringArray(R.array.zones501Name);
		zones[1][60] = resources.getStringArray(R.array.zones551Name);
		zones[1][61] = resources.getStringArray(R.array.zones552Name);
		zones[1][62] = resources.getStringArray(R.array.zones553Name);
		zones[1][63] = resources.getStringArray(R.array.zones701Name);
	}
}
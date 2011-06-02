package it.localhost.trafficdroid.common;

import it.localhost.trafficdroid.R;
import android.content.Intent;

public class Const {
	public static final int[] colorCat = new int[] { 0xffffffff, 0xffff0000, 0xffff0000, 0xffff8000, 0xffffff00, 0xff47ffff, 0xff00ff00 };
	public static final int menuSettings = 1;
	public static final int menuRefresh = 2;
	public static final int notificationId = 1;
	public static final char A = 'A';
	public static final char wcm = 'A';
	public static final String openRound = " (";
	public static final String closeRound = ")";
	public static final String equal = "=";
	public static final String separator = "; ";
	public static final String domain = "; domain=";
	public static final String http = "http://";
	public static final String dyn = "/dyn/";
	public static final String html = ".html?ts=1";
	public static final String popupTelecamera = "/autostrade-mobile/popupTelecamera.do?tlc=";
	public static final String events = "/portale/rss?rsstype=traffic";
	public static final String cookie = "/autostrade/traffico.do";
	public static final String codeDiv = "div";
	public static final String codeId = "id";
	public static final String codeTable = "table";
	public static final String codeSection = "section";
	public static final String codeRoadbox = "roadbox";
	public static final String codeClass = "class";
	public static final String tdData = "trafficData";
	public static final String doUpdate = "it.localhost.trafficdroid.DO_UPDATE";
	public static final String beginUpdate = "it.localhost.trafficdroid.BEGIN_UPDATE";
	public static final String endUpdate = "it.localhost.trafficdroid.END_UPDATE";
	public static final String scheduleService = "it.localhost.trafficdroid.SCHEDULE_SERVICE";
	public static final String wakeLock = "it.localhost.trafficdroid.WAKELOCK";
	public static final Intent doUpdateIntent = new Intent(Const.doUpdate);
	public static final Intent beginUpdateIntent = new Intent(Const.beginUpdate);
	public static final Intent endUpdateIntent = new Intent(Const.endUpdate);
	public static final Intent scheduleServiceIntent = new Intent(Const.scheduleService);
	public static final String camId = "camId";
	public static final String formatDateEventi = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String adMobId = "a14d8f6a3c4c2f3";
	public static final String item = "item";
	public static final int[] streetsRes = new int[] { R.array.streetsId, R.array.streetsName };

	public static final int[][] zonesRes() {
		int[][] zonesRes = new int[2][64];
		zonesRes[0][0] = R.array.zones1Id;
		zonesRes[0][1] = R.array.zones2Id;
		zonesRes[0][2] = R.array.zones3Id;
		zonesRes[0][3] = R.array.zones4Id;
		zonesRes[0][4] = R.array.zones5Id;
		zonesRes[0][5] = R.array.zones6Id;
		zonesRes[0][6] = R.array.zones7Id;
		zonesRes[0][7] = R.array.zones8Id;
		zonesRes[0][8] = R.array.zones9Id;
		zonesRes[0][9] = R.array.zones10Id;
		zonesRes[0][10] = R.array.zones11Id;
		zonesRes[0][11] = R.array.zones12Id;
		zonesRes[0][12] = R.array.zones13Id;
		zonesRes[0][13] = R.array.zones14Id;
		zonesRes[0][14] = R.array.zones15Id;
		zonesRes[0][15] = R.array.zones16Id;
		zonesRes[0][16] = R.array.zones18Id;
		zonesRes[0][17] = R.array.zones19Id;
		zonesRes[0][18] = R.array.zones20Id;
		zonesRes[0][19] = R.array.zones21Id;
		zonesRes[0][20] = R.array.zones22Id;
		zonesRes[0][21] = R.array.zones23Id;
		zonesRes[0][22] = R.array.zones24Id;
		zonesRes[0][23] = R.array.zones25Id;
		zonesRes[0][24] = R.array.zones26Id;
		zonesRes[0][25] = R.array.zones27Id;
		zonesRes[0][26] = R.array.zones28Id;
		zonesRes[0][27] = R.array.zones29Id;
		zonesRes[0][28] = R.array.zones30Id;
		zonesRes[0][29] = R.array.zones31Id;
		zonesRes[0][30] = R.array.zones32Id;
		zonesRes[0][31] = R.array.zones50Id;
		zonesRes[0][32] = R.array.zones51Id;
		zonesRes[0][33] = R.array.zones52Id;
		zonesRes[0][34] = R.array.zones55Id;
		zonesRes[0][35] = R.array.zones56Id;
		zonesRes[0][36] = R.array.zones90Id;
		zonesRes[0][37] = R.array.zones91Id;
		zonesRes[0][38] = R.array.zones101Id;
		zonesRes[0][39] = R.array.zones102Id;
		zonesRes[0][40] = R.array.zones103Id;
		zonesRes[0][41] = R.array.zones111Id;
		zonesRes[0][42] = R.array.zones121Id;
		zonesRes[0][43] = R.array.zones131Id;
		zonesRes[0][44] = R.array.zones141Id;
		zonesRes[0][45] = R.array.zones142Id;
		zonesRes[0][46] = R.array.zones143Id;
		zonesRes[0][47] = R.array.zones144Id;
		zonesRes[0][48] = R.array.zones161Id;
		zonesRes[0][49] = R.array.zones181Id;
		zonesRes[0][50] = R.array.zones211Id;
		zonesRes[0][51] = R.array.zones241Id;
		zonesRes[0][52] = R.array.zones261Id;
		zonesRes[0][53] = R.array.zones262Id;
		zonesRes[0][54] = R.array.zones263Id;
		zonesRes[0][55] = R.array.zones291Id;
		zonesRes[0][56] = R.array.zones301Id;
		zonesRes[0][57] = R.array.zones302Id;
		zonesRes[0][58] = R.array.zones303Id;
		zonesRes[0][59] = R.array.zones501Id;
		zonesRes[0][60] = R.array.zones551Id;
		zonesRes[0][61] = R.array.zones552Id;
		zonesRes[0][62] = R.array.zones553Id;
		zonesRes[0][63] = R.array.zones701Id;
		zonesRes[1][0] = R.array.zones1Name;
		zonesRes[1][1] = R.array.zones2Name;
		zonesRes[1][2] = R.array.zones3Name;
		zonesRes[1][3] = R.array.zones4Name;
		zonesRes[1][4] = R.array.zones5Name;
		zonesRes[1][5] = R.array.zones6Name;
		zonesRes[1][6] = R.array.zones7Name;
		zonesRes[1][7] = R.array.zones8Name;
		zonesRes[1][8] = R.array.zones9Name;
		zonesRes[1][9] = R.array.zones10Name;
		zonesRes[1][10] = R.array.zones11Name;
		zonesRes[1][11] = R.array.zones12Name;
		zonesRes[1][12] = R.array.zones13Name;
		zonesRes[1][13] = R.array.zones14Name;
		zonesRes[1][14] = R.array.zones15Name;
		zonesRes[1][15] = R.array.zones16Name;
		zonesRes[1][16] = R.array.zones18Name;
		zonesRes[1][17] = R.array.zones19Name;
		zonesRes[1][18] = R.array.zones20Name;
		zonesRes[1][19] = R.array.zones21Name;
		zonesRes[1][20] = R.array.zones22Name;
		zonesRes[1][21] = R.array.zones23Name;
		zonesRes[1][22] = R.array.zones24Name;
		zonesRes[1][23] = R.array.zones25Name;
		zonesRes[1][24] = R.array.zones26Name;
		zonesRes[1][25] = R.array.zones27Name;
		zonesRes[1][26] = R.array.zones28Name;
		zonesRes[1][27] = R.array.zones29Name;
		zonesRes[1][28] = R.array.zones30Name;
		zonesRes[1][29] = R.array.zones31Name;
		zonesRes[1][30] = R.array.zones32Name;
		zonesRes[1][31] = R.array.zones50Name;
		zonesRes[1][32] = R.array.zones51Name;
		zonesRes[1][33] = R.array.zones52Name;
		zonesRes[1][34] = R.array.zones55Name;
		zonesRes[1][35] = R.array.zones56Name;
		zonesRes[1][36] = R.array.zones90Name;
		zonesRes[1][37] = R.array.zones91Name;
		zonesRes[1][38] = R.array.zones101Name;
		zonesRes[1][39] = R.array.zones102Name;
		zonesRes[1][40] = R.array.zones103Name;
		zonesRes[1][41] = R.array.zones111Name;
		zonesRes[1][42] = R.array.zones121Name;
		zonesRes[1][43] = R.array.zones131Name;
		zonesRes[1][44] = R.array.zones141Name;
		zonesRes[1][45] = R.array.zones142Name;
		zonesRes[1][46] = R.array.zones143Name;
		zonesRes[1][47] = R.array.zones144Name;
		zonesRes[1][48] = R.array.zones161Name;
		zonesRes[1][49] = R.array.zones181Name;
		zonesRes[1][50] = R.array.zones211Name;
		zonesRes[1][51] = R.array.zones241Name;
		zonesRes[1][52] = R.array.zones261Name;
		zonesRes[1][53] = R.array.zones262Name;
		zonesRes[1][54] = R.array.zones263Name;
		zonesRes[1][55] = R.array.zones291Name;
		zonesRes[1][56] = R.array.zones301Name;
		zonesRes[1][57] = R.array.zones302Name;
		zonesRes[1][58] = R.array.zones303Name;
		zonesRes[1][59] = R.array.zones501Name;
		zonesRes[1][60] = R.array.zones551Name;
		zonesRes[1][61] = R.array.zones552Name;
		zonesRes[1][62] = R.array.zones553Name;
		zonesRes[1][63] = R.array.zones701Name;
		return zonesRes;
	}
}
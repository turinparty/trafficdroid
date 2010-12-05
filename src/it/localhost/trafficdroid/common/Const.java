package it.localhost.trafficdroid.common;

import android.content.Intent;

public class Const {
	public static final int[] colorCat = new int[] {0xffffffff, 0xffff0000, 0xffff0000, 0xffff8000, 0xffffff00, 0xff47ffff, 0xff00ff00 };
	public static final int menuSettings = 1;
	public static final int menuRefresh = 2;
	public static final int notificationId = 1;
	public static final String emptyString = "";
	public static final String http = "http://";
	public static final String dyn = "/dyn/";
	public static final String html = ".html?ts=1";
	public static final String codeDiv = "div";
	public static final String codeId = "id";
	public static final String codeTable = "table";
	public static final String codeSection = "section";
	public static final String codeRoadbox = "roadbox";
	public static final String codeClass = "class";
	public static final String BEGIN_UPDATE = "it.localhost.trafficdroid.BEGIN_UPDATE";
	public static final String END_UPDATE = "it.localhost.trafficdroid.END_UPDATE";
	public static final String DO_UPDATE = "it.localhost.trafficdroid.DO_UPDATE";
	public static final String PREF_EXIT = "it.localhost.trafficdroid.PREF_EXIT";
	public static final Intent beginUpdateIntent = new Intent(Const.BEGIN_UPDATE);
	public static final Intent endUpdateIntent = new Intent(Const.END_UPDATE);
	public static final Intent doUpdateIntent = new Intent(Const.DO_UPDATE);
	public static final Intent prefExitIntent = new Intent(Const.PREF_EXIT);
	public static final String defaultUpdateInterval = "1800";
	public static final String defaultCongestionThreshold = "1";
	public static final String tdData = "trafficData";
}

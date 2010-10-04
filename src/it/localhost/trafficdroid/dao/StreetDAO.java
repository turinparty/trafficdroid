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
		zones = new int[64][];
		zones[0] = new int[] { 1, R.string.s1, R.string.s1z1, R.string.s1z2, R.string.s1z3, R.string.s1z4, R.string.s1z5, R.string.s1z6, R.string.s1z7, R.string.s1z8, R.string.s1z9, R.string.s1z10, R.string.s1z11, R.string.s1z12, R.string.s1z13, R.string.s1z14, R.string.s1z15, R.string.s1z16, R.string.s1z17, R.string.s1z18, R.string.s1z19, R.string.s1z20, R.string.s1z21, R.string.s1z22, R.string.s1z23, R.string.s1z24, R.string.s1z25, R.string.s1z26, R.string.s1z27, R.string.s1z28, R.string.s1z29, R.string.s1z30, R.string.s1z31, R.string.s1z32, R.string.s1z33, R.string.s1z34, R.string.s1z35, R.string.s1z36, R.string.s1z37, R.string.s1z38, R.string.s1z39, R.string.s1z40, R.string.s1z41, R.string.s1z42, R.string.s1z43, R.string.s1z44, R.string.s1z45, R.string.s1z46, R.string.s1z47, R.string.s1z48, R.string.s1z49, R.string.s1z50, R.string.s1z51, R.string.s1z52, R.string.s1z53, R.string.s1z54, R.string.s1z55, R.string.s1z56, R.string.s1z57, R.string.s1z58, R.string.s1z59, R.string.s1z60, R.string.s1z61, R.string.s1z62 };
		zones[1] = new int[] { 10, R.string.s10, R.string.s10z1, R.string.s10z2, R.string.s10z3, R.string.s10z4, R.string.s10z5, R.string.s10z6, R.string.s10z7, R.string.s10z8, R.string.s10z9, R.string.s10z10, R.string.s10z11, R.string.s10z12, R.string.s10z13, R.string.s10z14, R.string.s10z15, R.string.s10z16, R.string.s10z17, R.string.s10z18, R.string.s10z19, R.string.s10z20, R.string.s10z21, R.string.s10z22, R.string.s10z23, R.string.s10z24 };
		zones[2] = new int[] { 101, R.string.s101, R.string.s101z1, R.string.s101z2, R.string.s101z3, R.string.s101z4 };
		zones[3] = new int[] { 102, R.string.s102, R.string.s102z1, R.string.s102z2, R.string.s102z3, R.string.s102z4, R.string.s102z5 };
		zones[4] = new int[] { 103, R.string.s103, R.string.s103z1, R.string.s103z2, R.string.s103z3, R.string.s103z4, R.string.s103z5, R.string.s103z6, R.string.s103z7, R.string.s103z8, R.string.s103z9, R.string.s103z10, R.string.s103z11, R.string.s103z12, R.string.s103z13 };
		zones[5] = new int[] { 11, R.string.s11, R.string.s11z1, R.string.s11z2, R.string.s11z3, R.string.s11z4, R.string.s11z5, R.string.s11z6, R.string.s11z7, R.string.s11z8, R.string.s11z9, R.string.s11z10, R.string.s11z11, R.string.s11z12 };
		zones[6] = new int[] { 111, R.string.s111, R.string.s111z1, R.string.s111z2, R.string.s111z3, R.string.s111z4, R.string.s111z5 };
		zones[7] = new int[] { 12, R.string.s12, R.string.s12z1, R.string.s12z2, R.string.s12z3, R.string.s12z4, R.string.s12z5, R.string.s12z6, R.string.s12z7, R.string.s12z8 };
		zones[8] = new int[] { 121, R.string.s121, R.string.s121z1, R.string.s121z2, R.string.s121z3, R.string.s121z4, R.string.s121z5, R.string.s121z6, R.string.s121z7, R.string.s121z8, R.string.s121z9, R.string.s121z10, R.string.s121z11, R.string.s121z12, R.string.s121z13, R.string.s121z14, R.string.s121z15, R.string.s121z16, R.string.s121z17, R.string.s121z18, R.string.s121z19, R.string.s121z20, R.string.s121z21, R.string.s121z22 };
		zones[9] = new int[] { 13, R.string.s13, R.string.s13z1, R.string.s13z2, R.string.s13z3, R.string.s13z4, R.string.s13z5, R.string.s13z6, R.string.s13z7, R.string.s13z8, R.string.s13z9, R.string.s13z10, R.string.s13z11, R.string.s13z12, R.string.s13z13 };
		zones[10] = new int[] { 131, R.string.s131, R.string.s131z1, R.string.s131z2, R.string.s131z3, R.string.s131z4, R.string.s131z5, R.string.s131z6, R.string.s131z7, R.string.s131z8, R.string.s131z9, R.string.s131z10, R.string.s131z11, R.string.s131z12 };
		zones[11] = new int[] { 14, R.string.s14, R.string.s14z1, R.string.s14z2, R.string.s14z3, R.string.s14z4, R.string.s14z5, R.string.s14z6, R.string.s14z7, R.string.s14z8, R.string.s14z9, R.string.s14z10, R.string.s14z11, R.string.s14z12, R.string.s14z13, R.string.s14z14, R.string.s14z15, R.string.s14z16, R.string.s14z17, R.string.s14z18, R.string.s14z19, R.string.s14z20, R.string.s14z21, R.string.s14z22, R.string.s14z23, R.string.s14z24, R.string.s14z25, R.string.s14z26, R.string.s14z27, R.string.s14z28, R.string.s14z29, R.string.s14z30, R.string.s14z31, R.string.s14z32, R.string.s14z33, R.string.s14z34, R.string.s14z35, R.string.s14z36, R.string.s14z37, R.string.s14z38, R.string.s14z39, R.string.s14z40, R.string.s14z41, R.string.s14z42, R.string.s14z43, R.string.s14z44, R.string.s14z45, R.string.s14z46, R.string.s14z47, R.string.s14z48, R.string.s14z49, R.string.s14z50, R.string.s14z51, R.string.s14z52, R.string.s14z53, R.string.s14z54, R.string.s14z55, R.string.s14z56, R.string.s14z57, R.string.s14z58 };
		zones[12] = new int[] { 141, R.string.s141, R.string.s141z1, R.string.s141z2, R.string.s141z3 };
		zones[13] = new int[] { 142, R.string.s142, R.string.s142z1, R.string.s142z2, R.string.s142z3, R.string.s142z4 };
		zones[14] = new int[] { 143, R.string.s143, R.string.s143z1, R.string.s143z2, R.string.s143z3, R.string.s143z4, R.string.s143z5, R.string.s143z6, R.string.s143z7, R.string.s143z8, R.string.s143z9, R.string.s143z10, R.string.s143z11, R.string.s143z12, R.string.s143z13, R.string.s143z14, R.string.s143z15, R.string.s143z16, R.string.s143z17 };
		zones[15] = new int[] { 144, R.string.s144, R.string.s144z1, R.string.s144z2, R.string.s144z3, R.string.s144z4, R.string.s144z5, R.string.s144z6, R.string.s144z7, R.string.s144z8 };
		zones[16] = new int[] { 15, R.string.s15, R.string.s15z1, R.string.s15z2, R.string.s15z3, R.string.s15z4, R.string.s15z5, R.string.s15z6, R.string.s15z7, R.string.s15z8 };
		zones[17] = new int[] { 16, R.string.s16, R.string.s16z1, R.string.s16z2, R.string.s16z3, R.string.s16z4, R.string.s16z5, R.string.s16z6, R.string.s16z7, R.string.s16z8, R.string.s16z9, R.string.s16z10, R.string.s16z11, R.string.s16z12, R.string.s16z13 };
		zones[18] = new int[] { 161, R.string.s161, R.string.s161z1, R.string.s161z2, R.string.s161z3, R.string.s161z4, R.string.s161z5 };
		zones[19] = new int[] { 18, R.string.s18, R.string.s18z1, R.string.s18z2, R.string.s18z3, R.string.s18z4, R.string.s18z5, R.string.s18z6, R.string.s18z7, R.string.s18z8 };
		zones[20] = new int[] { 181, R.string.s181, R.string.s181z1, R.string.s181z2, R.string.s181z3, R.string.s181z4, R.string.s181z5, R.string.s181z6 };
		zones[21] = new int[] { 19, R.string.s19, R.string.s19z1, R.string.s19z2, R.string.s19z3, R.string.s19z4, R.string.s19z5, R.string.s19z6, R.string.s19z7, R.string.s19z8, R.string.s19z9, R.string.s19z10, R.string.s19z11, R.string.s19z12, R.string.s19z13, R.string.s19z14, R.string.s19z15, R.string.s19z16, R.string.s19z17, R.string.s19z18, R.string.s19z19, R.string.s19z20, R.string.s19z21, R.string.s19z22 };
		zones[22] = new int[] { 2, R.string.s2, R.string.s2z1, R.string.s2z2, R.string.s2z3, R.string.s2z4, R.string.s2z5, R.string.s2z6, R.string.s2z7, R.string.s2z8, R.string.s2z9, R.string.s2z10, R.string.s2z11, R.string.s2z12, R.string.s2z13, R.string.s2z14, R.string.s2z15, R.string.s2z16, R.string.s2z17 };
		zones[23] = new int[] { 20, R.string.s20, R.string.s20z1, R.string.s20z2, R.string.s20z3, R.string.s20z4, R.string.s20z5, R.string.s20z6, R.string.s20z7, R.string.s20z8, R.string.s20z9, R.string.s20z10, R.string.s20z11, R.string.s20z12, R.string.s20z13, R.string.s20z14, R.string.s20z15, R.string.s20z16, R.string.s20z17, R.string.s20z18, R.string.s20z19 };
		zones[24] = new int[] { 21, R.string.s21, R.string.s21z1, R.string.s21z2, R.string.s21z3, R.string.s21z4, R.string.s21z5, R.string.s21z6, R.string.s21z7, R.string.s21z8, R.string.s21z9, R.string.s21z10, R.string.s21z11, R.string.s21z12, R.string.s21z13, R.string.s21z14, R.string.s21z15, R.string.s21z16, R.string.s21z17, R.string.s21z18, R.string.s21z19, R.string.s21z20, R.string.s21z21, R.string.s21z22 };
		zones[25] = new int[] { 211, R.string.s211, R.string.s211z1, R.string.s211z2, R.string.s211z3 };
		zones[26] = new int[] { 22, R.string.s22, R.string.s22z1, R.string.s22z2, R.string.s22z3, R.string.s22z4, R.string.s22z5, R.string.s22z6, R.string.s22z7, R.string.s22z8, R.string.s22z9, R.string.s22z10, R.string.s22z11, R.string.s22z12, R.string.s22z13, R.string.s22z14, R.string.s22z15, R.string.s22z16, R.string.s22z17, R.string.s22z18, R.string.s22z19, R.string.s22z20, R.string.s22z21, R.string.s22z22, R.string.s22z23, R.string.s22z24, R.string.s22z25, R.string.s22z26 };
		zones[27] = new int[] { 23, R.string.s23, R.string.s23z1, R.string.s23z2, R.string.s23z3, R.string.s23z4, R.string.s23z5, R.string.s23z6, R.string.s23z7, R.string.s23z8, R.string.s23z9, R.string.s23z10 };
		zones[28] = new int[] { 24, R.string.s24, R.string.s24z1, R.string.s24z2, R.string.s24z3, R.string.s24z4, R.string.s24z5, R.string.s24z6, R.string.s24z7, R.string.s24z8, R.string.s24z9, R.string.s24z10, R.string.s24z11, R.string.s24z12, R.string.s24z13, R.string.s24z14, R.string.s24z15, R.string.s24z16, R.string.s24z17, R.string.s24z18 };
		zones[29] = new int[] { 241, R.string.s241, R.string.s241z1, R.string.s241z2, R.string.s241z3, R.string.s241z4, R.string.s241z5 };
		zones[30] = new int[] { 25, R.string.s25, R.string.s25z1, R.string.s25z2, R.string.s25z3, R.string.s25z4, R.string.s25z5, R.string.s25z6, R.string.s25z7, R.string.s25z8, R.string.s25z9, R.string.s25z10, R.string.s25z11, R.string.s25z12, R.string.s25z13 };
		zones[31] = new int[] { 26, R.string.s26, R.string.s26z1, R.string.s26z2, R.string.s26z3, R.string.s26z4, R.string.s26z5, R.string.s26z6, R.string.s26z7, R.string.s26z8, R.string.s26z9, R.string.s26z10, R.string.s26z11, R.string.s26z12, R.string.s26z13, R.string.s26z14, R.string.s26z15, R.string.s26z16, R.string.s26z17, R.string.s26z18 };
		zones[32] = new int[] { 261, R.string.s261, R.string.s261z1, R.string.s261z2, R.string.s261z3 };
		zones[33] = new int[] { 262, R.string.s262, R.string.s262z1, R.string.s262z2, R.string.s262z3, R.string.s262z4, R.string.s262z5, R.string.s262z6 };
		zones[34] = new int[] { 263, R.string.s263, R.string.s263z1, R.string.s263z2, R.string.s263z3 };
		zones[35] = new int[] { 27, R.string.s27, R.string.s27z1, R.string.s27z2, R.string.s27z3, R.string.s27z4, R.string.s27z5, R.string.s27z6, R.string.s27z7, R.string.s27z8, R.string.s27z9, R.string.s27z10 };
		zones[36] = new int[] { 28, R.string.s28, R.string.s28z1, R.string.s28z2, R.string.s28z3, R.string.s28z4, R.string.s28z5, R.string.s28z6, R.string.s28z7, R.string.s28z8, R.string.s28z9, R.string.s28z10, R.string.s28z11 };
		zones[37] = new int[] { 29, R.string.s29, R.string.s29z1, R.string.s29z2, R.string.s29z3, R.string.s29z4, R.string.s29z5, R.string.s29z6, R.string.s29z7, R.string.s29z8, R.string.s29z9, R.string.s29z10, R.string.s29z11, R.string.s29z12, R.string.s29z13, R.string.s29z14, R.string.s29z15, R.string.s29z16, R.string.s29z17, R.string.s29z18 };
		zones[38] = new int[] { 291, R.string.s291, R.string.s291z1, R.string.s291z2, R.string.s291z3, R.string.s291z4, R.string.s291z5, R.string.s291z6, R.string.s291z7, R.string.s291z8 };
		zones[39] = new int[] { 3, R.string.s3, R.string.s3z1, R.string.s3z2, R.string.s3z3, R.string.s3z4, R.string.s3z5, R.string.s3z6, R.string.s3z7, R.string.s3z8, R.string.s3z9, R.string.s3z10, R.string.s3z11, R.string.s3z12, R.string.s3z13, R.string.s3z14, R.string.s3z15, R.string.s3z16, R.string.s3z17, R.string.s3z18, R.string.s3z19, R.string.s3z20, R.string.s3z21, R.string.s3z22, R.string.s3z23, R.string.s3z24, R.string.s3z25, R.string.s3z26, R.string.s3z27, R.string.s3z28, R.string.s3z29, R.string.s3z30, R.string.s3z31, R.string.s3z32, R.string.s3z33, R.string.s3z34, R.string.s3z35, R.string.s3z36, R.string.s3z37, R.string.s3z38, R.string.s3z39, R.string.s3z40, R.string.s3z41, R.string.s3z42, R.string.s3z43, R.string.s3z44, R.string.s3z45, R.string.s3z46, R.string.s3z47, R.string.s3z48, R.string.s3z49, R.string.s3z50 };
		zones[40] = new int[] { 30, R.string.s30, R.string.s30z1, R.string.s30z2, R.string.s30z3, R.string.s30z4, R.string.s30z5, R.string.s30z6, R.string.s30z7 };
		zones[41] = new int[] { 301, R.string.s301, R.string.s301z1, R.string.s301z2, R.string.s301z3, R.string.s301z4 };
		zones[42] = new int[] { 302, R.string.s302, R.string.s302z1, R.string.s302z2, R.string.s302z3, R.string.s302z4, R.string.s302z5, R.string.s302z6, R.string.s302z7, R.string.s302z8, R.string.s302z9, R.string.s302z10 };
		zones[43] = new int[] { 303, R.string.s303, R.string.s303z1, R.string.s303z2, R.string.s303z3, R.string.s303z4, R.string.s303z5, R.string.s303z6, R.string.s303z7, R.string.s303z8 };
		zones[44] = new int[] { 31, R.string.s31, R.string.s31z1, R.string.s31z2, R.string.s31z3, R.string.s31z4, R.string.s31z5 };
		zones[45] = new int[] { 32, R.string.s32, R.string.s32z1, R.string.s32z2, R.string.s32z3, R.string.s32z4, R.string.s32z5, R.string.s32z6, R.string.s32z7, R.string.s32z8, R.string.s32z9, R.string.s32z10, R.string.s32z11, R.string.s32z12, R.string.s32z13 };
		zones[46] = new int[] { 4, R.string.s4, R.string.s4z1, R.string.s4z2, R.string.s4z3, R.string.s4z4, R.string.s4z5, R.string.s4z6, R.string.s4z7, R.string.s4z8, R.string.s4z9, R.string.s4z10, R.string.s4z11, R.string.s4z12, R.string.s4z13, R.string.s4z14, R.string.s4z15, R.string.s4z16, R.string.s4z17, R.string.s4z18, R.string.s4z19, R.string.s4z20, R.string.s4z21, R.string.s4z22, R.string.s4z23, R.string.s4z24, R.string.s4z25, R.string.s4z26, R.string.s4z27, R.string.s4z28, R.string.s4z29, R.string.s4z30, R.string.s4z31, R.string.s4z32, R.string.s4z33, R.string.s4z34, R.string.s4z35, R.string.s4z36, R.string.s4z37, R.string.s4z38, R.string.s4z39, R.string.s4z40, R.string.s4z41, R.string.s4z42, R.string.s4z43, R.string.s4z44, R.string.s4z45, R.string.s4z46, R.string.s4z47, R.string.s4z48, R.string.s4z49, R.string.s4z50, R.string.s4z51, R.string.s4z52, R.string.s4z53, R.string.s4z54, R.string.s4z55, R.string.s4z56, R.string.s4z57, R.string.s4z58, R.string.s4z59, R.string.s4z60, R.string.s4z61, R.string.s4z62, R.string.s4z63, R.string.s4z64, R.string.s4z65, R.string.s4z66, R.string.s4z67, R.string.s4z68, R.string.s4z69, R.string.s4z70, R.string.s4z71, R.string.s4z72, R.string.s4z73, R.string.s4z74, R.string.s4z75, R.string.s4z76, R.string.s4z77, R.string.s4z78, R.string.s4z79, R.string.s4z80, R.string.s4z81 };
		zones[47] = new int[] { 5, R.string.s5, R.string.s5z1, R.string.s5z2, R.string.s5z3, R.string.s5z4, R.string.s5z5, R.string.s5z6, R.string.s5z7, R.string.s5z8, R.string.s5z9, R.string.s5z10, R.string.s5z11, R.string.s5z12, R.string.s5z13, R.string.s5z14 };
		zones[48] = new int[] { 50, R.string.s50, R.string.s50z1, R.string.s50z2, R.string.s50z3, R.string.s50z4, R.string.s50z5, R.string.s50z6, R.string.s50z7, R.string.s50z8, R.string.s50z9, R.string.s50z10, R.string.s50z11, R.string.s50z12 };
		zones[49] = new int[] { 501, R.string.s501, R.string.s501z1, R.string.s501z2, R.string.s501z3 };
		zones[50] = new int[] { 51, R.string.s51, R.string.s51z1, R.string.s51z2, R.string.s51z3, R.string.s51z4, R.string.s51z5, R.string.s51z6, R.string.s51z7, R.string.s51z8, R.string.s51z9, R.string.s51z10, R.string.s51z11, R.string.s51z12, R.string.s51z13, R.string.s51z14, R.string.s51z15, R.string.s51z16, R.string.s51z17, R.string.s51z18, R.string.s51z19, R.string.s51z20, R.string.s51z21, };
		zones[51] = new int[] { 52, R.string.s52, R.string.s52z1, R.string.s52z2, R.string.s52z3, R.string.s52z4, R.string.s52z5, R.string.s52z6, R.string.s52z7, R.string.s52z8, R.string.s52z9 };
		zones[52] = new int[] { 55, R.string.s55, R.string.s55z1, R.string.s55z2, R.string.s55z3, R.string.s55z4, R.string.s55z5, R.string.s55z6, R.string.s55z7, R.string.s55z8, R.string.s55z9, R.string.s55z10, R.string.s55z11, R.string.s55z12, R.string.s55z13, R.string.s55z14, R.string.s55z15, R.string.s55z16 };
		zones[53] = new int[] { 551, R.string.s551, R.string.s551z1, R.string.s551z2, R.string.s551z3, R.string.s551z4 };
		zones[54] = new int[] { 552, R.string.s552, R.string.s552z1, R.string.s552z2, R.string.s552z3, R.string.s552z4, R.string.s552z5, R.string.s552z6 };
		zones[55] = new int[] { 553, R.string.s553, R.string.s553z1, R.string.s553z2, R.string.s553z3 };
		zones[56] = new int[] { 56, R.string.s56, R.string.s56z1, R.string.s56z2, R.string.s56z3, R.string.s56z4, R.string.s56z5, R.string.s56z6, R.string.s56z7, R.string.s56z8, R.string.s56z9, R.string.s56z10, R.string.s56z11 };
		zones[57] = new int[] { 6, R.string.s6, R.string.s6z1, R.string.s6z2, R.string.s6z3, R.string.s6z4, R.string.s6z5, R.string.s6z6, R.string.s6z7, R.string.s6z8, R.string.s6z9, R.string.s6z10, R.string.s6z11 };
		zones[58] = new int[] { 7, R.string.s7, R.string.s7z1, R.string.s7z2, R.string.s7z3, R.string.s7z4, R.string.s7z5, R.string.s7z6, R.string.s7z7, R.string.s7z8, R.string.s7z9, R.string.s7z10, R.string.s7z11, R.string.s7z12, R.string.s7z13, R.string.s7z14, R.string.s7z15, R.string.s7z16, R.string.s7z17, R.string.s7z18 };
		zones[59] = new int[] { 701, R.string.s701, R.string.s701z1, R.string.s701z2 };
		zones[60] = new int[] { 8, R.string.s8, R.string.s8z1, R.string.s8z2, R.string.s8z3, R.string.s8z4, R.string.s8z5, R.string.s8z6, R.string.s8z7, R.string.s8z8, R.string.s8z9, R.string.s8z10, R.string.s8z11, R.string.s8z12, R.string.s8z13 };
		zones[61] = new int[] { 9, R.string.s9, R.string.s9z1, R.string.s9z2, R.string.s9z3, R.string.s9z4, R.string.s9z5, R.string.s9z6, R.string.s9z7, R.string.s9z8, R.string.s9z9, R.string.s9z10 };
		zones[62] = new int[] { 90, R.string.s90, R.string.s90z1, R.string.s90z2, R.string.s90z3, R.string.s90z4, R.string.s90z5, R.string.s90z6, R.string.s90z7, R.string.s90z8, R.string.s90z9, R.string.s90z10, R.string.s90z11, R.string.s90z12, R.string.s90z13, R.string.s90z14, R.string.s90z15, R.string.s90z16, R.string.s90z17, R.string.s90z18, R.string.s90z19, R.string.s90z20, R.string.s90z21, R.string.s90z22, R.string.s90z23, R.string.s90z24, R.string.s90z25 };
		zones[63] = new int[] { 91, R.string.s91, R.string.s91z1, R.string.s91z2, R.string.s91z3, R.string.s91z4, R.string.s91z5, };
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
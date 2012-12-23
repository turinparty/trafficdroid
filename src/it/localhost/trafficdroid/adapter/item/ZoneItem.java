package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.AbstractActivity;
import it.localhost.trafficdroid.activity.WebViewActivity;
import it.localhost.trafficdroid.common.ViewTagger;
import it.localhost.trafficdroid.dto.ZoneDTO;

import java.util.GregorianCalendar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.analytics.tracking.android.EasyTracker;

public class ZoneItem extends AbstractItem {
	private static final String autostrade = "http://mobile.autostrade.it/autostrade-mobile/popupTelecamera.do?tlc=";
	private static final String cavspa = "http://www.cavspa.it/webcam/temp-imgs/camsbig/";
	private static final String edidomus = "http://telecamere.edidomus.it/vp2/vpcam.aspx?camid=";
	private static final String autofiori = "http://www.autofiori.it/cgi-bin/cgiwebcam.exe?site=";
	private static final String autobspd = "http://www.autobspd.it/images/telecamereAutobspd/";
	private static final int[] colorCat = new int[] { 0xffffffff, 0xffff0000, 0xffff0000, 0xffff8000, 0xffffff00, 0xff47ffff, 0xff00ff00 };
	private static final String jpg = ".jpg";
	private static final int date = new GregorianCalendar().get(GregorianCalendar.DATE);
	private static final String noDataSpeed = "-";
	private static final char camAutostrade = 'A';
	private static final char camCavspa = 'C';
	private static final char camEdidomus = 'E';
	private static final char camAutofiori = 'F';
	private static final char camAutobspd = 'B';
	private static final char camNone = 'H';
	private ZoneDTO zoneDTO;

	public ZoneItem(Context context, ZoneDTO zoneDTO) {
		super(context);
		this.zoneDTO = zoneDTO;
	}

	public int getType() {
		return itemTypes[4];
	}

	public View inflateView() {
		View view = inflater.inflate(R.layout.main_item_zone, null, false);
		ViewTagger.setTag(view, R.id.zoneName, view.findViewById(R.id.zoneName));
		ViewTagger.setTag(view, R.id.zoneSpeedLeft, view.findViewById(R.id.zoneSpeedLeft));
		ViewTagger.setTag(view, R.id.zoneSpeedRight, view.findViewById(R.id.zoneSpeedRight));
		ViewTagger.setTag(view, R.id.trendLeft, view.findViewById(R.id.trendLeft));
		ViewTagger.setTag(view, R.id.trendRight, view.findViewById(R.id.trendRight));
		ViewTagger.setTag(view, R.id.zoneCam, view.findViewById(R.id.zoneCam));
		ViewTagger.setTag(view, R.id.zoneAutoveloxLeft, view.findViewById(R.id.zoneAutoveloxLeft));
		ViewTagger.setTag(view, R.id.zoneAutoveloxRight, view.findViewById(R.id.zoneAutoveloxRight));
		return view;
	}

	public void fillView(View view) {
		ViewTagger.setTag(view, R.id.zoneType, getType());
		ViewTagger.setTag(view, R.id.itemKey, zoneDTO.getId());
		ViewTagger.setTag(view, R.id.itemName, zoneDTO.getName());
		TextView zoneNameText = (TextView) ViewTagger.getTag(view, R.id.zoneName);
		TextView leftZoneSpeedText = (TextView) ViewTagger.getTag(view, R.id.zoneSpeedLeft);
		TextView rightZoneSpeedText = (TextView) ViewTagger.getTag(view, R.id.zoneSpeedRight);
		ImageView trendLeftText = (ImageView) ViewTagger.getTag(view, R.id.trendLeft);
		ImageView trendRightText = (ImageView) ViewTagger.getTag(view, R.id.trendRight);
		ImageView cam = (ImageView) ViewTagger.getTag(view, R.id.zoneCam);
		ImageView autoveloxLeft = (ImageView) ViewTagger.getTag(view, R.id.zoneAutoveloxLeft);
		ImageView autoveloxRight = (ImageView) ViewTagger.getTag(view, R.id.zoneAutoveloxRight);
		zoneNameText.setText(zoneDTO.getName());
		leftZoneSpeedText.setTextColor(colorCat[zoneDTO.getCatLeft()]);
		rightZoneSpeedText.setTextColor(colorCat[zoneDTO.getCatRight()]);
		leftZoneSpeedText.setTypeface(zoneDTO.getCatLeft() == 1 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		rightZoneSpeedText.setTypeface(zoneDTO.getCatRight() == 1 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		if (zoneDTO.getCatLeft() != 0)
			leftZoneSpeedText.setText(Short.toString(zoneDTO.getSpeedLeft()));
		else
			leftZoneSpeedText.setText(noDataSpeed);
		if (zoneDTO.getCatRight() != 0)
			rightZoneSpeedText.setText(Short.toString(zoneDTO.getSpeedRight()));
		else
			rightZoneSpeedText.setText(noDataSpeed);
		if (zoneDTO.getTrendLeft() != 0) {
			trendLeftText.setImageResource(zoneDTO.getTrendLeft());
			trendLeftText.setVisibility(View.VISIBLE);
		} else
			trendLeftText.setVisibility(View.INVISIBLE);
		if (zoneDTO.getTrendRight() != 0) {
			trendRightText.setImageResource(zoneDTO.getTrendRight());
			trendRightText.setVisibility(View.VISIBLE);
		} else
			trendRightText.setVisibility(View.INVISIBLE);
		if (zoneDTO.getWebcam().charAt(0) == camAutostrade || zoneDTO.getWebcam().charAt(0) == camCavspa || zoneDTO.getWebcam().charAt(0) == camEdidomus || zoneDTO.getWebcam().charAt(0) == camAutofiori || zoneDTO.getWebcam().charAt(0) == camAutobspd)
			cam.setImageResource(android.R.drawable.ic_menu_camera);
		else if (zoneDTO.getWebcam().charAt(0) == camNone)
			cam.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
		else
			cam.setImageResource(android.R.drawable.ic_menu_add);
		if (zoneDTO.isAutoveloxLeft())
			autoveloxLeft.setVisibility(View.VISIBLE);
		else
			autoveloxLeft.setVisibility(View.INVISIBLE);
		if (zoneDTO.isAutoveloxRight())
			autoveloxRight.setVisibility(View.VISIBLE);
		else
			autoveloxRight.setVisibility(View.INVISIBLE);
	}

	public void onClick() {
		String code = zoneDTO.getWebcam();
		if (code.charAt(0) == camNone) {
			EasyTracker.getTracker().trackEvent(AbstractActivity.eventCatWebcam, AbstractActivity.eventActionNone, code, (long) 0);
			new AlertDialog.Builder(context).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.webcamNone).show();
		} else if (code.charAt(0) == camAutostrade) {
			EasyTracker.getTracker().trackEvent(AbstractActivity.eventCatWebcam, AbstractActivity.eventActionOpen, code, (long) 0);
			Intent intent = new Intent(context, WebViewActivity.class);
			int id = Integer.parseInt(code.substring(1)) + 6280 * (date);
			intent.putExtra(WebViewActivity.urlTag, autostrade + id);
			context.startActivity(intent);
		} else if (code.charAt(0) == camCavspa) {
			EasyTracker.getTracker().trackEvent(AbstractActivity.eventCatWebcam, AbstractActivity.eventActionOpen, code, (long) 0);
			Intent intent = new Intent(context, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, cavspa + code.substring(1) + jpg);
			context.startActivity(intent);
		} else if (code.charAt(0) == camEdidomus) {
			EasyTracker.getTracker().trackEvent(AbstractActivity.eventCatWebcam, AbstractActivity.eventActionOpen, code, (long) 0);
			Intent intent = new Intent(context, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, edidomus + code.substring(1));
			context.startActivity(intent);
		} else if (code.charAt(0) == camAutofiori) {
			EasyTracker.getTracker().trackEvent(AbstractActivity.eventCatWebcam, AbstractActivity.eventActionOpen, code, (long) 0);
			Intent intent = new Intent(context, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, autofiori + code.substring(1));
			context.startActivity(intent);
		} else if (code.charAt(0) == camAutobspd) {
			EasyTracker.getTracker().trackEvent(AbstractActivity.eventCatWebcam, AbstractActivity.eventActionOpen, code, (long) 0);
			Intent intent = new Intent(context, WebViewActivity.class);
			intent.putExtra(WebViewActivity.urlTag, autobspd + code.substring(1) + jpg);
			context.startActivity(intent);
		} else {
			EasyTracker.getTracker().trackEvent(AbstractActivity.eventCatWebcam, AbstractActivity.eventActionRequest, code, (long) 0);
			new AlertDialog.Builder(context).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.webcamAdd).show();
		}
	}
}
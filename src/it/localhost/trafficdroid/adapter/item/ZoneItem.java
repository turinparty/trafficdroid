package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.WebViewActivity;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdAnalytics;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dto.ZoneDTO;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ZoneItem extends AbstractChildItem {
	public ZoneDTO zoneDTO;

	public ZoneItem(Context context, ZoneDTO zoneDTO) {
		super(context);
		this.zoneDTO = zoneDTO;
	}

	public int getType() {
		return Const.itemTypes[1];
	}

	public View inflateView() {
		View view = inflater.inflate(R.layout.main_item_zone, null, false);
		view.setTag(R.id.zoneName, view.findViewById(R.id.zoneName));
		view.setTag(R.id.zoneKm, view.findViewById(R.id.zoneKm));
		view.setTag(R.id.zoneSpeedLeft, view.findViewById(R.id.zoneSpeedLeft));
		view.setTag(R.id.zoneSpeedRight, view.findViewById(R.id.zoneSpeedRight));
		view.setTag(R.id.trendLeft, view.findViewById(R.id.trendLeft));
		view.setTag(R.id.trendRight, view.findViewById(R.id.trendRight));
		view.setTag(R.id.zoneCam, view.findViewById(R.id.zoneCam));
		view.setTag(R.id.zoneAutoveloxLeft, view.findViewById(R.id.zoneAutoveloxLeft));
		view.setTag(R.id.zoneAutoveloxRight, view.findViewById(R.id.zoneAutoveloxRight));
		return view;
	}

	public void fillView(View view) {
		TextView zoneNameText = (TextView) view.getTag(R.id.zoneName);
		TextView zoneKmText = (TextView) view.getTag(R.id.zoneKm);
		TextView leftZoneSpeedText = (TextView) view.getTag(R.id.zoneSpeedLeft);
		TextView rightZoneSpeedText = (TextView) view.getTag(R.id.zoneSpeedRight);
		ImageView trendLeftText = (ImageView) view.getTag(R.id.trendLeft);
		ImageView trendRightText = (ImageView) view.getTag(R.id.trendRight);
		ImageView cam = (ImageView) view.getTag(R.id.zoneCam);
		ImageView autoveloxLeft = (ImageView) view.getTag(R.id.zoneAutoveloxLeft);
		ImageView autoveloxRight = (ImageView) view.getTag(R.id.zoneAutoveloxRight);
		zoneNameText.setText(zoneDTO.getName());
		zoneKmText.setText(zoneDTO.getKm());
		leftZoneSpeedText.setTextColor(Const.colorCat[zoneDTO.getCatLeft()]);
		rightZoneSpeedText.setTextColor(Const.colorCat[zoneDTO.getCatRight()]);
		leftZoneSpeedText.setTypeface(zoneDTO.getCatLeft() == 1 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		rightZoneSpeedText.setTypeface(zoneDTO.getCatRight() == 1 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		if (zoneDTO.getSpeedLeft() != 0)
			leftZoneSpeedText.setText(Byte.toString(zoneDTO.getSpeedLeft()));
		else
			leftZoneSpeedText.setText(Const.noDataSpeed);
		if (zoneDTO.getSpeedRight() != 0)
			rightZoneSpeedText.setText(Byte.toString(zoneDTO.getSpeedRight()));
		else
			rightZoneSpeedText.setText(Const.noDataSpeed);
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
		if (zoneDTO.getId().charAt(0) == Const.webcamTrue)
			cam.setImageResource(android.R.drawable.ic_menu_camera);
		else if (zoneDTO.getId().charAt(0) == Const.webcamNone)
			cam.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
		else
			cam.setImageResource(android.R.drawable.ic_menu_add);
		if (Const.autoveloxLeft.equalsIgnoreCase(zoneDTO.getAutovelox())) {
			autoveloxLeft.setImageResource(R.drawable.autovelox);
			autoveloxLeft.setVisibility(View.VISIBLE);
			autoveloxRight.setVisibility(View.INVISIBLE);
		} else if (Const.autoveloxRight.equalsIgnoreCase(zoneDTO.getAutovelox())) {
			autoveloxRight.setImageResource(R.drawable.autovelox);
			autoveloxRight.setVisibility(View.VISIBLE);
			autoveloxLeft.setVisibility(View.INVISIBLE);
		} else if (Const.autoveloxAll.equalsIgnoreCase(zoneDTO.getAutovelox())) {
			autoveloxLeft.setImageResource(R.drawable.autovelox);
			autoveloxRight.setImageResource(R.drawable.autovelox);
			autoveloxLeft.setVisibility(View.VISIBLE);
			autoveloxRight.setVisibility(View.VISIBLE);
		} else {
			autoveloxLeft.setVisibility(View.INVISIBLE);
			autoveloxRight.setVisibility(View.INVISIBLE);
		}
	}

	public void onClick() {
		String code = zoneDTO.getId();
		if (code.charAt(0) == Const.webcamNone) {
			TdAnalytics.trackEvent(Const.eventCatWebcam, Const.eventActionNone, code, 0);
			new AlertDialog.Builder(context).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.webcamNone).show();
		} else if (code.charAt(0) == Const.webcamTrue) {
			TdAnalytics.trackEvent(Const.eventCatWebcam, Const.eventActionOpen, code, 0);
			Intent intent = new Intent(context, WebViewActivity.class);
			int id = Integer.parseInt(code.substring(1)) + 6280 * (Const.date);
			intent.putExtra(Const.url, Const.http + TdApp.getPrefString(R.string.providerCamKey, R.string.providerCamDefault) + Const.popupTelecamera + id);
			context.startActivity(intent);
		} else {
			TdAnalytics.trackEvent(Const.eventCatWebcam, Const.eventActionRequest, code, 0);
			new AlertDialog.Builder(context).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.webcamAdd).show();
		}
	}
}
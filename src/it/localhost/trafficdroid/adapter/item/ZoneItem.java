package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.activity.WebViewActivity;
import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.common.TdApp;
import it.localhost.trafficdroid.dto.ZoneDTO;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.apps.analytics.GoogleAnalyticsTracker;

public class ZoneItem extends AbstractChildItem {
	public ZoneDTO zoneDTO;

	public ZoneItem(Context context, ZoneDTO zoneDTO) {
		super(context);
		this.zoneDTO = zoneDTO;
	}

	public int getType() {
		return Const.itemTypes[2];
	}

	public View inflateView() {
		return inflater.inflate(R.layout.main_item_zone, null, false);
	}

	public void fillView(View view) {
		TextView zoneNameText = (TextView) view.findViewById(R.id.zoneName);
		TextView zoneKmText = (TextView) view.findViewById(R.id.zoneKm);
		TextView leftZoneSpeedText = (TextView) view.findViewById(R.id.zoneSpeedLeft);
		TextView rightZoneSpeedText = (TextView) view.findViewById(R.id.zoneSpeedRight);
		ImageView trendLeftText = (ImageView) view.findViewById(R.id.trendLeft);
		ImageView trendRightText = (ImageView) view.findViewById(R.id.trendRight);
		ImageView cam = (ImageView) view.findViewById(R.id.zoneCam);
		zoneNameText.setText(zoneDTO.getName());
		zoneKmText.setText(zoneDTO.getKm());
		leftZoneSpeedText.setTextColor(Const.colorCat[zoneDTO.getCatLeft()]);
		rightZoneSpeedText.setTextColor(Const.colorCat[zoneDTO.getCatRight()]);
		leftZoneSpeedText.setTypeface(zoneDTO.getCatLeft() == 1 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		rightZoneSpeedText.setTypeface(zoneDTO.getCatRight() == 1 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
		if (zoneDTO.getSpeedLeft() != 0)
			leftZoneSpeedText.setText(Byte.toString(zoneDTO.getSpeedLeft()));
		else
			leftZoneSpeedText.setText("-");
		if (zoneDTO.getSpeedRight() != 0)
			rightZoneSpeedText.setText(Byte.toString(zoneDTO.getSpeedRight()));
		else
			rightZoneSpeedText.setText("-");
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
	}

	public void onClick() {
		String code = zoneDTO.getId();
		if (code.charAt(0) == Const.webcamNone) {
			GoogleAnalyticsTracker.getInstance().trackEvent(Const.eventCatWebcam, Const.eventActionNone, code, 0);
			new AlertDialog.Builder(context).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.webcamNone).show();
		} else if (code.charAt(0) == Const.webcamTrue) {
			GoogleAnalyticsTracker.getInstance().trackEvent(Const.eventCatWebcam, Const.eventActionOpen, code, 0);
			Intent intent = new Intent(context, WebViewActivity.class);
			String provider = TdApp.getPrefString(R.string.providerCamKey, R.string.providerCamDefault);
			String url = Const.http + provider + Const.popupTelecamera + Const.decodeCam(code);
			intent.putExtra(Const.url, url);
			context.startActivity(intent);
		} else {
			GoogleAnalyticsTracker.getInstance().trackEvent(Const.eventCatWebcam, Const.eventActionRequest, code, 0);
			new AlertDialog.Builder(context).setTitle(R.string.info).setPositiveButton(R.string.ok, null).setMessage(R.string.webcamAdd).show();
		}
	}
}
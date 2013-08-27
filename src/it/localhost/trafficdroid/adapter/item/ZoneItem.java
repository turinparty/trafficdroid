package it.localhost.trafficdroid.adapter.item;

import it.localhost.trafficdroid.R;
import it.localhost.trafficdroid.dto.ZoneDTO;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ZoneItem extends AbstractItem {
	private static final int[] colorCat = new int[] { 0xffffffff, 0xffff0000, 0xffff0000, 0xffff8000, 0xffffff00, 0xff47ffff, 0xff00ff00 };
	private static final String noDataSpeed = "-";
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
		view.setTag(R.id.zoneName, view.findViewById(R.id.zoneName));
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
		view.setTag(R.id.zoneType, getType());
		view.setTag(R.id.itemKey, zoneDTO.getId());
		view.setTag(R.id.itemName, zoneDTO.getName());
		TextView zoneNameText = (TextView) view.getTag(R.id.zoneName);
		TextView leftZoneSpeedText = (TextView) view.getTag(R.id.zoneSpeedLeft);
		TextView rightZoneSpeedText = (TextView) view.getTag(R.id.zoneSpeedRight);
		ImageView trendLeftText = (ImageView) view.getTag(R.id.trendLeft);
		ImageView trendRightText = (ImageView) view.getTag(R.id.trendRight);
		ImageView cam = (ImageView) view.getTag(R.id.zoneCam);
		ImageView autoveloxLeft = (ImageView) view.getTag(R.id.zoneAutoveloxLeft);
		ImageView autoveloxRight = (ImageView) view.getTag(R.id.zoneAutoveloxRight);
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
		if (zoneDTO.getWebcam().charAt(0) == camNone)
			cam.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
		else if (Character.isLetter(zoneDTO.getWebcam().charAt(0)))
			cam.setImageResource(android.R.drawable.ic_menu_camera);
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
		((OnZoneItemChildClickListener) context).onZoneItemChildClick(zoneDTO);
	}

	public interface OnZoneItemChildClickListener {
		public void onZoneItemChildClick(ZoneDTO zone);
	}
}
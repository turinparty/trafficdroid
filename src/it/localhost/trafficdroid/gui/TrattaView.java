package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.ZoneDTO;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TrattaView extends LinearLayout {
	private TextView trattaTextView;
	private TextView velocitaSxTextView;
	private TextView velocitaDxTextView;

	public TrattaView(Context context, ZoneDTO tratta) {
		super(context);
		this.setOrientation(VERTICAL);
		trattaTextView = new TextView(context);
		velocitaSxTextView = new TextView(context);
		velocitaDxTextView = new TextView(context);
		setZona(tratta);
		trattaTextView.setTypeface(null, Typeface.BOLD_ITALIC);
		trattaTextView.setGravity(Gravity.CENTER);
		addView(trattaTextView, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		TableLayout lay = new TableLayout(context);
		lay.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		lay.setStretchAllColumns(true);
		TableRow row = new TableRow(context);
		velocitaSxTextView.setGravity(Gravity.CENTER);
		velocitaDxTextView.setGravity(Gravity.CENTER);
		row.addView(velocitaSxTextView);
		row.addView(velocitaDxTextView);
		lay.addView(row);
		addView(lay, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}

	public void setZona(ZoneDTO zona) {
		trattaTextView.setText(zona.getName());
		if (zona.getCatLeft() == 1) {
			velocitaSxTextView.setText("<< " + zona.getSpeedLeft() + " >>");
			velocitaSxTextView.setTextColor(Const.colorCat1);
			velocitaSxTextView.setTypeface(null, Typeface.BOLD);
		} else if (zona.getCatLeft() == 2) {
			velocitaSxTextView.setText(zona.getSpeedLeft());
			velocitaSxTextView.setTextColor(Const.colorCat2);
			velocitaSxTextView.setTypeface(null, Typeface.NORMAL);
		} else if (zona.getCatLeft() == 3) {
			velocitaSxTextView.setText(zona.getSpeedLeft());
			velocitaSxTextView.setTextColor(Const.colorCat3);
			velocitaSxTextView.setTypeface(null, Typeface.NORMAL);
		} else if (zona.getCatLeft() == 4) {
			velocitaSxTextView.setText(zona.getSpeedLeft());
			velocitaSxTextView.setTextColor(Const.colorCat4);
			velocitaSxTextView.setTypeface(null, Typeface.NORMAL);
		} else if (zona.getCatLeft() == 5) {
			velocitaSxTextView.setText(zona.getSpeedLeft());
			velocitaSxTextView.setTextColor(Const.colorCat5);
			velocitaSxTextView.setTypeface(null, Typeface.NORMAL);
		} else if (zona.getCatLeft() == 6) {
			velocitaSxTextView.setText(zona.getSpeedLeft());
			velocitaSxTextView.setTextColor(Const.colorCat6);
			velocitaSxTextView.setTypeface(null, Typeface.ITALIC);
		}
		if (zona.getCatRight() == 1) {
			velocitaDxTextView.setText(">> " + zona.getSpeedRight() + " <<");
			velocitaDxTextView.setTextColor(Const.colorCat1);
			velocitaDxTextView.setTypeface(null, Typeface.BOLD);
		} else if (zona.getCatRight() == 2) {
			velocitaDxTextView.setText(zona.getSpeedRight());
			velocitaDxTextView.setTextColor(Const.colorCat2);
			velocitaDxTextView.setTypeface(null, Typeface.NORMAL);
		} else if (zona.getCatRight() == 3) {
			velocitaDxTextView.setText(zona.getSpeedRight());
			velocitaDxTextView.setTextColor(Const.colorCat3);
			velocitaDxTextView.setTypeface(null, Typeface.NORMAL);
		} else if (zona.getCatRight() == 4) {
			velocitaDxTextView.setText(zona.getSpeedRight());
			velocitaDxTextView.setTextColor(Const.colorCat4);
			velocitaDxTextView.setTypeface(null, Typeface.NORMAL);
		} else if (zona.getCatRight() == 5) {
			velocitaDxTextView.setText(zona.getSpeedRight());
			velocitaDxTextView.setTextColor(Const.colorCat5);
			velocitaDxTextView.setTypeface(null, Typeface.NORMAL);
		} else if (zona.getCatRight() == 6) {
			velocitaDxTextView.setText(zona.getSpeedRight());
			velocitaDxTextView.setTextColor(Const.colorCat6);
			velocitaDxTextView.setTypeface(null, Typeface.ITALIC);
		}
	}
}
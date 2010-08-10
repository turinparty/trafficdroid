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
	private TableLayout tableLayout;
	private TableRow tableRow;
	private TextView centerTextView;
	private TextView leftTextView;
	private TextView rightTextView;

	public TrattaView(Context context, ZoneDTO tratta) {
		super(context);
		this.setOrientation(VERTICAL);
		centerTextView = new TextView(context);
		leftTextView = new TextView(context);
		rightTextView = new TextView(context);
		tableRow = new TableRow(context);
		tableLayout = new TableLayout(context);
		centerTextView.setTypeface(null, Typeface.BOLD_ITALIC);
		centerTextView.setGravity(Gravity.CENTER);
		leftTextView.setGravity(Gravity.CENTER);
		rightTextView.setGravity(Gravity.CENTER);
		tableRow.addView(leftTextView);
		tableRow.addView(rightTextView);
		tableLayout.setStretchAllColumns(true);
		tableLayout.addView(tableRow);
		addView(centerTextView, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		addView(tableLayout, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		setZona(tratta);
	}

	public void setZona(ZoneDTO zona) {
		centerTextView.setText(zona.getName());
		switch (zona.getCatLeft()) {
		case 1:
			leftTextView.setText("<< " + zona.getSpeedLeft() + " >>");
			leftTextView.setTextColor(Const.colorCat1);
			leftTextView.setTypeface(null, Typeface.BOLD);
			break;
		case 2:
			leftTextView.setText(zona.getSpeedLeft());
			leftTextView.setTextColor(Const.colorCat2);
			leftTextView.setTypeface(null, Typeface.NORMAL);
			break;
		case 3:
			leftTextView.setText(zona.getSpeedLeft());
			leftTextView.setTextColor(Const.colorCat3);
			leftTextView.setTypeface(null, Typeface.NORMAL);
			break;
		case 4:
			leftTextView.setText(zona.getSpeedLeft());
			leftTextView.setTextColor(Const.colorCat4);
			leftTextView.setTypeface(null, Typeface.NORMAL);
			break;
		case 5:
			leftTextView.setText(zona.getSpeedLeft());
			leftTextView.setTextColor(Const.colorCat5);
			leftTextView.setTypeface(null, Typeface.NORMAL);
			break;
		case 6:
			leftTextView.setText(zona.getSpeedLeft());
			leftTextView.setTextColor(Const.colorCat6);
			leftTextView.setTypeface(null, Typeface.ITALIC);
			break;
		}
		switch (zona.getCatRight()) {
		case 1:
			rightTextView.setText(">> " + zona.getSpeedRight() + " <<");
			rightTextView.setTextColor(Const.colorCat1);
			rightTextView.setTypeface(null, Typeface.BOLD);
			break;
		case 2:
			rightTextView.setText(zona.getSpeedRight());
			rightTextView.setTextColor(Const.colorCat2);
			rightTextView.setTypeface(null, Typeface.NORMAL);
			break;
		case 3:
			rightTextView.setText(zona.getSpeedRight());
			rightTextView.setTextColor(Const.colorCat3);
			rightTextView.setTypeface(null, Typeface.NORMAL);
			break;
		case 4:
			rightTextView.setText(zona.getSpeedRight());
			rightTextView.setTextColor(Const.colorCat4);
			rightTextView.setTypeface(null, Typeface.NORMAL);
			break;
		case 5:
			rightTextView.setText(zona.getSpeedRight());
			rightTextView.setTextColor(Const.colorCat5);
			rightTextView.setTypeface(null, Typeface.NORMAL);
			break;
		case 6:
			rightTextView.setText(zona.getSpeedRight());
			rightTextView.setTextColor(Const.colorCat6);
			rightTextView.setTypeface(null, Typeface.ITALIC);
			break;
		}
	}
}
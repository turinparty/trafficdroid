package it.localhost.trafficdroid.gui.layout;

import it.localhost.trafficdroid.common.Const;
import it.localhost.trafficdroid.dto.ZoneDTO;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ZoneLayout extends LinearLayout {
	private TableLayout tableLayout;
	private TableRow tableRow;
	private TextView centerTextView;
	private TextView leftTextView;
	private TextView rightTextView;

	public ZoneLayout(Context context, ZoneDTO tratta) {
		super(context);
		this.setOrientation(VERTICAL);
		centerTextView = new TextView(context);
		leftTextView = new TextView(context);
		rightTextView = new TextView(context);
		tableRow = new TableRow(context);
		tableLayout = new TableLayout(context);
		centerTextView.setTypeface(Typeface.DEFAULT_BOLD);
		centerTextView.setGravity(Gravity.CENTER);
		leftTextView.setGravity(Gravity.CENTER);
		rightTextView.setGravity(Gravity.CENTER);
		tableRow.addView(leftTextView);
		tableRow.addView(rightTextView);
		tableLayout.setStretchAllColumns(true);
		tableLayout.addView(tableRow);
		addView(centerTextView);
		addView(tableLayout);
		setZona(tratta);
	}

	public void setZona(ZoneDTO zona) {
		centerTextView.setText(zona.getName());
		leftTextView.setText(zona.getSpeedLeft());
		rightTextView.setText(zona.getSpeedRight());
		leftTextView.setTypeface(Typeface.DEFAULT);
		rightTextView.setTypeface(Typeface.DEFAULT);
		switch (zona.getCatLeft()) {
		case 1:
			leftTextView.setTextColor(Const.colorCat1);
			leftTextView.setTypeface(Typeface.DEFAULT_BOLD);
			break;
		case 2:
			leftTextView.setTextColor(Const.colorCat2);
			break;
		case 3:
			leftTextView.setTextColor(Const.colorCat3);
			break;
		case 4:
			leftTextView.setTextColor(Const.colorCat4);
			break;
		case 5:
			leftTextView.setTextColor(Const.colorCat5);
			break;
		case 6:
			leftTextView.setTextColor(Const.colorCat6);
			break;
		}
		switch (zona.getCatRight()) {
		case 1:
			rightTextView.setTextColor(Const.colorCat1);
			rightTextView.setTypeface(Typeface.DEFAULT_BOLD);
			break;
		case 2:
			rightTextView.setTextColor(Const.colorCat2);
			break;
		case 3:
			rightTextView.setTextColor(Const.colorCat3);
			break;
		case 4:
			rightTextView.setTextColor(Const.colorCat4);
			break;
		case 5:
			rightTextView.setTextColor(Const.colorCat5);
			break;
		case 6:
			rightTextView.setTextColor(Const.colorCat6);
			break;
		}
	}
}
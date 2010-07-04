package com.google.code.trafficdroid.gui;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.code.trafficdroid.dto.ZoneDTO;

public class TrattaView extends LinearLayout
{
	private TextView trattaTextView;
	
	private TextView velocitaSxTextView;
	
	private TextView velocitaDxTextView;
	
	public TrattaView(Context context, ZoneDTO tratta)
	{
		super(context);
		this.setOrientation(VERTICAL);
		trattaTextView = new TextView(context);
		velocitaSxTextView = new TextView(context);
		velocitaDxTextView = new TextView(context);
		setZona(tratta);
		
		trattaTextView.setTypeface(null, Typeface.BOLD);
		trattaTextView.setGravity(Gravity.CENTER);
		addView(trattaTextView, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		TableLayout lay = new TableLayout(context);
		lay.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		lay.setStretchAllColumns(true);
		
		TableRow riga = new TableRow(context);
		
		velocitaSxTextView.setGravity(Gravity.CENTER);
		
		velocitaDxTextView.setGravity(Gravity.CENTER);
		
		riga.addView(velocitaSxTextView);
		riga.addView(velocitaDxTextView);
		
		lay.addView(riga);
		
		addView(lay, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}
	
	public void setZona(ZoneDTO zona)
	{
		trattaTextView.setText(zona.getName());
		
		if (zona.getCatLeft() < 4)
		{
			velocitaSxTextView.setText(">> " + zona.getSpeedLeft() + " <<");
			velocitaSxTextView.setTypeface(null, Typeface.BOLD_ITALIC);
		}
		else
		{
			velocitaSxTextView.setText(zona.getSpeedLeft());
			velocitaSxTextView.setTypeface(null, Typeface.NORMAL);
		}
		
		if (zona.getCatRight() < 4)
		{
			velocitaDxTextView.setText(">> " + zona.getSpeedRight() + " <<");
			velocitaDxTextView.setTypeface(null, Typeface.BOLD_ITALIC);
		}
		else
		{
			velocitaDxTextView.setText(zona.getSpeedRight());
			velocitaDxTextView.setTypeface(null, Typeface.NORMAL);
		}
		
	}
}

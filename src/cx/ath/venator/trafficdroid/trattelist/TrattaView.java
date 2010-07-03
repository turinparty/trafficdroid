package cx.ath.venator.trafficdroid.trattelist;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import cx.ath.venator.trafficdroid.data.Tratta;

public class TrattaView extends LinearLayout
{
	private TextView trattaTextView;
	private TextView velocitaSxTextView;
	private TextView velocitaDxTextView;
	
	public TrattaView(Context context, Tratta tratta)
	{
		super(context);
		
		this.setOrientation(VERTICAL);
		
		trattaTextView = new TextView(context);
		trattaTextView.setText(tratta.getTratta());
		/* Now the text (after the icon) */
		addView(trattaTextView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		LinearLayout lay = new LinearLayout(context);
		lay.setOrientation(HORIZONTAL);
		velocitaSxTextView = new TextView(context);
		velocitaSxTextView.setText(tratta.getVelocitaSx());
		lay.addView(velocitaSxTextView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		velocitaDxTextView = new TextView(context);
		velocitaDxTextView.setText(tratta.getVelocitaDx());
		lay.addView(velocitaDxTextView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		
		addView(lay, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}
	
	public void setTratta(String tratta)
	{
		trattaTextView.setText(tratta);
	}
	
	public void setVelocitaSx(String velocitaSx)
	{
		velocitaSxTextView.setText(velocitaSx);
	}
	
	public void setVelocitaDx(String velocitaDx)
	{
		velocitaDxTextView.setText(velocitaDx);
	}
	
	
}

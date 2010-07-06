package it.localhost.trafficdroid.gui;

import it.localhost.trafficdroid.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UrlRequestActivity extends Activity
{
	private EditText txtUrl;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.urlrequest);
		
		txtUrl = (EditText) findViewById(R.id.url);
		SharedPreferences settings = getApplicationContext().getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
		String url = settings.getString(MainActivity.KEY_URL, "");
		txtUrl.setText(url);
		
		Button okBtn = (Button) findViewById(R.id.cmdok);
		okBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SharedPreferences settings = getApplicationContext().getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE);
				SharedPreferences.Editor editor = settings.edit();
				editor.putString(MainActivity.KEY_URL, txtUrl.getText().toString());
				editor.commit();
				startActivity(new Intent(UrlRequestActivity.this, ParserActivity.class));
				finish();
			}
		});
	}
	
}

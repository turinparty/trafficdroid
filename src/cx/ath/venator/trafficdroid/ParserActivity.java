package cx.ath.venator.trafficdroid;

import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import cx.ath.venator.trafficdroid.data.Tratta;
import cx.ath.venator.trafficdroid.trattelist.TrattaListAdapter;

public class ParserActivity extends Activity
{
//	private String PROTO_URL = "http://traffico.octotelematics.com/dyn/#CITY#.gif?ts=1";
//	private List<Tratta> tratte = new ArrayList<Tratta>();
	//
	private ListView tratteListView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tratte);
		
		tratteListView = (ListView) findViewById(R.id.trattelist);
		
		new TratteDownloader().execute(1);
		
	}
	
	
	private class TratteDownloader extends AsyncTask<Integer, Void, List<Tratta>>
	{
		private ProgressDialog dialog;
		
		protected void onPreExecute()
		{
			dialog = ProgressDialog.show(ParserActivity.this, "Please wait", "Downloading tratte...", true);
		}

		protected List<Tratta> doInBackground(Integer... params)
		{
			int tratta = params[0];
			return Parser.getTratta(tratta);
		}
		
		protected void onPostExecute(List<Tratta> tratte)
		{
			dialog.dismiss();
			if (tratte == null)
			{
				new AlertDialog.Builder(ParserActivity.this).setTitle("Error").setMessage("Error downloading image").setPositiveButton("OK", null).show();
			}
			else
			{
				TrattaListAdapter tla = new TrattaListAdapter(ParserActivity.this);
				tla.setListItems(tratte);
				tratteListView.setAdapter(tla);
			}
		}
	}
}
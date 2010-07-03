package com.google.code.trafficdroid;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.code.trafficdroid.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class Main extends Activity //implements OnTouchListener
{
	//===
	private static final String TAG = "Touch";
	   // These matrices will be used to move and zoom image
	   Matrix matrix = new Matrix();
	   Matrix savedMatrix = new Matrix();

	   // We can be in one of these 3 states
	   static final int NONE = 0;
	   static final int DRAG = 1;
	   static final int ZOOM = 2;
	   int mode = NONE;

	   // Remember some things for zooming
	   PointF start = new PointF();
	   PointF mid = new PointF();
	   float oldDist = 1f;	
	//===
	
	private ImageView imView;
	private String PROTO_URL = "http://traffico.octotelematics.com/dyn/#CITY#.gif?ts=1";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button refreshBtn = (Button) findViewById(R.id.btn);
		refreshBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View view)
			{
				new ImageDownloader().execute("1");
			}
		});
		imView = (ImageView) findViewById(R.id.img);
		//imView.setOnTouchListener(this);
		
		Spinner autostrade = (Spinner) findViewById(R.id.cities);
	    ArrayAdapter adapter = ArrayAdapter.createFromResource(
	            this, R.array.autostrade, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    autostrade.setAdapter(adapter);
		
	}

//	private void refreshImageView()
//	{
//		URL myFileUrl = null; 
//		try
//		{
//			String url = PROTO_URL.replace("#CITY#", "1");
//			myFileUrl= new URL(url);
//		}
//		catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		try {
//			HttpURLConnection conn= (HttpURLConnection) myFileUrl.openConnection();
//			InputStream is = conn.getInputStream();
//			Bitmap bmImg = BitmapFactory.decodeStream(is);
//			imView.setImageBitmap(bmImg);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	
	
//	@Override
//	   public boolean onTouch(View v, MotionEvent rawEvent) {
//	      WrapMotionEvent event = WrapMotionEvent.wrap(rawEvent);
//	      // ...
//	      ImageView view = (ImageView) v;
//
//	      // Dump touch event to log
//	      dumpEvent(event);
//
//	      // Handle touch events here...
//	      switch (event.getAction() & MotionEvent.ACTION_MASK) {
//	      case MotionEvent.ACTION_DOWN:
//	         savedMatrix.set(matrix);
//	         start.set(event.getX(), event.getY());
//	         Log.d(TAG, "mode=DRAG");
//	         mode = DRAG;
//	         break;
//	      case MotionEvent.ACTION_POINTER_DOWN:
//	         oldDist = spacing(event);
//	         Log.d(TAG, "oldDist=" + oldDist);
//	         if (oldDist > 10f) {
//	            savedMatrix.set(matrix);
//	            midPoint(mid, event);
//	            mode = ZOOM;
//	            Log.d(TAG, "mode=ZOOM");
//	         }
//	         break;
//	      case MotionEvent.ACTION_UP:
//	      case MotionEvent.ACTION_POINTER_UP:
//	         mode = NONE;
//	         Log.d(TAG, "mode=NONE");
//	         break;
//	      case MotionEvent.ACTION_MOVE:
//	         if (mode == DRAG) {
//	            // ...
//	            matrix.set(savedMatrix);
//	            matrix.postTranslate(event.getX() - start.x,
//	                  event.getY() - start.y);
//	         }
//	         else if (mode == ZOOM) {
//	            float newDist = spacing(event);
//	            Log.d(TAG, "newDist=" + newDist);
//	            if (newDist > 10f) {
//	               matrix.set(savedMatrix);
//	               float scale = newDist / oldDist;
//	               matrix.postScale(scale, scale, mid.x, mid.y);
//	            }
//	         }
//	         break;
//	      }
//
//	      view.setImageMatrix(matrix);
//	      return true; // indicate event was handled
//	   }
//	
//	
//	/** Show an event in the LogCat view, for debugging */
//	   private void dumpEvent(WrapMotionEvent event) {
//	      // ...
//	      String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
//	            "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
//	      StringBuilder sb = new StringBuilder();
//	      int action = event.getAction();
//	      int actionCode = action & MotionEvent.ACTION_MASK;
//	      sb.append("event ACTION_").append(names[actionCode]);
//	      if (actionCode == MotionEvent.ACTION_POINTER_DOWN
//	            || actionCode == MotionEvent.ACTION_POINTER_UP) {
//	         sb.append("(pid ").append(
//	               action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
//	         sb.append(")");
//	      }
//	      sb.append("[");
//	      for (int i = 0; i < event.getPointerCount(); i++) {
//	         sb.append("#").append(i);
//	         sb.append("(pid ").append(event.getPointerId(i));
//	         sb.append(")=").append((int) event.getX(i));
//	         sb.append(",").append((int) event.getY(i));
//	         if (i + 1 < event.getPointerCount())
//	            sb.append(";");
//	      }
//	      sb.append("]");
//	      Log.d(TAG, sb.toString());
//	   }
//
//	   /** Determine the space between the first two fingers */
//	   private float spacing(WrapMotionEvent event) {
//	      // ...
//	      float x = event.getX(0) - event.getX(1);
//	      float y = event.getY(0) - event.getY(1);
//	      return FloatMath.sqrt(x * x + y * y);
//	   }
//
//	   /** Calculate the mid point of the first two fingers */
//	   private void midPoint(PointF point, WrapMotionEvent event) {
//	      // ...
//	      float x = event.getX(0) + event.getX(1);
//	      float y = event.getY(0) + event.getY(1);
//	      point.set(x / 2, y / 2);
//	   }
//	
	
	

	private class ImageDownloader extends AsyncTask<String, Void, Bitmap>
	{
		private ProgressDialog dialog;
		
		protected void onPreExecute()
		{
			dialog = ProgressDialog.show(Main.this, "Please wait", "Downloading image...", true);
		}

		protected Bitmap doInBackground(String... params)
		{
			String city = params[0];
			URL myFileUrl = null; 
			try
			{
				String url = PROTO_URL.replace("#CITY#", city);
				myFileUrl = new URL(url);
			}
			catch (MalformedURLException e) {
				e.printStackTrace();
			}
			try {
				HttpURLConnection conn= (HttpURLConnection) myFileUrl.openConnection();
				InputStream is = conn.getInputStream();
				return BitmapFactory.decodeStream(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(Bitmap result)
		{
			dialog.dismiss();
			if (result == null)
			{
				new AlertDialog.Builder(Main.this).setTitle("Error").setMessage("Error downloading image").setPositiveButton("OK", null).show();
			}
			else
			{
				imView.setImageBitmap(result);
			}
		}
	}
}
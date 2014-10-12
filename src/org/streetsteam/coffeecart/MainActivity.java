package org.streetsteam.coffeecart;

import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.streetsteam.coffeecart.http.EventbriteInvoker;
import org.streetsteam.coffeecart.http.response.EventbriteResponseDecoder;
import org.streetsteam.coffeecart.model.Event;
import org.streetstream.coffeecart.R;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends Activity {

	private LocationManager lm; 
	private double latitude;
	private double longitude;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		
		String provider = lm.getBestProvider(criteria, false);
		Location location = lm.getLastKnownLocation(provider);
		if (location != null) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}else {
			Toast.makeText(this, "Could not fetch location details.", Toast.LENGTH_LONG).show();
		}
		
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, locationListener);
		
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				submit();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void submit(){
		if (longitude != 0.0 && latitude != 0.0) {
			new EventbriteInvoker(latitude + "", longitude + "").getEvents(new EventbriteHttpResponseHandler());
		}else {
			Toast.makeText(this, "Could not get location information. Please switch on GPS.", Toast.LENGTH_LONG).show();
		}
	}
	
	
	class EventbriteHttpResponseHandler extends JsonHttpResponseHandler {

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			super.onSuccess(statusCode, headers, response);
			try {
				List<Event> events = new EventbriteResponseDecoder().decode(response);
				for(Event e:events)
					System.out.println(e);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	private final LocationListener locationListener = new LocationListener() {
		
		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}
	};
	
	//on pause
	protected void onPause() {
		super.onPause();
	}
	
	//on destroy
	protected void onDestroy() {
		super.onDestroy();
	}
}

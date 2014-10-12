package org.streetsteam.coffeecart;

import java.util.List;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;
import org.streetsteam.coffeecart.http.EventbriteInvoker;
import org.streetsteam.coffeecart.http.response.EventbriteResponseDecoder;
import org.streetsteam.coffeecart.model.Event;
import org.streetstream.coffeecart.R;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class MainActivity extends TabActivity{

	private LocationManager lm; 
	private double latitude;
	private double longitude;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		addTab();
	}

	private void addTab() {
		TabHost tabHost = getTabHost(); 

		// Event tab
		Intent eventIntent = new Intent().setClass(this, EventActivity.class);
		TabSpec tabSpecEvent = tabHost
				.newTabSpec("events")
				.setIndicator("Events")
				.setContent(eventIntent);

		// Heat tab
		Intent heatIntent = new Intent().setClass(this, HeatMapsActivity.class);
		TabSpec tabSpecHeat = tabHost
				.newTabSpec("heatedMaps")
				.setIndicator("Heated Maps")
				.setContent(heatIntent);

		// Scheduled tab
		Intent scheduledIntent = new Intent().setClass(this, ScheduledActivity.class);
		TabSpec tabSpecScheduled = tabHost
				.newTabSpec("scheduledEvents")
				.setIndicator("Scheduled")
				.setContent(scheduledIntent);

		// add all tabs 
		tabHost.addTab(tabSpecEvent);
		tabHost.addTab(tabSpecHeat);
		tabHost.addTab(tabSpecScheduled);

		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void submit(){
		
	}

	//on pause
	protected void onPause() {
		super.onPause();
	}

	//on destroy
	protected void onDestroy() {
		super.onDestroy();
	}
}

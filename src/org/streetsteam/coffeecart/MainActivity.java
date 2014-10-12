package org.streetsteam.coffeecart;

import org.streetstream.coffeecart.R;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity{

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
				.newTabSpec("heatMap")
				.setIndicator("Heat Map")
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

	//on pause
	protected void onPause() {
		super.onPause();
	}

	//on destroy
	protected void onDestroy() {
		super.onDestroy();
	}
}

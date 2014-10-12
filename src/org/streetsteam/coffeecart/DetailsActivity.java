package org.streetsteam.coffeecart;

import org.streetsteam.coffeecart.model.Event;
import org.streetsteam.coffeecart.sql.ScheduledEventsDAO;
import org.streetstream.coffeecart.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsActivity extends ActionBarActivity{

	Event event;
	String name, start, venue, capacity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		Intent intent = getIntent();
		event = (Event) intent.getSerializableExtra("eventObj");
		
		TextView t1 = (TextView)findViewById(R.id.textView8);
		t1.setText(event.getEventName());
		TextView t2 = (TextView)findViewById(R.id.textView2);
		t2.setText(event.getStart());
		TextView t4 = (TextView)findViewById(R.id.textView6);
		t4.setText(event.getVenue().toString());
		TextView t5 = (TextView)findViewById(R.id.textView7);
		t5.setText(event.getCapacity());
		TextView t9 = (TextView)findViewById(R.id.textView10);
		t9.setText(event.getDescription());
		
		getActionBar().setDisplayShowHomeEnabled(true);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.details_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        NavUtils.navigateUpFromSameTask(this);
	        return true;
	    case R.id.menu_schedule:
	    	ScheduledEventsDAO dao = new ScheduledEventsDAO(getApplicationContext());
			dao.open();
			long id = dao.createScheduleEvent(event);
			dao.close();
			if (id > 0)
				Toast.makeText(getApplicationContext(), "Event added to schedule.", Toast.LENGTH_LONG).show();
	    	return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
}

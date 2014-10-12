package org.streetsteam.coffeecart;

import java.util.List;

import org.streetsteam.coffeecart.model.Event;
import org.streetsteam.coffeecart.sql.ScheduledEventsDAO;
import org.streetstream.coffeecart.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class ScheduledActivity extends Activity {
	
	
	private ListView mainListView ;  
	private ArrayAdapter<Event> listAdapter ; 
	private List<Event> events;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scheduled_list);
        mainListView = (ListView) findViewById( R.id.scheduledListView);
        mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Event event = events.get(arg2);
				Intent intent = new Intent(getApplicationContext(),MapDirectionActivity.class);
				intent.putExtra("latitude", Double.parseDouble(event.getVenue().getLocation().getLatitude()));
				intent.putExtra("longitude", Double.parseDouble(event.getVenue().getLocation().getLongitude()));
				intent.putExtra("eventLocationName",event.getVenueString());
				ScheduledActivity.this.startActivity(intent);
			}
		});
        populateScheduledList();
    }

	private void populateScheduledList() {
		ScheduledEventsDAO dao = new ScheduledEventsDAO(getApplicationContext());
		dao.open();
		events =  dao.getScheduledEvents();
		dao.close();
		listAdapter = new EventArrayAdapter(getApplicationContext(), R.layout.scheduledrow, events);
		//Set the ArrayAdapter as the ListView's adapter.  
		mainListView.setAdapter( listAdapter );    
		listAdapter.notifyDataSetChanged();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		populateScheduledList();
	}
	
	class EventArrayAdapter extends ArrayAdapter<Event>{

		private Context context;
		private List<Event> events;
		
		public EventArrayAdapter(Context context, int resource,
				List<Event> objects) {
			super(context, resource, objects);
			this.context = context;
			this.events = objects;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = li.inflate(R.layout.scheduledrow, parent,false);
			Event event = events.get(position);
			
			if (event != null){
				TextView etv = (TextView) row.findViewById(R.id.evname);
				etv.setText(event.getEventName());
				TextView est = (TextView) row.findViewById(R.id.start);
				est.setText(event.getStart().replace("T", ","));
				est.setTypeface(null, Typeface.BOLD);
			}
			return row;
		}
	}
}
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
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

public class EventActivity extends Activity implements LocationListener{

	private LocationManager lm; 
	private double latitude = 37.2970156;
	private double longitude = -121.8174129;

	private ListView mainListView ;  
	private ArrayAdapter<Event> listAdapter ;  
	private List<Event> events;

	private ProgressDialog progress;
	/** Called when the activity is first created. */  
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.list);  
		populateEvents();

		lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();

		String provider = lm.getBestProvider(criteria, false);
		Location location = lm.getLastKnownLocation(provider);
		if (location != null) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}else {
			//Toast.makeText(this, "Could not fetch location details.", Toast.LENGTH_LONG).show();
		}
	} 

	public void populateEvents()
	{
		mainListView = (ListView) findViewById( R.id.mainListView);  

		if (longitude != 0.0 && latitude != 0.0) {
			new EventbriteInvoker(latitude + "", longitude + "").getEvents(new EventbriteHttpResponseHandler());
		}else {
			Toast.makeText(this, "Could not get location information. Please switch on GPS.", Toast.LENGTH_LONG).show();
		}
		mainListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intentDetails = new Intent(getApplicationContext(),DetailsActivity.class);
				Event event = events.get(position);
				intentDetails.putExtra("eventApiId", event.getApiEventId());
				intentDetails.putExtra("name", event.getEventName());
				intentDetails.putExtra("description", event.getDescription());
				intentDetails.putExtra("start", event.getStart());
				intentDetails.putExtra("end", event.getEnd());
				intentDetails.putExtra("capacity", event.getCapacity());
				intentDetails.putExtra("venue", event.getVenue().toString());
				EventActivity.this.startActivity(intentDetails);
			}	    	
		}); 
	}
	
	class EventArrayAdapter extends ArrayAdapter<Event>{

		private Context context;
		
		public EventArrayAdapter(Context context, int resource,
				List<Event> objects) {
			super(context, resource, objects);
			this.context = context;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View row = li.inflate(R.layout.simplerow, parent,false);
			Event event = events.get(position);
			if (event != null){
				TextView etv = (TextView) row.findViewById(R.id.eventName);
				etv.setText(event.getEventName());
			}
			return row;
		}
	}
	
	class EventbriteHttpResponseHandler extends JsonHttpResponseHandler {

		@Override
		public void onStart() {
			super.onStart();
			progress = ProgressDialog.show(EventActivity.this, "Loading events...", "Please wait");
		}
		
		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			try {
				events = new EventbriteResponseDecoder().decode(response);
				listAdapter = new EventArrayAdapter(EventActivity.this, R.layout.simplerow, events);
				//Set the ArrayAdapter as the ListView's adapter.  
				mainListView.setAdapter( listAdapter );    
				listAdapter.notifyDataSetChanged();

				progress.dismiss();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			super.onSuccess(statusCode, headers, response);
		}
	}

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		latitude = location.getLatitude();
		longitude = location.getLongitude();
	}

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
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (events != null){
			listAdapter.addAll(events);
			mainListView.setAdapter(listAdapter);
			listAdapter.notifyDataSetChanged();
		}
	}
}
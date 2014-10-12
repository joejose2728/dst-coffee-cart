package org.streetsteam.coffeecart;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.streetstream.coffeecart.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.maps.android.heatmaps.Gradient;
import com.google.maps.android.heatmaps.HeatmapTileProvider;
import com.google.maps.android.heatmaps.WeightedLatLng;

public class HeatMapsActivity extends Activity {
	private GoogleMap mMap;
	private LatLng origin = new LatLng(37.3768443, -121.9216705);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heated_map);

		if (mMap != null) {
			return;
		}
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.heatedmap)).getMap();
		if (mMap != null) {
			List<WeightedLatLng> list = null;

			// Get the data: latitude/longitude positions of police stations.
			try {
				list = readItems(R.raw.crowded_areas);
			} catch (JSONException e) {
				Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();
			}
			
			if (list == null)
				Toast.makeText(this, "Problem reading list of locations.", Toast.LENGTH_LONG).show();

			int[] colors = {
				    Color.rgb(102, 225, 0), // green
				    Color.rgb(255, 0, 0),  // red
				    Color.rgb(255, 255, 0), // yellow
				    Color.rgb(102, 225, 0), // green
				    Color.rgb(255, 0, 0),  // red
				    Color.rgb(255, 255, 0) // yellow
				};

				float[] startPoints = {
				    0.2f, 0.35f,0.5f, 0.75f, 0.8f, 1f
				};

				Gradient gradient = new Gradient(colors, startPoints);
			// Create a heat map tile provider, passing it the latlngs of the police stations.
			HeatmapTileProvider mProvider = new HeatmapTileProvider.Builder()
			.weightedData(list)
			.gradient(gradient)
			.build();
			// Add a tile overlay to the map, using the heat map tile provider.
			TileOverlay mOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(mProvider));
			mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 10));
			mMap.animateCamera(CameraUpdateFactory.zoomTo(11), 2000, null);
		}
	}

	private ArrayList<WeightedLatLng> readItems(int resource) throws JSONException {
		ArrayList<WeightedLatLng> list = new ArrayList<WeightedLatLng>();
		InputStream inputStream = getResources().openRawResource(resource);
		String json = new Scanner(inputStream).useDelimiter("\\A").next();
		JSONArray array = new JSONArray(json);
		for (int i = 0; i < array.length(); i++) {
			JSONObject object = array.getJSONObject(i);
			double lat = object.getDouble("lat");
			double lng = object.getDouble("lng");
			//list.add(new LatLng(lat, lng));
			list.add(new WeightedLatLng(new LatLng(lat, lng),50));
		}
		return list;
	}

}
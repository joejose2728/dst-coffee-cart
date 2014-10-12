package org.streetsteam.coffeecart.http;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class EventbriteInvoker extends EventsAPIInvoker {

	private static final String s_baseURL = "http://www.eventbriteapi.com/v3/events/search";
	private static final String s_token = "EPOXOXUPTRDIS4R4NXBB";
	private static final String s_locationWithin = "10mi";
	private static final String s_startDateKeyword = "this_week";
	
	private String latitude;
	private String longitude;
	
	public EventbriteInvoker(String lat, String lot){
		this.latitude = lat;
		this.longitude = lot;
	}
	
	@Override
	public void getEvents(AsyncHttpResponseHandler responseHandler) {
		RequestParams requestParams = new RequestParams();
		requestParams.put("token", s_token);
		requestParams.put("start_date.keyword", s_startDateKeyword);
		requestParams.put("location.latitude", latitude);
		requestParams.put("location.longitude", longitude);
		requestParams.put("location.within", s_locationWithin);
		get(s_baseURL, requestParams, responseHandler);
	}
	
	
}

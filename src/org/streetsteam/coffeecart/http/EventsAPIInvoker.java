package org.streetsteam.coffeecart.http;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public abstract class EventsAPIInvoker {

	private static AsyncHttpClient s_client = new AsyncHttpClient();

	public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		s_client.get(url, params, responseHandler);
	}

	public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		s_client.post(url, params, responseHandler);
	}

	public abstract void getEvents(AsyncHttpResponseHandler responseHandler);
	
}

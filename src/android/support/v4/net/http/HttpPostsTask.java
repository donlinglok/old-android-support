package android.support.v4.net.http;

import java.util.List;

import net.minidev.json.JSONObject;

import org.apache.http.NameValuePair;

import android.os.AsyncTask;
import android.support.v4.lang.Strings;

/*
 * Copyright (c) 2014 Kenneth Tu <don.ling.lok@gmail.com>
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * @author Kenneth Tu
 * @version 1.0.0
 */
public class HttpPostsTask extends AsyncTask<Void, Void, String> {
	private transient JSONObject dateObject;
	private transient List<NameValuePair> dateObject2;
	private transient final String url;
	private transient final HttpPostsTaskCallback callback;
	private transient final boolean isGzip;

	public interface HttpPostsTaskCallback {
		void onReturn(String result);
	}

	public HttpPostsTask(final HttpPostsTaskCallback callback,
			final String url, final JSONObject dateObject, final boolean isGzip) {
		super();
		this.callback = callback;
		this.url = url;
		this.dateObject = dateObject;
		this.isGzip = isGzip;
	}

	public HttpPostsTask(final HttpPostsTaskCallback callback,
			final String url, final List<NameValuePair> dateObject,
			final boolean isGzip) {
		super();
		this.callback = callback;
		this.url = url;
		dateObject2 = dateObject;
		this.isGzip = isGzip;
	}

	@Override
	public String doInBackground(final Void... params) {
		if (null == dateObject) {
			return HttpPosts.postNameValuePair(url, dateObject2, isGzip);
		} else {
			return HttpPosts.postBody(url, Strings.valueOf(dateObject), isGzip);
		}
	}

	@Override
	public void onPostExecute(final String result) {
		super.onPostExecute(result);
		callback.onReturn(result);
	}
}
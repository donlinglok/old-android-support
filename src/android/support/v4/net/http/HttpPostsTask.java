package android.support.v4.net.http;

import net.minidev.json.JSONObject;
import android.support.v4.util.AsyncTask;

/*
 * Copyright (c) 2014 Kenneth Tu <don.ling.lok@gmail.com>
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * @author Kenneth Tu
 * @version 1.0.0
 */
public class HttpPostsTask extends AsyncTask<Void, Void, String> {
	private final transient JSONObject dateObject;
	private final transient String url;
	private final transient HttpPostsTaskCallback callback;
	private final transient boolean isGzip;

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

	@Override
	protected String doInBackground(final Void... params) {
		String result = null;
		try {
			if (isGzip) {
				result = HttpPosts.postBody(url, dateObject.toString());
			} else {
				result = HttpPosts.postBodyNoGzip(url, dateObject.toString());
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return result;
	}

	@Override
	protected void onPostExecute(final String result) {
		super.onPostExecute(result);
		callback.onReturn(result);
	}
}
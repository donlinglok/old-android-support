package android.support.util;

import java.io.File;

import android.content.Context;
import android.support.net.http.HttpDownloadTask;
import android.support.net.http.HttpDownloadTask.HttpDownloadTaskCallback;

/*
 * Copyright (c) 2014 Kenneth Tu <don.ling.lok@gmail.com>
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * @author Kenneth Tu
 * @version 1.0.0
 */
public class RegionPhone {
	public static abstract interface RegionPhoneCallback {
		public abstract void onSuccess(File outputFile);

		public abstract void onFail(Exception exception);
	}

	public final void init(final Context context, final RegionPhoneCallback callback) {
		new HttpDownloadTask(context, new HttpDownloadTaskCallback() {
			@Override
			public void onSuccess(final File outputFile) {
				callback.onSuccess(outputFile);
			}

			@Override
			public void onFail(final Exception exception) {
				callback.onFail(exception);
			}
		}, "https://raw.githubusercontent.com/mledoze/countries/master/countries.json", "RegionPhone.json", false)
				.execute();
	}
}
package android.support.v4.os.storage;

import java.io.IOException;

import android.content.Context;
import android.support.v4.graphics.bitmapfun.AsyncTask;
import android.support.v4.lang.Strings;

public class AssetsCopyTask extends AsyncTask<Void, Void, String> {
	private final transient Context context;
	private transient AssetsCopyTaskCallback callback;

	public interface AssetsCopyTaskCallback {
		void onReturn(Boolean result);
	}

	public AssetsCopyTask(final Context context,
			final AssetsCopyTaskCallback callback) {
		super();
		this.context = context;
		this.callback = callback;
	}

	public AssetsCopyTask(final Context context) {
		super();
		this.context = context;
	}

	@Override
	public final String doInBackground(final Void... params) {
		try {
			Files.copyAssets(context);
			callback.onReturn(true);
		} catch (final IOException exception) {
			Strings.exceptionToJSONObject(exception);
			callback.onReturn(false);
		}
		return Strings.EMPTY;
	}
}

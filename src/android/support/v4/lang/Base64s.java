package android.support.v4.lang;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.support.v4.graphics.bitmapfun.ImageResizer;
import android.util.Base64;
import android.util.Base64OutputStream;

public final class Base64s {
	public String imgToBase64(final String filePath) {
		final Bitmap bitmap = ImageResizer.decodeSampledBitmapFromFile(
				filePath, 1536, 1536, null);
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
		final byte[] byteArrayImage = baos.toByteArray();
		try {
			baos.close();
		} catch (final IOException e) {
		}
		bitmap.recycle();

		return Base64.encodeToString(byteArrayImage, Base64.DEFAULT)
				.replace("\n", "").replace("=", "");
	}

	public String fileToBase64(final String filePath) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(filePath);
		} catch (final FileNotFoundException e1) {
		}
		final byte[] buffer = new byte[8192];
		int bytesRead;
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		final Base64OutputStream output64 = new Base64OutputStream(output,
				Base64.DEFAULT);
		try {
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				output64.write(buffer, 0, bytesRead);
			}
		} catch (final IOException e) {
		}
		try {
			output64.close();
		} catch (final IOException e) {
		}
		try {
			inputStream.close();
		} catch (final IOException e) {
		}

		return Base64.encodeToString(buffer, Base64.DEFAULT).replace("\n", "")
				.replace("=", "");
	}
}

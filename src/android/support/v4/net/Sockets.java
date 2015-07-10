package android.support.v4.net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;

import net.minidev.json.JSONObject;
import android.support.v4.lang.Strings;

/*
 * Copyright (c) 2014 Kenneth Tu <don.ling.lok@gmail.com>
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * @author Kenneth Tu
 * @version 1.0.0
 */
public class Sockets extends Socket {
	public Sockets(final InetAddress serverAddr, final int serverport)
			throws IOException {
		super(serverAddr, serverport);
	}

	public Sockets(final SocketImpl socketImpl) throws SocketException {
		super(socketImpl);
	}

	private transient String idString;

	public void setIdString(final String idString) {
		this.idString = idString;
	}

	public String getIdString() {
		return idString;
	}

	private transient JSONObject propertiesObject = new JSONObject();

	public void setProperties(final JSONObject propertiesObject) {
		this.propertiesObject = propertiesObject;
	}

	public JSONObject getProperties() {
		return propertiesObject;
	}

	private transient final String encryptionKey = Strings.fString(
			Strings.UPPK, Strings.LOWE, Strings.LOWN, Strings.LOWN,
			Strings.LOWN, Strings.LOWE, Strings.LOWT, Strings.LOWH);

	// public void setEncryptionKey(final String encryptionKey) {
	// this.encryptionKey = encryptionKey;
	// }

	public String getEncryptionKey() {
		return encryptionKey;
	}

	private transient final int encryptSizeLimit = 10;

	// public void setencryptSizeLimit(final int encryptSizeLimit) {
	// this.encryptSizeLimit = encryptSizeLimit;
	// }

	public int getEncryptionSizeLimit() {
		return encryptSizeLimit;
	}

	public static final String MSG_CODE = Strings.fString(Strings.LOWC,
			Strings.LOWO, Strings.LOWD, Strings.LOWE);
	public static final String ACTION = Strings.fString(Strings.UPPA,
			Strings.UPPC, Strings.UPPT, Strings.UPPI, Strings.UPPO,
			Strings.UPPN);
	public static final String RETURN = Strings.fString(Strings.UPPR,
			Strings.UPPE, Strings.UPPT, Strings.UPPU, Strings.UPPR,
			Strings.UPPN);
	public static final String KEEPALIVE = Strings.LOWK;
	public static final String KEEPALIVE_REACTION = Strings.LOWT;
	public static final String DISCONNECT = Strings.fString(Strings.UPPD,
			Strings.UPPI, Strings.UPPS, Strings.UPPC, Strings.UPPO,
			Strings.UPPN);

}
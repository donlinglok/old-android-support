/*
 * Copyright (c) 2014 Kenneth Tu <don.ling.lok@gmail.com>
 *
 * All rights reserved. No warranty, explicit or implicit, provided.
 *
 * @author Kenneth Tu
 * @version 1.0.0
 */

package android.support.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.n.NString;
import android.support.lang.Crypt;

public class Preferences {
	public static final String NAME = "SharedPreference";

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String key, final boolean value) {
		set(baseContext, NAME, key, value);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String key, final float value) {
		set(baseContext, NAME, key, value);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String key, final int value) {
		set(baseContext, NAME, key, value);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String key, final Long value) {
		set(baseContext, NAME, key, value);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String key, final String value) {
		set(baseContext, NAME, key, value);
	}

	/**
	 *
	 * @param baseContext
	 * @param arrayName
	 * @param array
	 * @return
	 */
	public static final boolean setArray(final Context baseContext, final String arrayName, final List<?> array) {
		return setArray(baseContext, NAME, arrayName, array);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final boolean get(final Context baseContext, final String key, final boolean defValue) {
		return get(baseContext, NAME, key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final float get(final Context baseContext, final String key, final float defValue) {
		return get(baseContext, NAME, key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final int get(final Context baseContext, final String key, final int defValue) {
		return get(baseContext, NAME, key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final Long get(final Context baseContext, final String key, final Long defValue) {
		return get(baseContext, NAME, key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final String get(final Context baseContext, final String key, final String defValue) {
		return get(baseContext, NAME, key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param key
	 * @return
	 */
	public static final List<?> getArray(final Context baseContext, final String key) {
		return getArray(baseContext, NAME, key);
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String name, final String key, final boolean value) {
		final Editor editor = baseContext.getSharedPreferences(name, 0).edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String name, final String key, final float value) {
		final Editor editor = baseContext.getSharedPreferences(name, 0).edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String name, final String key, final int value) {
		final Editor editor = baseContext.getSharedPreferences(name, 0).edit();
		editor.putInt(key, value);
		editor.commit();
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String name, final String key, final Long value) {
		final Editor editor = baseContext.getSharedPreferences(name, 0).edit();
		editor.putLong(key, value);
		editor.commit();
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param value
	 */
	public static final void set(final Context baseContext, final String name, final String key, final String value) {
		final Editor editor = baseContext.getSharedPreferences(name, 0).edit();
		editor.putString(key, Crypt.encrypt(value, NAME));
		editor.commit();
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param array
	 * @return
	 */
	public static final boolean setArray(final Context baseContext, final String name, final String key,
			final List<?> array) {
		final Editor editor = baseContext.getSharedPreferences(name, 0).edit();
		editor.putInt(NString.add(key, "_size"), array.size());
		for (int i = 0; i < array.size(); i++) {
			editor.putString(NString.add(key, "_") + i, (String) array.get(i));
		}
		return editor.commit();
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final boolean get(final Context baseContext, final String name, final String key,
			final boolean defValue) {
		return baseContext.getSharedPreferences(name, 0).getBoolean(key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final float get(final Context baseContext, final String name, final String key,
			final float defValue) {
		return baseContext.getSharedPreferences(name, 0).getFloat(key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final int get(final Context baseContext, final String name, final String key, final int defValue) {
		return baseContext.getSharedPreferences(name, 0).getInt(key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final Long get(final Context baseContext, final String name, final String key, final Long defValue) {
		return baseContext.getSharedPreferences(name, 0).getLong(key, defValue);
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static final String get(final Context baseContext, final String name, final String key,
			final String defValue) {
		return Crypt.decrypt(baseContext.getSharedPreferences(name, 0).getString(key, defValue), NAME);
	}

	/**
	 *
	 * @param baseContext
	 * @param name
	 * @param key
	 * @return
	 */
	public static final List<?> getArray(final Context baseContext, final String name, final String key) {
		final SharedPreferences prefs = baseContext.getSharedPreferences(name, 0);
		final int size = prefs.getInt(NString.add(key, "_size"), 0);
		final ArrayList<String> array = new ArrayList<String>(size);
		for (int i = 0; i < size; i++) {
			array.add(i, prefs.getString(NString.add(key, "_") + i, null));
		}
		return array;
	}
}

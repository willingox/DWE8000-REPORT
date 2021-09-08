package com.xjgc.wind.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class HourMTimeUtil {

	private static ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {

		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat("HH:mm");
		}

	};

	public static DateFormat get() {
		return df.get();
	}

}

package com.xjgc.wind.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class MonthDayTimeUtil {

	private static ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {

		protected synchronized DateFormat initialValue() {
			return new SimpleDateFormat("MM-dd");
		}

	};

	public static DateFormat get() {
		return df.get();
	}

}

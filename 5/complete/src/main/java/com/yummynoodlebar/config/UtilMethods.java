package com.yummynoodlebar.config;

import java.util.Date;

public class UtilMethods {
	public static Date clone(final Date date) {
		if (date == null)
			return null;
		return new Date(date.getTime());
	}
}

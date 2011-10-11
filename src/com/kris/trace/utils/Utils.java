package com.kris.trace.utils;

import java.util.regex.Matcher;

import android.text.TextUtils;
/**
 * 工具类
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since 1.0.0 下午05:16:40
 * @version 1.0.0
 */
public class Utils {
	/**
	 * Returns true if the string is null or 0-length.
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(final String text)
	{
		return TextUtils.isEmpty(text);
	}
	public static  boolean matchPhone(CharSequence phone)
	{
		Matcher m = Patterns.MOBILE_PHONE.matcher(phone);
		return m.matches();
	}
}

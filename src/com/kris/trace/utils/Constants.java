package com.kris.trace.utils;

import java.io.File;

import android.os.Environment;


/**
 * Global Constants 
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since 1.0.0 ä¸??11:09:34
 * @version 1.0.0
 */
public class Constants {
	public static final String  APP_NAME				= "trace";
	public static  String		EXTERNAL_DIR 			= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+APP_NAME;
	
	public static final String 	PERFER_NAME 			= "tracing";
	public static final String 	PERFER_PASSWORD 		= "password";
	public static final String 	PERFER_SIM 			= "sim";
	public static final String 	PERFER_PHONE_NUMBER	= "phoneNumber";
	public static final String 	PERFER_PRE_PHONE		= "pre_phone";
	public static final String 	PERFER_SETP			= "step";
}

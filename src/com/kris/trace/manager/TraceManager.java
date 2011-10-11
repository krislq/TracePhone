package com.kris.trace.manager;


import com.kris.trace.utils.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.telephony.TelephonyManager;
/**
 * 主要是用于保存preference的值
 *
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since  2011-10-9  下午6:19:17
 * @version 1.0.0
 */
public class TraceManager {
	private Context mContext;
	public TraceManager(Context context) {
		mContext = context;
	}
	private SharedPreferences getSharedPreferences()
	{
		return mContext.getSharedPreferences(Constants.PERFER_NAME, Context.MODE_PRIVATE);
	}
	private Editor getEditer()
	{
		return getSharedPreferences().edit();
	}
	public String getPassword() {
		SharedPreferences swg = getSharedPreferences();
		
		return swg.getString(Constants.PERFER_PASSWORD, null);
	}

	public void setPassword(String pwd) {
		Editor editor = getEditer();
		editor.putString(Constants.PERFER_PASSWORD, pwd);
		editor.commit();
	}
	

	public String getSim() {
		SharedPreferences swg = getSharedPreferences();
		
		return swg.getString(Constants.PERFER_SIM, null);
	}

	public void setSim(String sim) {
		Editor editor = getEditer();
		editor.putString(Constants.PERFER_SIM, sim);
		editor.commit();
	}
	


	public String getPhoneNumber() {
		SharedPreferences swg = getSharedPreferences();
		
		return swg.getString(Constants.PERFER_PHONE_NUMBER, null);
	}

	public void setPhoneNumber(String phone) {
		Editor editor = getEditer();
		editor.putString(Constants.PERFER_PHONE_NUMBER, phone);
		editor.commit();
	}
	

	public String getPreparePhoneNumber() {
		SharedPreferences swg = getSharedPreferences();
		
		return swg.getString(Constants.PERFER_PRE_PHONE, null);
	}

	public void setPreparePhoneNumber(String phone) {
		Editor editor = getEditer();
		editor.putString(Constants.PERFER_PRE_PHONE, phone);
		editor.commit();
	}
	
	public static final int SETP_PASSWORD 	= 0;
	public static final int SETP_INFO 		= 1;
	public static final int SETP_OVER 		= 2;
	public int getStep() {
		SharedPreferences swg = getSharedPreferences();
		
		return swg.getInt(Constants.PERFER_SETP, SETP_PASSWORD);
	}

	public void setStep(int step) {
		Editor editor = getEditer();
		editor.putInt(Constants.PERFER_SETP, step);
		editor.commit();
	}
	//////////////////////////////////////////////////
	public String getSimSerialNumber()
	{
		TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSimSerialNumber();
	}
	public String getLocalPhoneNumber()
	{
		TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getLine1Number();
	}
}

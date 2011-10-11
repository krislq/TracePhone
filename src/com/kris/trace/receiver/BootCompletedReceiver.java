package com.kris.trace.receiver;

import com.kris.trace.manager.TraceManager;
import com.kris.trace.service.TraceService;
import com.kris.trace.utils.L;
import com.kris.trace.utils.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 *监听手机的启动，每次启动都去比较　手机号码　
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @version 1.0
 */
public class BootCompletedReceiver extends BroadcastReceiver {

	public static final String BOOT_COMPLETED_ACTION = "android.intent.action.BOOT_COMPLETED";
	@Override
	public void onReceive(Context context, Intent intent) {
		L.i("监听到了手机的启动。");
		String action = intent.getAction();
		 if(action.equals(BOOT_COMPLETED_ACTION))
		 {
			 TraceManager manager = new TraceManager(context);
			 String password = manager.getPassword();
			 if(!Utils.isEmpty(password))
			 {
				 //如果不为空，则证明已经初始化过了，则去启动服务,比较 
				 Intent service = new Intent(context,TraceService.class);
				 context.startService(service);
			 }
		 };   
	}

}

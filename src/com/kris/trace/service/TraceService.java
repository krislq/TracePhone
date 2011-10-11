package com.kris.trace.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;

import com.kris.trace.manager.TraceManager;
import com.kris.trace.utils.L;
/**
 * 服务，主要是手于比较当前的电话与设定的是否是一至的，如果不一样，则发短信
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since 1.0.0 上午10:27:30
 * @version 1.0.0
 */
public class TraceService extends Service {
	private TraceManager manager ;
	private Context context;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = this;
		manager = new TraceManager(context);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				L.i("开始了对比的服务。。。");
				String origSim = manager.getSim();
				String nowSim = manager.getSimSerialNumber();
				String ownPhone = manager.getPhoneNumber();
				String prePhone = manager.getPreparePhoneNumber();
				L.i("ownPhone:"+ownPhone+"#prePhone:"+prePhone);
				if(!origSim.equals(nowSim))
				{
					//if not the same ,so send a SMS
					L.e("sim卡被换了");
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(prePhone, null, "你的手机("+ownPhone+")正在被当前的用户使用，请核实此信息。", null, null);
				}
				TraceService.this.stopSelf();
			}
		}).start();
		
	}

}

package com.kris.trace.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.kris.trace.R;
import com.kris.trace.R.id;
import com.kris.trace.R.layout;
import com.kris.trace.R.menu;
import com.kris.trace.R.string;
import com.kris.trace.manager.TraceManager;
import com.kris.trace.utils.L;
/**
 * 
 *这个是主界面　
 * @author <a href="mailto:kris1987@qq.com">Kris.lee</a>
 * @since  2011-10-9  下午6:18:01
 * @version 1.0.0
 */
public class TracingPhoneActivity extends Activity {

	private static final int 		DIALOG_ENTER_PASSWORD 	= 0x1000;
	
	private Context mContext;
	private TraceManager manager ;
	private String mPassword;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
        manager = new TraceManager(mContext);
        L.i("prepare:"+manager.getPreparePhoneNumber());
        L.i("local:"+manager.getPhoneNumber());
        L.i("sim:"+manager.getSim());
        int step = manager.getStep();
        mPassword =  manager.getPassword();
        if(step == TraceManager.SETP_INFO)
        {
        	L.i("当前是在info步");
        	Intent initIntent = new Intent(mContext,InfoActivity.class);
        	mContext.startActivity(initIntent);
        	finish();
        	return;
        }
        else if(step == TraceManager.SETP_PASSWORD)
        {
        	L.i("当前是在password步");
        	Intent initIntent = new Intent(mContext,PasswordActivity.class);
        	mContext.startActivity(initIntent);
        	finish();
        	return;
        }

		//cancel the title 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
        case R.id.about:
        	Intent aboutIntent = new Intent(this, AboutActivity.class);
        	startActivity(aboutIntent);
        	break;
        case R.id.setting:
        	showDialog(DIALOG_ENTER_PASSWORD);
        	break;
        }
        return false;
        
    }
    @Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_ENTER_PASSWORD:
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	         View v = inflater.inflate(R.layout.input_password, null, false);
	         final EditText passwordText = (EditText)v.findViewById(R.id.password);
	         return new AlertDialog.Builder(mContext)
		         .setTitle(mContext.getString(R.string.dialog_enter_password_title))
		         .setView(v)
		         .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener()
		         {
					
					public void onClick(DialogInterface dialog, int which)
					{
						String password = passwordText.getText().toString();
						if(mPassword.equals(password))
						{
							Intent setIntent = new Intent(mContext,PasswordActivity.class);
							mContext.startActivity(setIntent);
						}
						else
						{
							mHandler.post(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(mContext, R.string.password_uncorrect, Toast.LENGTH_SHORT).show();
								}
							});
						}
					}
		         })
		         .setNegativeButton(R.string.dialog_cancel, null)
		         .create();
		}
		return null;
	}
	private Handler mHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
		
	};
}
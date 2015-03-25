package org.bhd.sdk;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/**
 * 蓝牙状态监听者
 * Intent会携带EXTRA_DEVICE和EXTRA_CLASS附加字段
 * 这个两个字段分别包含了BluetoothDevice和BluetoothClass对象
 */
public class BluetoothStateReceiver extends BroadcastReceiver {
	private static final String TAG = "BluetoothStateReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)){
			int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,-1);
			int previous = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_STATE,-1);
			// 蓝牙现在状态
			if(BluetoothAdapter.STATE_TURNING_ON == state){
				Log.i(TAG,"Current BluetoothState STATE_TURNING_ON");
			}
			if(BluetoothAdapter.STATE_ON == state){
				Log.i(TAG,"Current BluetoothState STATE_ON");
			}
			if(BluetoothAdapter.STATE_OFF == state){
				Log.i(TAG,"Current BluetoothState STATE_OFF");
			}
			if(BluetoothAdapter.STATE_TURNING_OFF == state){
				Log.i(TAG,"Current BluetoothState STATE_TURNING_OFF");
			}
			// 蓝牙之前的状态
			if(BluetoothAdapter.STATE_TURNING_ON == previous){
				Log.i(TAG,"Previous BluetoothState STATE_TURNING_ON");
			}
			if(BluetoothAdapter.STATE_ON == previous){
				Log.i(TAG,"Previous BluetoothState STATE_ON");
			}
			if(BluetoothAdapter.STATE_OFF == previous){
				Log.i(TAG,"Previous BluetoothState STATE_OFF");
			}
			if(BluetoothAdapter.STATE_TURNING_OFF == previous){
				Log.i(TAG,"Previous BluetoothState STATE_TURNING_OFF");
			}
		}

	}
}

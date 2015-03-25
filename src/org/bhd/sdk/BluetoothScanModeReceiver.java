package org.bhd.sdk;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
/**
 * 蓝牙可发现模式监听器
 * 这个Intent对象中包含了EXTRA_SCAN_MODE和EXTRA_PREVIOUS_SCAN_MODE附加字段
 * 它们会分别告诉你新旧扫描模式。它们每个可能的值是：SCAN_MODE_CONNECTABLE_DISCOVERABLE
 * SCAN_MODE_CONNECTABLE或SCAN_MODE_NONE，
 * 它们分别指明设备是在可发现模式下，还是在可发现模式下但依然可接收连接，
 * 或者是在可发现模式下并不能接收连接。 
 */
public class BluetoothScanModeReceiver extends BroadcastReceiver {
	private static final String TAG = "BluetoothScanModeReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action)){
			int state = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE,-1);
			int previous = intent.getIntExtra(BluetoothAdapter.EXTRA_PREVIOUS_SCAN_MODE,-1);
			
			// 蓝牙现在可发现模式
			if(BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE == state){
				Log.i(TAG,"Current BluetoothScanMode CONNECTABLE_DISCOVERABLE");
			}
			if(BluetoothAdapter.SCAN_MODE_CONNECTABLE == state){
				Log.i(TAG,"Current BluetoothScanMode CONNECTABLE");
			}
			if(BluetoothAdapter.SCAN_MODE_NONE == state){
				Log.i(TAG,"Current BluetoothScanMode NONE");
			}
			
			// 蓝牙之前可发现模式
			if(BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE == previous){
				Log.i(TAG,"Previous BluetoothScanMode CONNECTABLE_DISCOVERABLE");
			}
			if(BluetoothAdapter.SCAN_MODE_CONNECTABLE == previous){
				Log.i(TAG,"Previous BluetoothScanMode CONNECTABLE");
			}
			if(BluetoothAdapter.SCAN_MODE_NONE == previous){
				Log.i(TAG,"Previous BluetoothScanMode NONE");
			}
			
		}

	}

}

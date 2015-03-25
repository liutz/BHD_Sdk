package org.bhd.sdk;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bhd.sdk.R;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	/** 请求打开蓝牙动作 **/
	private static final int REQUEST_ENABLE_BT_ACTION = 1;
	/** 请求设备被发现 **/
	private static final int REQUEST_DISCOVERY_ACTION = 2;
	private List<String> mArrayAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
		    // 设备不支持蓝牙
			return;
		}
		// 未打开蓝牙，请求打开
		if (!mBluetoothAdapter.isEnabled()) {
		    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT_ACTION);
		}
		
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
		int devicesSize = pairedDevices.size();
		// 如果有配对设备
		if (devicesSize > 0) {
			mArrayAdapter = new ArrayList<String>(devicesSize);
		    // 循环配对设备
		    for (BluetoothDevice device : pairedDevices) {
		    	// 添加名字和Mac地址到数组中
		        mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		    }
		}
		
		// 注册设备发现，将发现设备添加到数组中
		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		registerReceiver(mDevicesFoundReceiver, filter);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i(TAG,"onActivityResult requestCode "+requestCode+" resultCode "+resultCode+" Intent:"+data);
		// 蓝牙是否打开请求响应
		if(REQUEST_ENABLE_BT_ACTION == requestCode){
			// 打开成功蓝牙
			if(RESULT_OK == resultCode){
				Log.i(TAG,"Bluetooth enable");
			}
			// 用户取消
			if(RESULT_CANCELED == resultCode){
				Log.i(TAG,"User cancel bluetooth");
			}
		}
		
		// 用户选择设备被发现请求响应
		if(REQUEST_DISCOVERY_ACTION == requestCode){
			// 打开成功蓝牙
			if(RESULT_OK == resultCode){
				Log.i(TAG,"Devices have been found [ok]");
			}
			// 用户取消
			if(RESULT_CANCELED == resultCode){
				Log.i(TAG,"Devices have been found [canceled]");
			}
		}
	}
	
	// 创建一个设备发现监听器
	private final BroadcastReceiver mDevicesFoundReceiver = new BroadcastReceiver() {

	    public void onReceive(Context context, Intent intent) {

	        String action = intent.getAction();

	        // 监听器发现到一个设备时
	        if (BluetoothDevice.ACTION_FOUND.equals(action)) {

	            // Get the BluetoothDevice object from the Intent

	            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	            BluetoothClass clazz = intent.getParcelableExtra(BluetoothDevice.EXTRA_CLASS);
	            
	            // Add the name and address to an array adapter to show in a ListView

	            mArrayAdapter.add(device.getName() + "\n" + device.getAddress());

	        }

	    }

	};
	
	/** 设备发现 **/
	public void startDiscovery(View view){
		//1.cancelDiscovery()
		
	}
	
	/** 设备被发现 **/
	public void discovery(View view){
		Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
		// 被发现持续时间设置为300秒
		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
		startActivityForResult(discoverableIntent,REQUEST_DISCOVERY_ACTION);
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.unregisterReceiver(mDevicesFoundReceiver);
	}
}

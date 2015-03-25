package org.bhd.sdk;

import java.util.Set;

import android.bluetooth.BluetoothAdapter;

public class Utils {

	public static boolean supportBluetooth(){
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if (mBluetoothAdapter == null) {
		    // Device does not support Bluetooth
			return false;
		}
		return true;
	}
	
	public static void dd(){
		
	}
	

}

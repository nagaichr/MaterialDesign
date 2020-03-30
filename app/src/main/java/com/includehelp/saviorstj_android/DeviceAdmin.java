package com.includehelp.saviorstj_android;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import timber.log.Timber;

/**
 * Created by user on 6/9/2017.
 */

public class DeviceAdmin extends DeviceAdminReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Timber.d("DeviceAdminReceiver Called");

         Toast.makeText(context, "DeviceAdminReceiver Called", Toast.LENGTH_SHORT).show();
    }
    public void onEnabled(Context context, Intent intent){
        Timber.d("Device Administrator Enabled");

    }

    public void onDisabled(Context context, Intent intent) {
        Timber.d("Device Administrator Disabled");

    }
}

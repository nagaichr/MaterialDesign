package com.includehelp.saviorstj_android;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.io.DataOutputStream;

import androidx.appcompat.app.AppCompatActivity;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    public static final int OVERLAY_PERMISSION_REQ_CODE = 4545;
    protected CustomViewGroup blockingView = null;


    /* for Device Admin*/
    final int REQUEST_CODE = 0;
    DevicePolicyManager mDPM;
    ComponentName mAdminName;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
/*

        //For Disable Status bar Expansion
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "Please give my app this permission!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
            }
            else {
                disableStatusBar();
            }
        }
        else {
            disableStatusBar();
        }

*/
        //Enable Device Admin
       // enableDeviceAdmin();
    }



    protected void disableStatusBar() {

        WindowManager manager = ((WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE));

        WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        localLayoutParams.gravity = Gravity.TOP;
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |

                // this is to enable the notification to receive touch events
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |

                // Draws over status bar
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        localLayoutParams.height = (int) (40 * getResources().getDisplayMetrics().scaledDensity);
        localLayoutParams.format = PixelFormat.TRANSPARENT;

        blockingView = new CustomViewGroup(this);
        manager.addView(blockingView, localLayoutParams);
    }


    /**
     * This class creates the overlay on the status bar which stops it from expanding.
     */
    public static class CustomViewGroup extends ViewGroup {

        public CustomViewGroup(Context context) {
            super(context);
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            Log.v("customViewGroup", "********** Status bar swipe intercepted");
            return true;
        }
    }


    /**
     * Method to Enable Device Admin
     */
    private void enableDeviceAdmin(){
        try{
            /* To enable Device Admin */
            // Initiate DevicePolicyManager.
            mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            Timber.e("AAA");

            // Set DeviceAdminDemo Receiver for active the component with different option
            mAdminName = new ComponentName(this, DeviceAdmin.class);
            Timber.e("bbb");

            if (!mDPM.isAdminActive(mAdminName)) {
                // try to become active
                Intent ii = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                ii.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
                ii.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Click on Activate button to secure your application.");
                startActivityForResult(ii, REQUEST_CODE);
                Timber.e("ccc");

            }
            else{
                Timber.e("Already Device Admin");
                // Already is a device administrator, can do security operations now.
                // mDPM.lockNow();
                // mDPM.wipeData(DevicePolicyManager.WIPE_EXTERNAL_STORAGE);
            }
        }
        catch (Exception e){
            Timber.e("MainActivity DeviceAdmin Exp: "+e.toString());
        }
    }


    /**
     * to Enable Wifi
     * @param view
     */
    public void enableWiFi(View view){
        WifiManager wManager = (WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wManager.isWifiEnabled()) {
            Boolean status = wManager.setWifiEnabled(true); //true or false
            Timber.e("WifI Enbale Status : "+status);
        }
    }

    /**
     * Method to Set Date and Time
     * @param view
     */
    public void setDateTime(View view) {
        try {

            Process process = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            String command = "date 1123104017\n";
            // Log.e("command",command);
            os.writeBytes(command);
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();

        }
        catch (Exception e) {
            Timber.e("Exception: %s",e.toString());
       }
    }
}

package com.includehelp.saviorstj_android;

import android.Manifest;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.includehelp.saviorstj_android.databinding.ActivityMainBinding;
import com.includehelp.saviorstj_android.fragments.CommModeFragment;
import com.includehelp.saviorstj_android.fragments.ConfirmPasswordFragment;
import com.includehelp.saviorstj_android.fragments.HomeFragment;
import com.includehelp.saviorstj_android.fragments.RemovePaycodeFragment;
import com.includehelp.saviorstj_android.fragments.ResetPasswordFragment;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    public static final int OVERLAY_PERMISSION_REQ_CODE = 4545;
    public static final int MULTIPLE_PERMISSIONS  = 101;
    protected CustomViewGroup blockingView = null;
    ActivityMainBinding binding;


    /* for Device Admin*/
    final int REQUEST_CODE = 0;
    DevicePolicyManager mDPM;
    ComponentName mAdminName;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //view binding implemented
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context=this;

        if(checkPermissions()){
            HomeFragment homeFragment = new HomeFragment();
            setFragment(homeFragment);

//            CameraFragment cameraFragment = new CameraFragment();
//            setFragment(cameraFragment);
        }



//        //For Disable Status bar Expansion
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            if (!Settings.canDrawOverlays(this)) {
//                Toast.makeText(this, "Please give my app this permission!", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
//                startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
//            }
//            else {
//                disableStatusBar();
//            }
//        }
//        else {
//            disableStatusBar();
//        }


        //Enable Device Admin
       // enableDeviceAdmin();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("lifecycle","onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lifecycle","onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("lifecycle","onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("lifecycle","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("lifecycle","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lifecycle","onDestroy invoked");
    }


    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();
        //Toast.makeText(this, "Count : "+count, Toast.LENGTH_SHORT).show();

        if (count == 1) {
            finish();
            //additional code
        }
        else {
            getSupportFragmentManager().popBackStack();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {  // permissions granted.
                    Toast.makeText(context, "Permission Granted : "+grantResults.length, Toast.LENGTH_SHORT).show();
                            HomeFragment homeFragment = new HomeFragment();
                            setFragment(homeFragment);

//                    CameraFragment cameraFragment = new CameraFragment();
//                    setFragment(cameraFragment);
                }
                break;
            }
        }
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



    //To show Dialog Fragment
    public void setDialogFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        fragmentTransaction.addToBackStack(null);

        if (fragment instanceof ConfirmPasswordFragment) {
            ((ConfirmPasswordFragment) fragment).show(fragmentTransaction, "dialog");
        }
        else if (fragment instanceof ResetPasswordFragment) {
            ((ResetPasswordFragment) fragment).show(fragmentTransaction, "dialog");
        }
        else if (fragment instanceof RemovePaycodeFragment) {
            ((RemovePaycodeFragment) fragment).show(fragmentTransaction, "dialog");
        }
        else if (fragment instanceof CommModeFragment) {
            ((CommModeFragment) fragment).show(fragmentTransaction, "dialog");
        }

    }

    //To set Fragment
    public void setFragment(Fragment fragment) {
        FragmentTransaction  fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentContainer,fragment,"Fragment");
        fragmentTransaction.addToBackStack("Fragment");
        fragmentTransaction.commit();
    }


    private boolean checkPermissions() {
        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.CHANGE_NETWORK_STATE
        }; // Here i used multiple permission check

        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getApplicationContext(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
}

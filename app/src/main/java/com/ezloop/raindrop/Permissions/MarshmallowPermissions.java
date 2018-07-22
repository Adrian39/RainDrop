package com.ezloop.raindrop.Permissions;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class MarshmallowPermissions {
    public static final int FINE_LOCATION_REQUEST_CODE = 1;
    public static final int COARSE_LOCATION_REQUEST_CODE = 2;
    Activity mActivity;

    public MarshmallowPermissions(Activity activity){
        this.mActivity = activity;
    }

    public void requestPermissionForFineLocation(){
        if (ActivityCompat.shouldShowRequestPermissionRationale
                (mActivity, Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(mActivity,
                    "Fine Location permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    FINE_LOCATION_REQUEST_CODE);
        }
    }

    public void requestPermissionForCoarseLocation(){
        if (ActivityCompat.shouldShowRequestPermissionRationale
                (mActivity, Manifest.permission.ACCESS_COARSE_LOCATION)){
            Toast.makeText(mActivity,
                    "Coarse Location permission needed. Please allow in App Settings for additional functionality.",
                    Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    COARSE_LOCATION_REQUEST_CODE);
        }
    }
}

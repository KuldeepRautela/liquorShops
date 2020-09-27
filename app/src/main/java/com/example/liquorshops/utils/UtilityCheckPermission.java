package com.example.liquorshops.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.liquorshops.R;


public class UtilityCheckPermission {

    public static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 121;
    public static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 122;

    public static boolean checkPermission(final Context context, int i) {
        if (i == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            return checkPermission(context, "Network permission is necessary for Providing Better User Experience.",
                    Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else if (i == MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE) {
            return checkPermission(context, "External storage permission is necessary without it you Cannot Access Images from Gallery Or Other File Manager"
                    , Manifest.permission.WRITE_EXTERNAL_STORAGE, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private static boolean checkPermission(final Context context, final String message, final String permission, final int code) {
       // if (CheckOs.checkBuildMarshmallow()) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context, R.style.AppTheme);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission Requirement");
                    alertBuilder.setMessage(message);
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{permission
                                            , Manifest.permission.READ_EXTERNAL_STORAGE
                                            , Manifest.permission.WRITE_EXTERNAL_STORAGE}, code);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();

                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{permission, Manifest.permission.READ_EXTERNAL_STORAGE}, code);
                }
                return false;
            } else {
                return true;
            }
//        } else {
//            return true;
//        }
//    }
}}


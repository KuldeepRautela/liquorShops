package com.example.liquorshops.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.liquorshops.R;
import com.example.liquorshops.utils.CheckConnection;
import com.example.liquorshops.utils.UtilityCheckPermission;

import static com.example.liquorshops.utils.constants.Constants.TIME_DELAYED;

public class SplashActivity extends AppCompatActivity {
   private Handler handler;
   private   Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (CheckConnection.checkCon(getApplicationContext()))
        askForPermission();
        else
            Toast.makeText(SplashActivity.this, R.string.connection_check_no_internet, Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        };
        handler.postDelayed(runnable, TIME_DELAYED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (runnable != null)
            handler.removeCallbacks(runnable);
    }

    void askForPermission() {
        if(UtilityCheckPermission.checkPermission(getApplicationContext(),UtilityCheckPermission.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION))
        startMainActivity();
    }



    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            askForPermission();
    }
}

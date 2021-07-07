package com.masai.permissions_we;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnLocation;
    private Button mBtnContacts;
    private static final int REQ_CONTACTS_CODE = 101;
    private static final int REQ_LOCATIONS_CODE = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewAndListener();
    }

    private void initViewAndListener() {
        mBtnContacts = findViewById(R.id.btnContactPermission);
        mBtnLocation =findViewById(R.id.btnLocationPermission);
        mBtnLocation.setOnClickListener(this);
        mBtnContacts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnContactPermission:
                boolean isContactPermissionGranted = ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED;
                if(!isContactPermissionGranted) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQ_CONTACTS_CODE);
                }
                else{
                    Toast.makeText(MainActivity.this,"Contact permission already allowed",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLocationPermission:
                boolean isLocationGranted = ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED;
                if(!isLocationGranted) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQ_LOCATIONS_CODE);
                }
                else{
                    Toast.makeText(MainActivity.this,"Location permission already allowed",Toast.LENGTH_SHORT).show();
        }
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_CONTACTS_CODE:
                if (grantResults.length >= 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Contact permission allowed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Contact permission denied", Toast.LENGTH_SHORT).show();
                }
                break;

            case REQ_LOCATIONS_CODE:
                if(grantResults.length>=1 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"Location permission allowed",Toast.LENGTH_SHORT).show();
                }
                else if(grantResults.length>=1 && grantResults[0]==PackageManager.PERMISSION_DENIED){
                    if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[0])) {
                        Toast.makeText(MainActivity.this, "Location permission denied", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"selected do not show again go to setting for enabled",Toast.LENGTH_SHORT).show();
                    }

        }

                break;
        }

    }
}
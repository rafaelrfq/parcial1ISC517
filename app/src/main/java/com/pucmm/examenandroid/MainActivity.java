package com.pucmm.examenandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private boolean hasCamera = false;
    private boolean hasCameraPermission = false;
    private boolean hasReadPermission = false;
    private boolean hasWritePermission = false;
    private boolean hasAllPermissions = false;
    private static final Integer REQUEST_CODE_CAMERA = 1;
    private static final Integer REQUEST_CODE_READ_STORAGE = 2;
    private static final Integer REQUEST_CODE_WRITE_STORAGE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = getResources().getConfiguration().orientation;
        int columnCount;

        if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            hasCamera = true;
        }
        // Camera
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && hasCamera) {
            hasCameraPermission = true;
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.CAMERA }, REQUEST_CODE_CAMERA);
        }
        // Read storage
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            hasReadPermission = true;
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_CODE_READ_STORAGE);
        }
        // Write storage
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            hasWritePermission = true;
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE_WRITE_STORAGE);
        }

        // check all permissions
        if (hasCameraPermission && hasReadPermission && hasWritePermission) {
            hasAllPermissions = true;
        }

        if ((orientation == Configuration.ORIENTATION_PORTRAIT)) {
            setContentView(R.layout.activity_main);
            columnCount = 2;
        } else {
            setContentView(R.layout.activity_main_dual);
            columnCount = 1;
        }

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_parent, ItemFragment.newInstance(columnCount, hasAllPermissions))
                .commit();
    }
}
package com.pucmm.examenandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private boolean hasCamera = false;
    private boolean hasPermissions = false;
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
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            hasPermissions = true;
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.CAMERA }, REQUEST_CODE_CAMERA);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, REQUEST_CODE_READ_STORAGE);
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_CODE_WRITE_STORAGE);
        }

        if ((orientation == Configuration.ORIENTATION_PORTRAIT)) {
            setContentView(R.layout.activity_main);
            columnCount = 2;
        } else {
            setContentView(R.layout.activity_main_dual);
            columnCount = 1;
        }

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true)
                .add(R.id.fragment_parent, ItemFragment.newInstance(columnCount))
                .commit();
    }
}
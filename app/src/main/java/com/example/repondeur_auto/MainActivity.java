package com.example.repondeur_auto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public static Context contextOfApplication;

    public boolean isPermissionGranted() {
        // Return true if user has given his permission to read incoming messages

        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    public void requestContactsPermission() {
        // Ask user for his permission to read incoming messages

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_CONTACTS)) {
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_CONTACTS,
        }, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contextOfApplication = getApplicationContext();

        ViewPager viewPager = findViewById(R.id.activity_main_viewpager);

        // Instanciate the adapter
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), getContentResolver());

        // Bind the Adapter to the viewPager
        viewPager.setAdapter(pagerAdapter);

        // Bind the Tab bar to the viewpager
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        if (!isPermissionGranted()) {
            requestContactsPermission();
        }
    }


}
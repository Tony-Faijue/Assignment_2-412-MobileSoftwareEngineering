package com.example.assignment_2_412_mobilesoftwareengineering;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Main Activity of the Android application
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        System.out.println(R.id.fullName);
        String appName = getString(R.string.app_name);
        String firstName = getString(R.string.first_name);
        String lastName = getString(R.string.last_name);
        String fullName = firstName + " " + lastname;
    }
}

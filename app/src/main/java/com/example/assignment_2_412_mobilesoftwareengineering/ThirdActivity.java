package com.example.assignment_2_412_mobilesoftwareengineering;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        //inflate layout
        setContentView(R.layout.third_activity);
        //initialize ui components
        Button captureButton  = (Button) findViewById(R.id.capture_img_btn);

        //set up onlcick listener event for capture button
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });





        //setup on click listener events
    }
}

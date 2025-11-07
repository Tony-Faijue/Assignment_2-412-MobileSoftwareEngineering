package com.example.assignment_2_412_mobilesoftwareengineering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main Activity of the Android application
 * Use of an android jetpack class to use newer UI features
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        //inflate the layout
        setContentView(R.layout.activity_main);

        //initialize UI components

        //Create button objects, find them by their corresponding id
        Button implicitButton = (Button) findViewById(R.id.implicit_call);
        Button explicitButton = (Button) findViewById(R.id.explicit_call);
        Button viewImgActivityBtn = (Button) findViewById(R.id.view_img_activity);

        //setup Listeners

        //set on click listeners for each button that calls activity with intent
        implicitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openNewImplicitActivity();
            }
        });
        explicitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openNewExplicitActivity();
            }
        });
        //Add on click listener event for view img activity button
        viewImgActivityBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openThirdActivity();
            }});
    }

    /**
     * Method to start second activity implicitly
     *
     */
    public void openNewImplicitActivity(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        startActivity(intent);
    }

    /**
     * Method to start second activity explicitly
     */
    public void openNewExplicitActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    /**
     * Method to start third activity (view img activity)
     *
     */
    public void openThirdActivity(){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}

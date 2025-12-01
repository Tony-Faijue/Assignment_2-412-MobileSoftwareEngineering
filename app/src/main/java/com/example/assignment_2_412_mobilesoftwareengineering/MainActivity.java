package com.example.assignment_2_412_mobilesoftwareengineering;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Main Activity of the Android application
 * Use of an android jetpack class to use newer UI features
 */
public class MainActivity extends AppCompatActivity {
    private static final String PERMISSION_MSE412 = "com.example.assignment_2_412_mobilesoftwareengineering.MSE412";
    private ActivityResultLauncher<String> requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        //inflate the layout
        setContentView(R.layout.activity_main);

        //Initialize the Activity Result Launcher
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(), isGranted -> {
                    if(isGranted){
                        //Toast.makeText(this, "Permission GRANTED! Opening Second Activity: " + PERMISSION_MSE412, Toast.LENGTH_SHORT).show();
                        openNewExplicitActivity();
                    } else {
                        Toast.makeText(this, "Enable MSE412 Permission in settings to use this feature.", Toast.LENGTH_SHORT).show();
                    }
                }
        );


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
                //Request Permissions Before Explicit call
                handleExplicitActivityLaunch();
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

    /**
     *
     * @return true if the permission is granted, false otherwise
     */
    public boolean checkMSE412Permission(){
        return ContextCompat.checkSelfPermission(this, PERMISSION_MSE412) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Method to check permission and launch the request or second activity
     */
    private void handleExplicitActivityLaunch(){
    //Request Permission at runtime for Second Activity before it is started
        if(checkMSE412Permission()){ //Permission is granted, open second activity
            //Toast.makeText(this, "Permission already granted for MSE412: " + PERMISSION_MSE412, Toast.LENGTH_SHORT).show();
            openNewExplicitActivity();
        } else if(ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_MSE412)) {
            //Show Rational for permission
            new AlertDialog.Builder(this)
                    .setTitle("Permission is Needed")
                    .setMessage("This app needs custom permission to open second activity. Allow the permission MSE412, to access Mobile Software Engineering Challenges")
                    .setPositiveButton("OK", (dialog, which) -> requestPermissionLauncher.launch(PERMISSION_MSE412))
                    .setNegativeButton("Cancel", null).show();
        } else{
            requestPermissionLauncher.launch(PERMISSION_MSE412);
        }
    }

}

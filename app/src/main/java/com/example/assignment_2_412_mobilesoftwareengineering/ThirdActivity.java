package com.example.assignment_2_412_mobilesoftwareengineering;

import android.Manifest;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;

public class ThirdActivity extends AppCompatActivity {

    private File photoFile;
    private ImageView photoView;

    //Camera Permission Request Launcher; Requesting Camera Permissions From the User
     private final ActivityResultLauncher<String> requestCameraPermissionLauncher =
             registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                  if (isGranted){
                     //Permission is granted, open the default camera
                      startDefaultCamera();
                  }
                  else {
                      //Permission is denied, let the user know to enable the permissions through settings
                      Toast.makeText(this, "Go to settings and enable camera permissions to use this feature", Toast.LENGTH_SHORT).show();
                  }
             }
    );
     // Camera Intent Launcher, to capture the image photo
    //Use of the Activity Result Contracts TakePicture function to take a picture an saving it in the ImageView
    private final ActivityResultLauncher<Uri> takePictureLauncher = registerForActivityResult(new ActivityResultContracts.TakePicture(), result ->{
        //Handle the resulting photo data
         if(result && photoFile != null){
             photoView.setImageBitmap(BitmapFactory.decodeFile(photoFile.getAbsolutePath()));
         } else{
             Toast.makeText(this, "Capture photo failed", Toast.LENGTH_SHORT).show();
         }
     });


    @Override
    protected void onCreate(Bundle savedInstances){
        super.onCreate(savedInstances);
        //inflate layout
        setContentView(R.layout.third_activity);
        //initialize ui components
        Button captureButton  = (Button) findViewById(R.id.capture_img_btn);
        //image view to store captured image to display
         photoView = findViewById(R.id.capture_photo_view);

        //set up onclick listener event for capture button
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Request Camera Permission when the button is clicked
                //Use the launch method to execute the ActivityResultContract, with Camera Permissions
                requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
            }
        });

    }

    //Method to create a temporary image file in the cache directory
    private File createImageFile() throws IOException{
        //create new file in the cache directory in the images directory
        File imagesDir = new File(getCacheDir(), "images");
        if(!imagesDir.exists()){
            //Create directory if not already created
            imagesDir.mkdirs();
        }
        return File.createTempFile("photo_", ".jpg", imagesDir);
    }

    //Launch the default camera app
    private void startDefaultCamera(){
        try{
            photoFile = createImageFile();
            String authority = getPackageName() + ".fileprovider";
            Uri photoUri = FileProvider.getUriForFile(this, authority, photoFile);

            //Launch takePictureLauncher with the destination Uri
            takePictureLauncher.launch(photoUri);

        } catch (IOException e) {
            Toast.makeText(this, "Unable to create file for photo", Toast.LENGTH_SHORT).show();
        }
    }
}

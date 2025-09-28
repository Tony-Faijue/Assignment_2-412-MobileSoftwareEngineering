package com.example.assignment_2_412_mobilesoftwareengineering;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        //inflate layout
        setContentView(R.layout.second_activity);

        //initialize UI components
        //Create object components and find them by their ids

        Button mainButton = (Button) findViewById(R.id.main_activity_btn);
        ListView mobileChallenges = (ListView) findViewById(R.id.mobile_challenges_list);

        //on click listener for the main activity button
        mainButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMainActivity();
            }
        });
        //Access String Array items in Resources

        //Array of challenges from String Array Resources
        String[] challengesArray = getResources().getStringArray(R.array.mobile_challenges_array);
        //Use of an ArrayAdapter to fill the listview of strings
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, challengesArray);
        //Set adapter to the ListView
        mobileChallenges.setAdapter(adapter);
    }

    /**
     * Start the main activity explicitly
     */
    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

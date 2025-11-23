package com.example.assignment_2_412_mobilesoftwareengineering;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(AndroidJUnit4.class)
public class AutomatedUITest {

    private static final String PACKAGE = "com.example.assignment_2_412_mobilesoftwareengineering";
    private UiDevice myDevice;
    private static final int MAX_TIME = 5000;

    @Before
    /**
     *
     * Method to run before any test to launch the application
     * from the home screen
     */
    public void startMainActivityFromHomeScreen(){
        //Initialize UiDevice instance
        myDevice = UiDevice.getInstance(getInstrumentation());
        //Start from home screen
        myDevice.pressHome();
        //Wait for launcher to appear
        assertNotNull(PACKAGE);
        myDevice.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), MAX_TIME);
        //Launch the app
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE);
        assertNotNull(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        //Wait for the app to appear
        myDevice.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), MAX_TIME);
    }

    @Test
    /**
     * Test the Explicit Activity has started
     */
    public void testStartExplicitActivity(){
        //Start Explicit activity
        UiObject2 explicitCallBtn = myDevice.wait(Until.findObject(By.res(PACKAGE,"explicit_call")), MAX_TIME);
        assertNotNull(explicitCallBtn);
        explicitCallBtn.click();

        //Check if unique elements of the second activity exist

        //Verify mobile challenges list exist
        UiObject2 challengesList = myDevice.wait(Until.findObject(By.res(PACKAGE,"mobile_challenges_list")), MAX_TIME);
        assertNotNull(challengesList);

        //Verify the start main activity button exist
        UiObject2 startMainActivityBtn = myDevice.wait(Until.findObject(By.res(PACKAGE,"main_activity_btn")), MAX_TIME);
        assertNotNull(startMainActivityBtn);
    }
    @Test
    /**
     * Test The Mobile Challenges Exist in the Activity
     */
    public void testHasMobileSoftwareEngineeringChallenges(){
        //Start Explicit activity
        UiObject2 explicitCallBtn = myDevice.wait(Until.findObject(By.res(PACKAGE,"explicit_call")), MAX_TIME);
        assertNotNull(explicitCallBtn);

        explicitCallBtn.click();

        UiObject2 challengesList = myDevice.wait(Until.findObject(By.res(PACKAGE,"mobile_challenges_list")), MAX_TIME);
        assertNotNull(challengesList);

        //Check if all all mobile challenges strings exist in the list
        String[] verifiedStrings = {"Device Fragmentation", "OS Fragmentation",
                "Rapid Changes", "Unstable and Dynamic Environments", "Low Tolerance from Users", "Low Security and Privacy Awareness"};

        //Add the verified challenges to a HashSet
        HashSet<String> viewedStrings = new HashSet<>();
        for(String challengeItem: verifiedStrings){
            viewedStrings.add(challengeItem);
        }


        int maxScrollAttempts = 7;

        for(int i = 0; i < maxScrollAttempts && !viewedStrings.isEmpty(); i++){
            //Create a list of challenges from the ListView
            List<UiObject2> challenges = challengesList.findObjects(By.clazz("android.widget.TextView"));
            assertNotNull(challenges);

            //Remove challenge if it already exist in the HashSet
            for(UiObject2 challengeItem: challenges){
                String itemText = challengeItem.getText();
                if(itemText != null && viewedStrings.contains(itemText)){
                    viewedStrings.remove(itemText);
                }
            }
            if(!viewedStrings.isEmpty()){
                //Scroll through the list
                challengesList.scroll(Direction.DOWN, 0.7f);
            }

        }
        //Assert that the HashSet is empty
        assertTrue(viewedStrings.isEmpty());
    }


}

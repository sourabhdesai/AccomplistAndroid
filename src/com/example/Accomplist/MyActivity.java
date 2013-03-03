package com.example.Accomplist;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class MyActivity extends Activity {
    //Main Splash Screen
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle splashScreen) {
        super.onCreate(splashScreen);
        setContentView(R.layout.main);
       Thread timer= new Thread(){
        public void run()   {
        try {
            sleep(5000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }   finally {
            Intent mainScreen= new Intent ("com.example.Accomplist.MAINSCREEN");
            startActivity(mainScreen);
        }
        }
       };
        timer.start();
    }
}

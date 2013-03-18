package com.example.Accomplist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 3/16/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class MasterScreen extends Activity {
    TextView topEvents;
    TextView searchEvents;
    TextView searchTags;
    TextView searchUsers;
    TextView myProfile;
    TextView about;
    TextView credits;

    private static final String eventURL="http://accomplist.herokuapp.com/api/v1/sharedevent/?format=json";
    private static final String tagURL="http://accomplist.herokuapp.com/api/v1/sharedevent/?format=json";
    private static final String usersURL="http://accomplist.herokuapp.com/api/v1/sharedevent/?format=json";

    public static String searchUrl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_screen);
        topEvents = (TextView) findViewById(R.id.topevents);
        topEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                try {
                    Class topEventsClass= Class.forName("com.example.Accomplist.MainScreen");
                    Intent topEventsIntent= new Intent(MasterScreen.this, topEventsClass);
                    startActivity(topEventsIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        searchEvents = (TextView) findViewById(R.id.srchEvents);
        searchEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                try {
                    searchUrl=eventURL;
                    Class searchAct= Class.forName("com.example.Accomplist.SearchActivity");
                    Intent searchActIntent= new Intent(MasterScreen.this, searchAct);
                    startActivity(searchActIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        searchTags = (TextView) findViewById(R.id.srchTags);
        searchTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                try {
                    searchUrl=tagURL;
                    Class searchAct= Class.forName("com.example.Accomplist.SearchActivity");
                    Intent searchActIntent= new Intent(MasterScreen.this, searchAct);
                    startActivity(searchActIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        searchUsers = (TextView) findViewById(R.id.srchUsers);
        searchUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                try {
                    searchUrl=usersURL;
                    Class searchAct= Class.forName("com.example.Accomplist.SearchActivity");
                    Intent searchActIntent= new Intent(MasterScreen.this, searchAct);
                    startActivity(searchActIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        credits = (TextView) findViewById(R.id.credits);
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                try {
                    Class creditsAct= Class.forName("com.example.Accomplist.CreditsScreen");
                    Intent creditsActIntent= new Intent(MasterScreen.this, creditsAct);
                    startActivity(creditsActIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        about = (TextView) findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To change body of implemented methods use File | Settings | File Templates.
                try {
                    Class creditsAct= Class.forName("com.example.Accomplist.AboutScreen");
                    Intent creditsActIntent= new Intent(MasterScreen.this, creditsAct);
                    startActivity(creditsActIntent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

    }
}

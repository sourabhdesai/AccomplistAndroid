package com.example.Accomplist;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 3/1/13
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewEvent extends Activity {
    static final int objectsIndex = 0;
    //Following comment block is example of json object from above url
    {
    /*

    {
   "date":"2013-01-17T12:48:24.326859",
   "event":{
      "description":"I want to find work that I love so that I never have to work a day in my life.",
      "id":3,
      "resource_uri":"/api/v1/event/3/",
      "title":{
         "id":3,
         "listitem":"Get a Job I Enjoy",
         "resource_uri":"/api/v1/title/3/"
      },
      "user":{
         "date_joined":"2013-01-17T12:40:17.115755",
         "email":"sourabhdesai@gmail.com",
         "first_name":"",
         "id":3,
         "is_active":true,
         "is_staff":false,
         "is_superuser":false,
         "last_login":"2013-02-02T15:51:11.622505",
         "last_name":"",
         "password":"pbkdf2_sha256$10000$ETuiYJXQjvez$5yHd5IPaQpS0SvlissO/JydwXUHhMYDgadY7uyTyWg0=",
         "resource_uri":"/api/v1/user/3/",
         "username":"sourabhd"
      }
   },
   "id":2,
   "resource_uri":"/api/v1/sharedevent/2/",
   "votes":7
}

     */
    }
    private static final String TAG_OBJECTS="objects";        //A JSON array from the main JSON Object
    private static final String TAG_DATE="date";              //A JSON tag within the JSON array OBJECTS
    private static final String TAG_EVENT="event";            //A JSON tag within the JSON array OBJECTS
    private static final String TAG_VOTES="votes";            //A JSON tag within the JSON array OBJECTS
    private static final String TAG_DESC="description";       //A JSON tag within the JSON array OBJECTS
    private static final String TAG_TITLE="title";            //A JSON tag within the JSON object EVENT
    private static final String TAG_LI="listitem";            //A JSON tag within the JSON object TITLE
    private static final String TAG_USER="user";              //A JSON tag within the JSON object EVENT
    private static final String TAG_UN="username";            //A JSON tag within the JSON object USER

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_event);
        new JSONParse().execute(MainScreen.objectsIndex);
    }
    private class JSONParse extends AsyncTask<Integer, Void,String[]> {

        @Override
        protected String[] doInBackground(Integer... objectsIndex) {
                try {
                        JSONArray objectsJSON=MainScreen.jsonObj.getJSONArray(TAG_OBJECTS);
                        JSONObject specUserJSON= objectsJSON.getJSONObject(MainScreen.objectsIndex);
                        String upVotes= "Votes: "+specUserJSON.getString(TAG_VOTES);
                        String dateStringLong= specUserJSON.getString(TAG_DATE);
                        String dateStringShort= MainScreen.Months[Integer.parseInt(dateStringLong.substring(5,7))-1] +" "+dateStringLong.substring(8,10)+", "+dateStringLong.substring(0,4); //Example: January 17, 2013
                        JSONObject eventJSON=specUserJSON.getJSONObject(TAG_EVENT);
                        String descString= eventJSON.getString(TAG_DESC);
                        JSONObject titleJSON= eventJSON.getJSONObject(TAG_TITLE);
                        String eventTitleStr= titleJSON.getString(TAG_LI);
                        JSONObject userJSON= eventJSON.getJSONObject(TAG_USER);
                        String usernameStr= userJSON.getString(TAG_UN);
                        String[] eventDetails= new String[5];
                        eventDetails[0]= eventTitleStr;
                        eventDetails[1]= descString;
                        eventDetails[2]= dateStringShort;
                        eventDetails[3]= upVotes;
                        eventDetails[4]= usernameStr;
                        return eventDetails;
                }
                catch (JSONException e1) {
                    e1.printStackTrace();
                }
        return null;
        }
        protected void onProgressUpdate() {
            Toast loadingToast= Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG);
            loadingToast.show();
        }
        protected void onPostExecute(String[] singleEventDetails) {
            /*
                        eventDetails[0]= eventTitleStr;
                        eventDetails[1]= descString;
                        eventDetails[2]= dateStringShort;
                        eventDetails[3]= usernameStr;
                        eventDetails[4]= usernameStr;
            */
            TextView title= (TextView) findViewById(R.id.title);
            TextView desc= (TextView) findViewById(R.id.description);
            TextView date= (TextView) findViewById(R.id.date);
            TextView votes= (TextView) findViewById(R.id.upvotes);
            TextView un= (TextView) findViewById(R.id.username);
            title.setText(singleEventDetails[0]);
            desc.setText(singleEventDetails[1]);
            date.setText(singleEventDetails[2]);
            votes.setText(singleEventDetails[3]);
            un.setText(singleEventDetails[4]);
        }
    }
}
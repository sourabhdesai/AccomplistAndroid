package com.example.Accomplist;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
public class MainScreen extends ListActivity{
   static JSONObject jsonObj= null;
   static int objectsIndex;

    ArrayList<String> eventArr=new ArrayList<String>();
    int i=0;

    String url= "http://accomplist.herokuapp.com/api/v1/sharedevent/?format=json";//"http://accomplist.herokuapp.com/api/v1/sharedevent/1/?format=json";


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
    private static final String TAG_OBJECTS="objects"; //A JSON array from the main JSON Object
    private static final String TAG_EVENT="event";     //A JSON tag within the JSON array OBJECTS
    private static final String TAG_VOTES="votes";     //A JSON tag within the JSON array OBJECTS
    private static final String TAG_TITLE="title";     //A JSON tag within the JSON object EVENT
    private static final String TAG_LI="listitem";     //A JSON tag within the JSON object TITLE
    private static final String TAG_USER="user";       //A JSON tag within the JSON object EVENT
    private static final String TAG_UN="username";     //A JSON tag within the JSON object USER
    public final static String[] Months= {"January","February","March","April","May","June","July","August","September","November","December"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        ListView eventsList= getListView();
        new JSONParse().execute(url);
    }

    protected void onListItemClick (ListView l, View v, int position, long id)  {
        super.onListItemClick(l,v,position,id);
        objectsIndex=position;
        try {
            Class viewEvent= Class.forName("com.example.Accomplist.ViewEvent");
            Intent viewEventIntent= new Intent(MainScreen.this, viewEvent);
            startActivity(viewEventIntent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
private class JSONParse extends AsyncTask<String, Void, ArrayList<String>> {

    @Override
    protected ArrayList<String> doInBackground(String... str) {
        HttpClient client=new DefaultHttpClient();
        HttpUriRequest request= new HttpGet(url);
        HttpResponse r= null;
        try {
            r = client.execute(request);
        } catch (ClientProtocolException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        int status= r.getStatusLine().getStatusCode();
        if (status==200){
            HttpEntity e=r.getEntity();
            String data= null;
            try {
                data = EntityUtils.toString(e);
            } catch (IOException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                jsonObj = new JSONObject(data);
            } catch (JSONException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                JSONArray objectsJson= jsonObj.getJSONArray(TAG_OBJECTS);    //I guess its not getting the JSONArray...
                for (int n=0; n<objectsJson.length(); n++)  {
                    JSONObject singleJSON=objectsJson.getJSONObject(n);
                    String upVotes= singleJSON.getString(TAG_VOTES);
                    JSONObject eventJSON=singleJSON.getJSONObject(TAG_EVENT);
                    JSONObject titleJSON= eventJSON.getJSONObject(TAG_TITLE);
                    String eventTitleStr= titleJSON.getString(TAG_LI);
                    JSONObject userJSON= eventJSON.getJSONObject(TAG_USER);
                    String usernameStr= userJSON.getString(TAG_UN);
                    String totalEventStr=upVotes+" Pts: "+"\""+eventTitleStr+"\" -"+usernameStr; //Example: '"Get a Job I Enjoy" -sourabhd'
                    eventArr.add(totalEventStr);
                }
                return eventArr;
            }
            catch (JSONException e1) {
             e1.printStackTrace();
            }
        }
        else{
            eventArr.add("Didn't Work Bro");
            eventArr.add("Try Again! Dont Give Up!!");
            return eventArr;
        }
        eventArr.add("Alright Somethings Really Fucked Up");
        return eventArr;
    }
    protected void onProgressUpdate() {
        Toast loadingToast= Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG);
        loadingToast.show();
    }
    protected void onPostExecute(ArrayList<String> result) {
        MainScreen.this.setListAdapter(new ArrayAdapter<String>(MainScreen.this,
                android.R.layout.simple_list_item_1,result));
    }
}
}
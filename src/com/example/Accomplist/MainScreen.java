package com.example.Accomplist;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
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

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 3/1/13
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreen extends Activity {
    TextView myTextView;
    JSONObject jsonObj= null;

   // HttpClient client;
    // url to make request
    final static String url = "http://accomplist.herokuapp.com/api/v1/sharedevent/2/?format=json";
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
    private static final String TAG_EVENT="event"; //A JSON object within the JSON object that will be returned by JSONParse()
    private static final String TAG_DESCRIPTION="description"; //A JSON tag within the JSON object EVENT
    private static String eventString="Yo";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        new JSONParse().execute("");
    }
private class JSONParse extends AsyncTask<String, Void, String> {
    HttpClient client=new DefaultHttpClient();

    @Override
    protected String doInBackground(String... jsonurl) {
//        StringBuilder url= new StringBuilder(String.valueOf(jsonurl));
        HttpUriRequest request= new HttpGet("http://accomplist.herokuapp.com/api/v1/sharedevent/2/?format=json");
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
                JSONObject eventJson= jsonObj.getJSONObject(TAG_EVENT);
                eventString= eventJson.getString(TAG_DESCRIPTION);
            }
            catch (JSONException e1) {
                eventString="Couldn't Parse Data";
            }
            return eventString;
        }
        else{
            return eventString;
        }
    }
    protected void onProgressUpdate() {
        Toast loadingToast= Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG);
        loadingToast.show();
    }
    protected void onPostExecute(String result) {
        eventString=result;

        myTextView= (TextView)findViewById(R.id.textView1);
        myTextView.setText(eventString);

    }
}
}
package com.example.Accomplist;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
 * Date: 3/9/13
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDetailsActivity extends Activity {
        JSONObject usersJsonObj= null;

        String url= "http://accomplist.herokuapp.com/api/v1/userprofile/?format=json";

        private static final String TAG_OBJECTS="objects"; //A JSON array from the main JSON Object
        private static final String TAG_FN="firstName"; //A JSON array from the JSONObject objects
        private static final String TAG_LN="lastName"; //A JSON array from the JSONObject objects
        private static final String TAG_ID="id"; //A JSON array from the JSONObject objects
        private static final String TAG_PTS="points"; //A JSON array from the JSONObject objects
        private static final String TAG_TAGLN="tagline"; //A JSON array from the JSONObject objects

        static TextView firstNameBox;
        static TextView lastNameBox;
        static TextView tagLineBox;
        static TextView votesBox;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.user_det_activity);
            firstNameBox= (TextView) findViewById(R.id.firstname);
            lastNameBox= (TextView) findViewById(R.id.lastname);
            tagLineBox= (TextView) findViewById(R.id.tagline);
            votesBox= (TextView) findViewById(R.id.upvotes);
            new JSONParse().execute(url);
        }

        private class JSONParse extends AsyncTask<String, Void, String[]> {

            @Override
            protected String[] doInBackground(String... str) {
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
                        usersJsonObj = new JSONObject(data);
                    } catch (JSONException e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    try {
                        JSONArray objectsJson= usersJsonObj.getJSONArray(TAG_OBJECTS);
                        if (ViewEvent.idStr==7)   {  //God Dammit Aditi
                        try {
                            for(int a=0; a<objectsJson.length(); a++)   {
                            JSONObject singleJSON=objectsJson.getJSONObject(a);
                            if (singleJSON.getInt(TAG_ID)==ViewEvent.idStr) {
                            String[] userDetailsArr= {singleJSON.getString(TAG_FN), singleJSON.getString(TAG_LN),singleJSON.getString(TAG_TAGLN),singleJSON.getString(TAG_PTS)};
                            return userDetailsArr;
                            }
                            }
                        }
                        catch (JSONException e1)    {
                            e1.printStackTrace();
                        }
                        }
                        for (int n=0; n<objectsJson.length(); n++)  {
                            JSONObject singleJSON=objectsJson.getJSONObject(n);
                            try {
                                if (ViewEvent.idStr==singleJSON.getInt(TAG_ID))   {
                                    String[] userDetailsArr= {singleJSON.getString(TAG_FN), singleJSON.getString(TAG_LN),singleJSON.getString(TAG_TAGLN),singleJSON.getString(TAG_PTS)};
                                    return userDetailsArr;
                            }
                            } catch (JSONException e1) {
                                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                        }
                    }
                    catch (JSONException e1) {
                        e1.printStackTrace();
                    }
                }
            return null;
            }
            protected void onProgressUpdate() {

            }
            protected void onPostExecute(String[] result) {
                //String[] userDetailsArr= {singleJSON.getString(TAG_FN), singleJSON.getString(TAG_LN),singleJSON.getString(TAG_TAGLN),singleJSON.getString(TAG_PTS)};

                firstNameBox.setText(result[0]);
                lastNameBox.setText(result[1]);
                tagLineBox.setText(result[2]);
                votesBox.setText(result[3]+"Points");

            }
        }
}

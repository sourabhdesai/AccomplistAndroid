package com.example.Accomplist;
/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 3/1/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */

import android.util.Log;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;




import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class JSONParse {

    static InputStream is = null;
    static JSONObject jObj = null;
    static String jsonS = "";
    static String myJsonString = "";

    // constructor


    public JSONObject getJSONFromUrl(String url) {

        // Making HTTP request
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            jsonS = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(jsonS);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jObj;

    }
    // url to make request
    private static String url = "http://accomplist.herokuapp.com/api/v1/sharedevent/?format=json";

    // JSON Node names
    private static final String TAG_EVENTTITLE = "listitem";

    // contacts JSONArray
    JSONArray contacts = null;

    // Creating JSON Parser instance
    JSONParse jParser = new JSONParse();

    // getting JSON string from URL
    JSONObject json = jParser.getJSONFromUrl(url);
    public void run() {
        // Getting Array of Contacts
        try {
            contacts = json.getJSONArray(TAG_EVENTTITLE);
        } catch (JSONException e1) {
            e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // looping through All Contacts
        for(int i = 0; i < contacts.length(); i++){
            JSONObject c = null;
            try {
                c = contacts.getJSONObject(i);
            } catch (JSONException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            // Storing each json item in variable
            try {
                String id = c.getString(TAG_EVENTTITLE);
            } catch (JSONException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


     for (int a=0; a<contacts.length();a++) {
         myJsonString+=contacts.optJSONObject(a).toString();

     }

}
    public static String myStringOut() {
        return myJsonString;
    }
}

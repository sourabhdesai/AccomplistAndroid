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
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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
    public static String readJSONRequest() {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet("http://accomplist.herokuapp.com/api/v1/sharedevent/?format=json");
        try {
            HttpResponse response = client.execute(getRequest);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
            } else {
                Log.e(JSONParse.class.toString(), "Failed Bro");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(String.valueOf(builder), "JSON RESPONSE");
        return builder.toString();
    }


}

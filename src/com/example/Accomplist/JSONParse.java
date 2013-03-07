package com.example.Accomplist;
/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 3/1/13
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
 */
/*
import android.os.AsyncTask;


//package com.cw.json;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParse extends AsyncTask<String, String, JSONObject> {
    HttpClient client;
    JSONObject jsonObj= null;



    @Override
    protected JSONObject doInBackground(String... jsonurl) {
        StringBuilder url= new StringBuilder(String.valueOf(jsonurl));

        HttpGet get= new HttpGet(url.toString());
        HttpResponse r= null;
        try {
            r = client.execute(get);
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
                return jsonObj;

            }
            else{
                return null;
            }

        }
   /*
    protected void onProgressUpdate(String... progress) {
        progress="Loading...";
        return progress;
    }

    protected void onPostExecute(JSONObject result) {
        MainScreen.json=jsonObj;
    }

    }   */
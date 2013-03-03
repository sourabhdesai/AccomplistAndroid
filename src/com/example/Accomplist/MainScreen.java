package com.example.Accomplist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: DESAI_628IL
 * Date: 3/1/13
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class MainScreen extends Activity{
    TextView myTextView;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        }
    myTextView= (TextView) findViewById(R.id.textView1);
    String myJsonStr= JSONParse.myStringOut();


}

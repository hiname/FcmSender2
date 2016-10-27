package com.fcmsender2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncTask<String, String, String>(){
            @Override
            protected String doInBackground(String... params) {
                new FcmSender().main();
                return null;
            }
        }.execute();

    }
}

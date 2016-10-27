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
                String apiKey = "AIzaSyAN7qcnJgV5tlNx35-2J4xq-krVLIo2CQw";
                String token = "duOvNQ2bQzA:APA91bGNkagnaDMNwZnUx1T_FcI6sdnp42yAS4GDnWIrcRqzYe-0D92MszxDq5NDeUtJgTz5z45o6T6uEBuByRgZ4bBFEiJZVVlyilUp9dJeVeE86_NuQwFqEXzgR9PWsKfYr0Pv9L-s";
                FcmSender.sendMsg(apiKey, token, "sendTest");
                return null;
            }
        }.execute();

    }
}

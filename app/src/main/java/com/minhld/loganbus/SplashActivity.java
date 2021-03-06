package com.minhld.loganbus;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.minhld.supports.GTFSLoader;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new AsyncTask(){

            @Override
            protected Void doInBackground(Object[] params){
                try{
                    // load the GTFS data
                    GTFSLoader.loadGTFS(SplashActivity.this);

                    Thread.sleep(1000);
                    publishProgress(0);
                }catch(Exception e){
                    publishProgress(-1);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Object[] params){
                // go to main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }.execute();
    }

}

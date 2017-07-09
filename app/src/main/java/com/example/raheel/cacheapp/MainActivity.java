package com.example.raheel.cacheapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.raheel.cacheapp.util.CacheUtil;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "CacheApp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CacheUtil.scheduleJob(getApplicationContext(), true);
        Toast.makeText(this,"Cache App Started.", Toast.LENGTH_LONG).show();
        thread.start();
    }
    Thread thread = new Thread(){
        @Override
        public void run() {
            try {
                Thread.sleep(Toast.LENGTH_LONG); // As I am using LENGTH_LONG in Toast
                MainActivity.this.finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}

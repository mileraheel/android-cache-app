package com.example.raheel.cacheapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.raheel.cacheapp.MainActivity;
import com.example.raheel.cacheapp.service.CacheService;
import com.example.raheel.cacheapp.util.CacheUtil;

/**
 * Created by Raheel on 6/23/2017.
 */

public class CacheReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MainActivity.TAG, "Event Received.");
        CacheUtil.scheduleJob(context, true);
    }
}

package com.example.raheel.cacheapp.util;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

import com.example.raheel.cacheapp.MainActivity;
import com.example.raheel.cacheapp.service.CacheService;

/**
 * Created by Raheel on 6/23/2017.
 */

public class CacheUtil {

    public static void scheduleJob(Context context, boolean runNow) {

        ComponentName serviceComponent = new ComponentName(context, CacheService.class);
        JobInfo.Builder builder = new JobInfo.Builder(0, serviceComponent);
        if (runNow) {
            Log.i(MainActivity.TAG, "Running Job now.");
            builder.setMinimumLatency(3 * 1000);
            builder.setOverrideDeadline(3 * 1000);
        } else {
            Log.i(MainActivity.TAG, "Scheduling Job to run after an hour.");
            builder.setMinimumLatency(60 * 60 * 1000);
            builder.setOverrideDeadline(60 * 60 * 1000);
        }
        builder.setRequiresCharging(false);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);

        JobScheduler jobScheduler = context.getSystemService(JobScheduler.class);
        int result = jobScheduler.schedule(builder.build());
        Log.i(MainActivity.TAG, result == 1 ? "Job scheduled successfuly" : "Job scheduling failed.");
    }
}

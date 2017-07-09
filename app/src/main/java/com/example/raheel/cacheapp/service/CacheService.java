package com.example.raheel.cacheapp.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

import com.example.raheel.cacheapp.MainActivity;
import com.example.raheel.cacheapp.util.CacheUtil;

/**
 * Created by Raheel on 6/23/2017.
 */

public class CacheService extends JobService {
    @Override
    public boolean onStartJob(final JobParameters params) {
        Log.i(MainActivity.TAG, "onStartJob - Inside Cache Service");
        CacheDataTask asyncTask = new CacheDataTask(getBaseContext()){
            @Override
            protected void onPostExecute(Boolean success) {
                Log.i(MainActivity.TAG, "Finished Async task - reading json and putting in db");
                jobFinished(params, false);
                CacheUtil.scheduleJob(getBaseContext(), false);
                Log.i(MainActivity.TAG, "Finished Async task - Job completed successfuly.");
            }
        };
        asyncTask.execute();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(MainActivity.TAG, "Job not finished succesfuly");
        return false;
    }
}

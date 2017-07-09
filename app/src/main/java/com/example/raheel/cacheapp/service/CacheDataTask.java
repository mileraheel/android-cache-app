package com.example.raheel.cacheapp.service;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.raheel.cacheapp.MainActivity;
import com.example.raheel.cacheapp.R;
import com.example.raheel.cacheapp.data.Menu;
import com.example.raheel.cacheapp.data.MenuListProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Raheel on 6/27/2017.
 */

public class CacheDataTask extends AsyncTask<Void, Void, Boolean> {
    private Context context;

    public CacheDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        InputStream is = context.getResources().openRawResource(R.raw.z_output);
        Log.i(MainActivity.TAG, "loading from json file");
        ObjectMapper mapper = new ObjectMapper();
        List<Menu> menus;
        try {
            menus = mapper.readValue(is, TypeFactory.defaultInstance().constructCollectionType(
                    List.class, Menu.class));
            Log.i(MainActivity.TAG, "menus loaded from file : " + menus.size());
            for (Menu menu : menus) {
                Log.i(MainActivity.TAG, "record to be inserted : " + menu.getContentValues());
                Uri uri = context.getContentResolver().insert(MenuListProvider.CONTENT_URI, menu.getContentValues());
                Log.i(MainActivity.TAG, "record inserted :" + uri.toString());
            }
        } catch (IOException e) {
            Log.i(MainActivity.TAG, "error in converting from json file to Menu List");
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

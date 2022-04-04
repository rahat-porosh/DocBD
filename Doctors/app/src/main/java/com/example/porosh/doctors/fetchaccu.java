package com.example.porosh.doctors;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class fetchaccu extends AsyncTask<Void, Void, String> {
    ListView listView;
    Context context;

    public fetchaccu(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder stringBuilder = new StringBuilder();
        //StringBuilder stringBuffer = new StringBuilder();
        try {
            JSONObject jsonObject=new JSONObject(download.data);
            JSONArray jsonArray=jsonObject.getJSONArray("Accupressure");
            accupressure.strings=new String[jsonArray.length()];
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String name=jsonObject1.getString("Doctor's Name");
                accupressure.strings[i]=name;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
            CustomAdapter customAdapter = new CustomAdapter(context,accupressure.strings);
            listView.setAdapter(customAdapter);
    }
}

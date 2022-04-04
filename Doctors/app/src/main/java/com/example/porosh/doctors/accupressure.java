package com.example.porosh.doctors;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class accupressure extends AppCompatActivity {
    private ListView listView;
    static String[] strings;
    Intent intent;
    String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accupressure);
        intent=getIntent();
        s=intent.getStringExtra("spe");
        listView = findViewById(R.id.listaccu);
        fetchaccu f = new fetchaccu(listView,getApplicationContext());
        f.execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=accupressure.strings[position];
                Intent intent=new Intent(accupressure.this,fetch.class);
                Bundle bundle=new Bundle();
                bundle.putString("val",value);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

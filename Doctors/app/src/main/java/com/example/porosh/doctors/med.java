package com.example.porosh.doctors;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class med extends AppCompatActivity {
    private ListView listView;
    static String[] strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med);
        listView=findViewById(R.id.listmed);
        getmed g=new getmed(listView,getApplicationContext());
        g.execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=med.strings[position];
                Intent intent=new Intent(med.this,fetchmed.class);
                intent.putExtra("valmed",value);
                startActivity(intent);
            }
        });
    }
}
class getmed extends AsyncTask<Void, Void, String> {
    ListView listView;
    Context context;

    public getmed(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder stringBuilder = new StringBuilder();
        //StringBuilder stringBuffer = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(download.data);
            JSONArray jsonArray = jsonObject.getJSONArray("Medicine");
            med.strings = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String name = jsonObject1.getString("Doctor's Name");
                med.strings[i] = name;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        CustomAdapter customAdapter = new CustomAdapter(context, med.strings);
        listView.setAdapter(customAdapter);
    }
}
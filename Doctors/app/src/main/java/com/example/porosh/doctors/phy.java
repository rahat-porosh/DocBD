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

public class phy extends AppCompatActivity {
    private ListView listView;
    static String[] strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phy);
        listView=findViewById(R.id.listphy);
        getphy g=new getphy(listView,getApplicationContext());
        g.execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=phy.strings[position];
                Intent intent=new Intent(phy.this,fetchphy.class);
                intent.putExtra("valphy",value);
                startActivity(intent);
            }
        });
    }
}
class getphy extends AsyncTask<Void, Void, String> {
    ListView listView;
    Context context;

    public getphy(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder stringBuilder = new StringBuilder();
        //StringBuilder stringBuffer = new StringBuilder();
        try {
            JSONObject jsonObject=new JSONObject(download.data);
            JSONArray jsonArray=jsonObject.getJSONArray("Physiotherapist");
            phy.strings=new String[jsonArray.length()];
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String name=jsonObject1.getString("Doctor's Name");
                phy.strings[i]=name;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        CustomAdapter customAdapter = new CustomAdapter(context,phy.strings);
        listView.setAdapter(customAdapter);
    }
}
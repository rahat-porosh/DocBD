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

public class medisono extends AppCompatActivity {
    private ListView listView;
    static String[] strings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medisono);
        listView=findViewById(R.id.listsono);
        getsono g= new getsono(listView,getApplicationContext());
        g.execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=medisono.strings[position];
                Intent intent=new Intent(medisono.this,fecthsono.class);
                intent.putExtra("valsono",value);
                startActivity(intent);
            }
        });
    }
}
class getsono extends AsyncTask<Void, Void, String> {
    ListView listView;
    Context context;

    public getsono(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        //StringBuilder stringBuilder = new StringBuilder();
        //StringBuilder stringBuffer = new StringBuilder();
        try {
            JSONObject jsonObject=new JSONObject(download.data);
            JSONArray jsonArray=jsonObject.getJSONArray("Medical Sonography");
            medisono.strings=new String[jsonArray.length()];
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String name=jsonObject1.getString("Doctor's Name");
                medisono.strings[i]=name;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        CustomAdapter customAdapter = new CustomAdapter(context,medisono.strings);
        listView.setAdapter(customAdapter);
    }
}
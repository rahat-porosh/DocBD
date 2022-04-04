package com.example.porosh.doctors;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

public class fetch extends AppCompatActivity {
    Intent intent;
    String st;
    static String st1;
    Bundle bundle;
    FloatingActionButton fab;
    public static TextView textView;
    //ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        intent=getIntent();
        Bundle bundle=intent.getExtras();
        st=bundle.getString("val");
        //st1=bundle.getString("phn");
        textView=findViewById(R.id.docdet);
        fab=findViewById(R.id.fab);
        fetchdoc f=new fetchdoc(st);
        f.execute();
        fab.setOnClickListener(new View.OnClickListener() {
            String s;
            @Override
            public void onClick(View view) {
                if (st1.equals("")){
                    Toast.makeText(fetch.this,"No phone number",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    s="tel:"+st1;
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });

    }
}
class fetchdoc extends AsyncTask<Void,Void,Void> {
    private String string1;

    fetchdoc(String s) {

        string1 = s;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuffer = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(download.data);
            JSONArray jsonArray = jsonObject.getJSONArray("Accupressure");
            // accupressure.strings = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                String name = jsonObject1.getString("Doctor's Name");
                if (name.equals(string1))
                {
                    String deg = jsonObject1.optString("Professional Degree");
                    String des = jsonObject1.optString("Designation");
                    String hosname = jsonObject1.optString("Hospital Name");
                    String chamname = jsonObject1.optString("Chamber Name");
                    String vis = jsonObject1.optString("Visiting Hour");
                    String loc = jsonObject1.optString("Chamber Location");
                    String phn = jsonObject1.optString("Phone Number");
                    String ema = jsonObject1.optString("Email Address");
                    stringBuffer.append("Doctor's name:").append(name).append("\n").append("Professional Degree:" ).append(deg).append("\n")
                            .append("Designation: ").append(des).append("\n").append("Hospital Name: ").append(hosname).append("\n")
                            .append("Chamber Name: ").append(chamname).append("\n").append("Visiting Hour: ").append(vis).append("\n")
                            .append("Chamber Location: ").append(loc).append("\n").append("Phone Number: ").append(phn).append("\n")
                            .append("Email Address: ").append(ema).append("\n \n");
                    string1 = stringBuffer.toString();
                    fetch.st1=phn;
                    break;
                }
                else continue;
            }
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        fetch.textView.setText(string1);
    }
}
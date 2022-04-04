package com.example.porosh.doctors;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class fetchpsyc extends AppCompatActivity {
    Intent intent;
    String st;
    static String st1;
    FloatingActionButton fab;
    public static TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchpsyc);
        intent=getIntent();
        st=getIntent().getStringExtra("valpsyc");
        textView=findViewById(R.id.psycdet);
        psycdoc a=new psycdoc(st);
        a.execute();
        fab = findViewById(R.id.fabpsyc);
        fab.setOnClickListener(new View.OnClickListener() {
            String s;

            @Override
            public void onClick(View view) {
                if (st1.equals("")) {
                    Toast.makeText(fetchpsyc.this, "No phone number", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    s = "tel:" + st1;
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });
    }
}
class psycdoc extends AsyncTask<Void,Void,Void> {
    private String string1;

    psycdoc(String s) {
        string1 = s;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuffer = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(download.data);
            JSONArray jsonArray = jsonObject.getJSONArray("Psychiatric and Drug Addiction");
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
                    fetchpsyc.st1=phn;
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
        fetchpsyc.textView.setText(string1);
    }
}
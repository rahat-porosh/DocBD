package com.example.porosh.doctors;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.porosh.doctors.R;
import com.example.porosh.doctors.download;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class fetchalle extends AppCompatActivity {
    Intent intent;
    String st;
    static String st1;
    public static TextView textView;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetchalle);
        intent=getIntent();
        st=getIntent().getStringExtra("valalle");
        textView=findViewById(R.id.alledet);
        alledoc a=new alledoc(st);
        a.execute();
        fab = findViewById(R.id.faballe);
        fab.setOnClickListener(new View.OnClickListener() {
            String s;

            @Override
            public void onClick(View view) {
                if (st1.equals("")) {
                    Toast.makeText(fetchalle.this, "No phone number", Toast.LENGTH_SHORT).show();
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
class alledoc extends AsyncTask<Void,Void,Void> {
    private String string1;

    alledoc(String s) {
        string1 = s;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        //StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuffer = new StringBuilder();
        try {
            JSONObject jsonObject = new JSONObject(download.data);
            JSONArray jsonArray = jsonObject.getJSONArray("Allergy specialist");
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
                    fetchalle.st1=phn;
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
        fetchalle.textView.setText(string1);
    }
}
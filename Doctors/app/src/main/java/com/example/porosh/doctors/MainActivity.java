package com.example.porosh.doctors;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            listView = findViewById(R.id.list);
            fab=findViewById(R.id.fabref);
            final String[] specialist = getResources().getStringArray(R.array.specialty);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.listview_view, R.id.listtext, specialist);
            listView.setAdapter(arrayAdapter);
        if (isOnline()) {
            download d = new download();
            d.execute();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String value = specialist[position];
                    if (value.equals("Accupressure")) {
                        Intent intent = new Intent(MainActivity.this, accupressure.class);
                        //intent.putExtra("spe",value);
                        startActivity(intent);
                    } else if (value.equals("Anaesthesiologist")) {
                        Intent intent = new Intent(MainActivity.this, anaesthesiologist.class);
                        startActivity(intent);
                    } else if (value.equals("Allergy specialist")) {
                        Intent intent = new Intent(MainActivity.this, allergy.class);
                        startActivity(intent);
                    } else if (value.equals("Cardiologist")) {
                        Intent intent = new Intent(MainActivity.this, cardio.class);
                        startActivity(intent);
                    } else if (value.equals("Cosmetologist and Trichologist")) {
                        Intent intent = new Intent(MainActivity.this, cosmetology.class);
                        startActivity(intent);
                    } else if (value.equals("Dental Surgeon")) {
                        Intent intent = new Intent(MainActivity.this, dental.class);
                        startActivity(intent);
                    } else if (value.equals("Dermatology and Venereology")) {
                        Intent intent = new Intent(MainActivity.this, derma.class);
                        startActivity(intent);
                    } else if (value.equals("Diabetologist")) {
                        Intent intent = new Intent(MainActivity.this, dia.class);
                        startActivity(intent);
                    } else if (value.equals("Dietician and Nutritionist")) {
                        Intent intent = new Intent(MainActivity.this, diet.class);
                        startActivity(intent);
                    } else if (value.equals("ENT Surgeon(Ear,Nose,Throat)")) {
                        Intent intent = new Intent(MainActivity.this, ent.class);
                        startActivity(intent);
                    } else if (value.equals("Gastro Phy/Hepatologist")) {
                        Intent intent = new Intent(MainActivity.this, gastro.class);
                        startActivity(intent);
                    } else if (value.equals("Hematologist")) {
                        Intent intent = new Intent(MainActivity.this, hematologist.class);
                        startActivity(intent);
                    } else if (value.equals("Homeopathy Doctor")) {
                        Intent intent = new Intent(MainActivity.this, homeopathy.class);
                        startActivity(intent);
                    } else if (value.equals("Maternity and Child Specialist")) {
                        Intent intent = new Intent(MainActivity.this, maternity.class);
                        startActivity(intent);
                    } else if (value.equals("Medical Sonography")) {
                        Intent intent = new Intent(MainActivity.this, medisono.class);
                        startActivity(intent);
                    } else if (value.equals("Nephrologist(Kidney Specialist)")) {
                        Intent intent = new Intent(MainActivity.this, musculo.class);
                        startActivity(intent);
                    } else if (value.equals("Neuro Medicine")) {
                        Intent intent = new Intent(MainActivity.this, neuromed.class);
                        startActivity(intent);
                    } else if (value.equals("Neuro Surgeon")) {
                        Intent intent = new Intent(MainActivity.this, neurosur.class);
                        startActivity(intent);
                    } else if (value.equals("Oncologist(Cancer)-Medical/Surgeon")) {
                        Intent intent = new Intent(MainActivity.this, oncologist.class);
                        startActivity(intent);
                    } else if (value.equals("Orthopaedic")) {
                        Intent intent = new Intent(MainActivity.this, ortho.class);
                        startActivity(intent);
                    } else if (value.equals("Physiotherapist")) {
                        Intent intent = new Intent(MainActivity.this, phy.class);
                        startActivity(intent);
                    } else if (value.equals("Plastic and Cosmetic Surgeon")) {
                        Intent intent = new Intent(MainActivity.this, plas.class);
                        startActivity(intent);
                    } else if (value.equals("Urologist")) {
                        Intent intent = new Intent(MainActivity.this, uro.class);
                        startActivity(intent);
                    } else if (value.equals("Vascular Surgeon")) {
                        Intent intent = new Intent(MainActivity.this, vas.class);
                        startActivity(intent);
                    } else if (value.equals("Ophthalmologist(Eye Specialist)")) {
                        Intent intent = new Intent(MainActivity.this, oph.class);
                        startActivity(intent);
                    } else if (value.equals("Medicine)")) {
                        Intent intent = new Intent(MainActivity.this, med.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, psyc.class);
                        startActivity(intent);
                    }
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"Please turn on internet and press refresh",Toast.LENGTH_SHORT).show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    restartActivity();
                }
            });
        }
    }
    private boolean isOnline(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        else return false;
    }
    public void restartActivity()
    {
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

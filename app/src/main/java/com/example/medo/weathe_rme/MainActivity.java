package com.example.medo.weathe_rme;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
       Typeface type;
       ArrayList<Location> dat;
       Adapter2 adapt;
       Spinner cityspinner;
    TextView t1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_first);
            AdView mAdView = (AdView) findViewById(R.id.adView);

            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            type = Typeface.createFromAsset(getAssets(), "fonts/font2.TTF");
            initViews();
            AddData();
            t1.setTypeface(type);
            adapt = new Adapter2(MainActivity.this, dat);
            cityspinner.setAdapter(adapt);
   cityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           Location loc = dat.get(i);
           // TODO Auto-generated method stub
           String c = loc.cityname;
           if (!c.equals("Nothing selected"))
           {
               Intent intent = new Intent(MainActivity.this, DataActivity.class);
               intent.putExtra("city", c);
               startActivity(intent);
           }
       }

       @Override
       public void onNothingSelected(AdapterView<?> adapterView) {

       }
   });
        }


        public void initViews () {
            cityspinner = (Spinner) findViewById(R.id.l1);
            t1 = (TextView) findViewById(R.id.t1);
        }
    public void AddData () {

        dat = new ArrayList<Location>();
        dat.add(new Location("Nothing selected" , R.mipmap.ic_launcher));
        dat.add(new Location("Cairo", R.drawable.egypt));
        dat.add(new Location("London", R.drawable.england));
        dat.add(new Location("Riyadh", R.drawable.saudi));
        dat.add(new Location("Moscow", R.drawable.rus));
        dat.add(new Location("New York", R.drawable.usa));
        dat.add(new Location("Madrid", R.drawable.spain));
        dat.add(new Location("Berlin", R.drawable.germany));
        dat.add(new Location("Bucharest", R.drawable.rom));
        dat.add(new Location("Pretoria", R.drawable.south));


        }

    }

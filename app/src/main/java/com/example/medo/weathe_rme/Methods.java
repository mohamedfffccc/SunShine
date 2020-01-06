package com.example.medo.weathe_rme;

import android.location.*;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Methods extends AppCompatActivity {
    String city = "";
    String l_name="";
    double lat,lang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_methods);
        LocationManager lm = (LocationManager)getSystemService(Methods.this.LOCATION_SERVICE);


        new GetCoun().execute("http://maps.googleapis.com/maps/api/geocode/json?latlng=30.6,31.25&sensor=true");
    }
    class GetCoun extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... arg0) {
            try {
                URL url = new URL(arg0[0]);
                URLConnection conn = url.openConnection();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String text = "";
                while ((text = br.readLine()) != null) {
                    city += text;
                    Log.d("name", city);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return city;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            try {
		JSONObject j1 = new JSONObject(result);
 		JSONArray e = j1.getJSONArray("results");
		String tf = e.getString(0);
		JSONObject oo2 = new JSONObject(tf);
		JSONArray s  = oo2.getJSONArray("address_components");
		String n = s.getString(2);
		JSONObject ooooo = new JSONObject(n);
l_name = ooooo.getString("long_name");
                Toast.makeText(Methods.this, l_name+"", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
            }
            super.onPostExecute(result);
        }

    }
}

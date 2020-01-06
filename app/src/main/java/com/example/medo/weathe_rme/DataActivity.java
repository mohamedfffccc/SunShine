package com.example.medo.weathe_rme;

import java.io.BufferedReader;
import java.io.InputStreamReader; 
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {
	String cityname;
	ListView weatherlist;
	ArrayList<Details> data;  
	String m;
	Adapter adapter; 
	String info = "";
	String date, d, temp;
	int number; 
	RelativeLayout rel2;
	SlidingDrawer drawer;
	ListView list2;
	ArrayAdapter<String> adapter2;   
	ArrayList<String> listdata;
	TextView txcit, txtem, txcon, txdate,txmin;
	ImageView iv1;
	ImageView imagev;
	Typeface type;
	String hum, coun, clouds, clod, win, pres, vis, winds, mint, maxt;   
	ProgressDialog d2;
 
	@Override    
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cityname = getIntent().getExtras().getString("city");
		d2 = new ProgressDialog(DataActivity.this);
		d2.setTitle("please wait");
		d2.setCancelable(true);
		d2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		d2.setIcon(R.mipmap.ic_launcher);
		weatherlist = (ListView) findViewById(R.id.forecast_list);
		data = new ArrayList<Details>();
		weatherlist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				listdata.clear();
				drawer.open();
				number = arg2;
//				new GetDetails()
//						.execute("http://api.openweathermap.org/data/2.5/forecast/weekly?q="+
//                         cityname+"&mode=json&units=metric&appid=293ce27472ad0f2a943c114bac5e18a5");
//


				// TODO Auto-generated method stub

			}
		});

		drawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		txmin = (TextView) findViewById(R.id.txmin);
		list2 = (ListView) findViewById(R.id.data);
	type = Typeface.createFromAsset(getAssets(), "fonts/font2.TTF");
		txcit = (TextView) findViewById(R.id.txcity);
		txcit.setText(cityname);
		txcon = (TextView) findViewById(R.id.txcoun);
		txtem = (TextView) findViewById(R.id.txtemp);
		txdate = (TextView) findViewById(R.id.date);
		txdate.setTypeface(type);
        txmin.setTypeface(type);
		txcit.setTypeface(type);
		txdate.setText(date);
		txcon.setTypeface(type);
		txtem.setTypeface(type);
		 
		rel2 = (RelativeLayout) findViewById(R.id.rel1);
		rel2.setVisibility(View.INVISIBLE);

		listdata = new ArrayList<String>();

		iv1 = (ImageView) findViewById(R.id.imageView2);
		imagev = (ImageView) findViewById(R.id.imageView1);
		//
		adapter2 = new ArrayAdapter<String>(DataActivity.this, R.layout.item,
				listdata);

	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.REFRESH:
			d2.show();
			data.clear();
			new GetWet()
					.execute
							("http://api.openweathermap.org/data/2.5/forecast/weekly?q="+ cityname +"&mode=json&units=metric&appid=293ce27472ad0f2a943c114bac5e18a5");


        txcit.setText(info);

			break;
		case R.id.action_settings:
			Intent i = new Intent(DataActivity.this,MainActivity.class);
			startActivity(i);  
			break;
		}
		return super.onOptionsItemSelected(item);  
	}
	//TODO

	class GetWet extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {

			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			try {
				URL url = new URL(arg0[0]);
				URLConnection conn = url.openConnection();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));
				String text = "";
				while ((text = br.readLine()) != null) {
					info += text;
					Log.d("WEATHER", info);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return info;
		}

		@Override
		protected void onPostExecute(String result) { 
			// TODO Auto-generated method stub
			try {
				JSONObject mainjson = new JSONObject(result);
				JSONArray mainarray = mainjson.getJSONArray("weatherlist");
				for (int i = 0; i < mainarray.length(); i++) {
					String maininfo = mainarray.getString(i);
					JSONObject mainjson2 = new JSONObject(maininfo);
					String main = mainjson2.getString("main");

					JSONObject mainjson3 = new JSONObject(main);
					temp = mainjson3.getString("temp");
					Log.d("TEMP", temp);

					
					date = mainjson2.getString("dt_txt");

					data.add(new Details(date + " - " + temp + "°C"));

				}
				adapter = new Adapter(DataActivity.this, data);
				weatherlist.setAdapter(adapter);
				d2.dismiss();

			} catch (Exception e) {
				e.printStackTrace();
			}
			super.onPostExecute(result);
		}

	}


	// ////////////////////////////////////////////////////////////////////
	class GetDetails extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {

			return info;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			try {
				JSONObject mainjson = new JSONObject(result);
				String city = mainjson.getString("city");
				JSONObject o3 = new JSONObject(city);
				coun = o3.getString("country");
				txcon.setText(","+coun);
				String coor = o3.getString("coord");
				JSONObject o4 = new JSONObject(coor);
				String lat = o4.getString("lat");
				String lon = o4.getString("lon");

				JSONArray mainarray = mainjson.getJSONArray("weatherlist");
				String maininfo = mainarray.getString(number);
				JSONObject mainjson2 = new JSONObject(maininfo);
				clouds = mainjson2.getString("clouds");
				JSONObject o5 = new JSONObject(clouds);
				clod = o5.getString("all");
				win = mainjson2.getString("wind");
				JSONObject o6 = new JSONObject(win);
				winds = o6.getString("speed");
				String main = mainjson2.getString("main");

				JSONObject mainjson3 = new JSONObject(main);
				temp = mainjson3.getString("temp");
				hum = mainjson3.getString("humidity");
				pres = mainjson3.getString("pressure");
				mint = mainjson3.getString("temp_min");
				maxt = mainjson3.getString("temp_max");
				JSONArray array = mainjson2.getJSONArray("weather");
				String weather = array.getString(0);
				JSONObject o7 = new JSONObject(weather);
				String d = o7.getString("description");
                m = o7.getString("main");
				//
				listdata.add("latitude : " + lat);
				listdata.add("longtude : " + lon);
				listdata.add("description : " + d);
				listdata.add("clouds : " + clod);
				listdata.add("Max : " + maxt + "°C");
				listdata.add("Min : " + mint + "°C");
				listdata.add("humidity : " + hum + " %");  	
				listdata.add("pressure : " + pres + " hpa");
				listdata.add("wind speed : " + winds + " km/h NW");

				date = mainjson2.getString("dt_txt");
				txtem.setText(temp + "°C");
				txdate.setText(date);
				txmin.setText(m);
				
				rel2.setVisibility(View.VISIBLE);
				list2.setAdapter(adapter2);
				if (clod.equals("0")) {
					imagev.setVisibility(View.VISIBLE); 
					iv1.setVisibility(View.INVISIBLE);
					Animation anim = AnimationUtils.loadAnimation(
							getApplicationContext(), R.anim.tw1);   
					imagev.startAnimation(anim);
				} else {
					iv1.setVisibility(View.VISIBLE);
					imagev.setVisibility(View.INVISIBLE);
					Animation anim2 = AnimationUtils.loadAnimation(
							getApplicationContext(), R.anim.tw2); 
					iv1.startAnimation(anim2);

				}
				

			} catch (Exception e) {

			}
			super.onPostExecute(result);
		}

	}

}

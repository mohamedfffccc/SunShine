package com.example.medo.weathe_rme;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter2 extends BaseAdapter {
	Typeface type;
	ArrayList<Location> data;
	Context con;
	public Adapter2 (Context con,ArrayList<Location> data)
	{
		this.con=con;
		this.data=data;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LayoutInflater inflater = (LayoutInflater) con.getSystemService(con.LAYOUT_INFLATER_SERVICE);
		type = Typeface.createFromAsset(con.getAssets(), "fonts/font2.TTF");
		View view = inflater.inflate(R.layout.cityitem, null);
		TextView tv = (TextView) view.findViewById(R.id.textcity);
		ImageView im  = (ImageView) view.findViewById(R.id.cityflag);
		Location l = data.get(arg0);
		tv.setText(l.cityname);
		im.setImageResource(l.flag);
		tv.setTypeface(type);
		// TODO Auto-generated method stub
		return view;
	}

}

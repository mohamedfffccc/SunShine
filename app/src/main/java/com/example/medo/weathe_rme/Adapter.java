package com.example.medo.weathe_rme;

import java.util.ArrayList;

import org.w3c.dom.Text;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
	ArrayList<Details> data;
	Context con; 
	public Adapter (Context con,ArrayList<Details> data)
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
		View v = inflater.inflate(R.layout.list_item, null);
		TextView tv = (TextView) v.findViewById(R.id.textView1);
		// TODO Auto-generated method stub
		Details d = data.get(arg0);
		tv.setText(d.details);
		return v;
	}

}

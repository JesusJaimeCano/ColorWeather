package com.example.jesus.colorweather.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.example.jesus.colorweather.Day;

import java.util.ArrayList;

/**
 * Created by Admin on 04/12/2017.
 */

public class HourlyWeatherAdapter extends BaseAdapter{

    public static final String TAG = HourlyWeatherAdapter.class.getSimpleName();

    ArrayList<Day> days;
    Context context;


    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        

        return null;
    }
}

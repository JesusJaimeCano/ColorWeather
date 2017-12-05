package com.example.jesus.colorweather.Adapters;

import android.content.Context;
<<<<<<< HEAD
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.jesus.colorweather.Hour;
import com.example.jesus.colorweather.R;
=======
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.example.jesus.colorweather.Day;
>>>>>>> 9a1067e6b000c2a2a269d476a0a52866d787d2ca

import java.util.ArrayList;

/**
 * Created by Admin on 04/12/2017.
 */

public class HourlyWeatherAdapter extends BaseAdapter{

    public static final String TAG = HourlyWeatherAdapter.class.getSimpleName();

<<<<<<< HEAD
    ArrayList<Hour> hours;
    Context context;

    public HourlyWeatherAdapter(Context context, ArrayList<Hour> hours){

        this.context = context;
        this.hours = hours;

    }


    @Override
    public int getCount() {
        return hours.size();
=======
    ArrayList<Day> days;
    Context context;


    @Override
    public int getCount() {
        return days.size();
>>>>>>> 9a1067e6b000c2a2a269d476a0a52866d787d2ca
    }

    @Override
    public Object getItem(int position) {
<<<<<<< HEAD
        return hours.get(position);
=======
        return days.get(position);
>>>>>>> 9a1067e6b000c2a2a269d476a0a52866d787d2ca
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
<<<<<<< HEAD
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        Hour hour =  hours.get(position);

        if (view == null){

            Log.d(TAG,"Creando vistas y buscando vistas");
            view = LayoutInflater.from(context).inflate(R.layout.hourly_list_item, viewGroup,false);

            viewHolder = new ViewHolder();

            viewHolder.Title = view.findViewById(R.id.hourlyTitleTextView);
            viewHolder.weatherDescription = view.findViewById(R.id.hourlyDescriptionTextView);

            view.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.Title.setText(hour.getTitle());
        viewHolder.weatherDescription.setText(hour.getWeatherDescription());


        return view;
    }

    static class ViewHolder{

        TextView Title;
        TextView weatherDescription;
=======
    public View getView(int i, View view, ViewGroup viewGroup) {

        

        return null;
>>>>>>> 9a1067e6b000c2a2a269d476a0a52866d787d2ca
    }
}

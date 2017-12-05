package com.example.jesus.colorweather.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.jesus.colorweather.Hour;
import com.example.jesus.colorweather.R;

import java.util.ArrayList;

/**
 * Created by Admin on 04/12/2017.
 */

public class HourlyWeatherAdapter extends BaseAdapter{

    public static final String TAG = HourlyWeatherAdapter.class.getSimpleName();

    ArrayList<Hour> hours;
    Context context;

    public HourlyWeatherAdapter(Context context, ArrayList<Hour> hours){

        this.context = context;
        this.hours = hours;

    }


    @Override
    public int getCount() {
        return hours.size();
    }

    @Override
    public Object getItem(int position) {
        return hours.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
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
    }
}

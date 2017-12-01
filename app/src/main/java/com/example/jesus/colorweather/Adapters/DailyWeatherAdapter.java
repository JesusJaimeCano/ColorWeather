package com.example.jesus.colorweather.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jesus.colorweather.Day;
import com.example.jesus.colorweather.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jesus on 01/12/2017.
 */

public class DailyWeatherAdapter extends BaseAdapter {

    ArrayList<Day> days;
    Context context;

    public DailyWeatherAdapter(Context context, ArrayList<Day> days){

        this.context = context;
        this.days = days;

    }

    @Override
    public int getCount() {
        return days.size();//Este es el que le va a decir al adapter cuantos elementos hay en los datos, 7 dias d ela semana, 24 horas etc


    }

    @Override
    public Object getItem(int position) {


        return days.get(position);//Item que vamos a renderizar
    }

    @Override
    public long getItemId(int position) {
        return 0;//No se utilizara
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.daily_list_item,null);

        TextView dayTitle = view.findViewById(R.id.dailyListTitle);
        TextView dayDescription =  view.findViewById(R.id.dailyListDescription);
        TextView rainProbability = view.findViewById(R.id.dailyListProbability);

        Day day = days.get(position);

        dayTitle.setText(day.getDayname());
        dayDescription.setText(day.getWeatherDescription());
        rainProbability.setText(day.getRainProbability());

        return view;//Como vamos a renderizar y a mapear los datos
    }
}

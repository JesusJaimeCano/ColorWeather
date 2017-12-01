package com.example.jesus.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.jesus.colorweather.Adapters.DailyWeatherAdapter;

import java.util.ArrayList;

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        ArrayList<Day> days = new ArrayList<Day>();

        Day day = new Day();

        day.setDayname("Mondey");
        day.setWeatherDescription("Si funciona perro");
        day.setRainProbability("Rain Probality: 30%");

        for (int i = 0; i < 500; i++){
            days.add(day);
        }

        DailyWeatherAdapter dailyWeatherAdapter = new DailyWeatherAdapter(this,days);

        setListAdapter(dailyWeatherAdapter);
    }
}

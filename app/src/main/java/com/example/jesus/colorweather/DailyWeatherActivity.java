package com.example.jesus.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.jesus.colorweather.Adapters.DailyWeatherAdapter;

import java.util.ArrayList;

public class DailyWeatherActivity extends ListActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        ArrayList<Day> days;


        days = getIntent().getParcelableArrayListExtra("days");

        DailyWeatherAdapter dailyWeatherAdapter = new DailyWeatherAdapter(this,days);

        setListAdapter(dailyWeatherAdapter);
    }
}

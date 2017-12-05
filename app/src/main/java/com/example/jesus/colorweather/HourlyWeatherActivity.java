package com.example.jesus.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jesus.colorweather.Adapters.HourlyWeatherAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

    public class HourlyWeatherActivity extends Activity {

        @BindView(R.id.hourlyTitleTextView)
        ListView hourlyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_weather);

        ButterKnife.bind(this);


        Hour hour = new Hour();
        hour.setTitle("11:00pm");
        hour.setWeatherDescription("There is a storm");

        ArrayList<Hour> hours = new ArrayList<Hour>();
        hours.add(hour);

        HourlyWeatherAdapter hourlyWeatherAdapter = new HourlyWeatherAdapter(this,hours);

        hourlyListView.setAdapter(hourlyWeatherAdapter);

    }
}

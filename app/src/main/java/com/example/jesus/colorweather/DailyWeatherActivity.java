package com.example.jesus.colorweather;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class DailyWeatherActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather);

        ArrayList<String> dayArray =  new ArrayList<String>();
        dayArray.add("Lunes");
        dayArray.add("Martes");
        dayArray.add("Miercoles");
        dayArray.add("Jueves");
        dayArray.add("Viernes");
        dayArray.add("Sabado");
        dayArray.add("Domingo");
        dayArray.add("Jueves");
        dayArray.add("Viernes");
        dayArray.add("Sabado");
        dayArray.add("Domingo");
        dayArray.add("Lunes");
        dayArray.add("Martes");
        dayArray.add("Miercoles");
        dayArray.add("Jueves");
        dayArray.add("Viernes");
        dayArray.add("Sabado");
        dayArray.add("Domingo");
        dayArray.add("Jueves");
        dayArray.add("Viernes");
        dayArray.add("Sabado");
        dayArray.add("Domingo");

        ArrayAdapter<String> daysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,dayArray);

        setListAdapter(daysAdapter);
    }
}

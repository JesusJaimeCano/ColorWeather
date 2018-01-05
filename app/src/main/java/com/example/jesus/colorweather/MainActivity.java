package com.example.jesus.colorweather;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {


    @BindView(R.id.iconImageView)
    ImageView iconImageView;
    @BindView(R.id.descriptionTextView)
    TextView descriptionTextView;
    @BindView(R.id.currentTempTextView)
    TextView currentTempTextView;
    @BindView(R.id.highestTempTextView)
    TextView highestTempTextView;
    @BindView(R.id.lowestTempTextView)
    TextView lowestTempTextView;

    @BindDrawable(R.drawable.clear_night)
    Drawable clearnight;

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        /*CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);

        currentWeather.setIconImage("sunny");
        currentWeather.setDescription("SunnyDay");
        currentWeather.setCurrentTemperature("24");
        currentWeather.setHighestTemperature("H: 25");
        currentWeather.setLowestTemperature("L: 10");

        iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());
        descriptionTextView.setText(currentWeather.getDescription());
        currentTempTextView.setText(currentWeather.getCurrentTemperature());
        highestTempTextView.setText(currentWeather.getHighestTemperature());
        lowestTempTextView.setText(currentWeather.getLowestTemperature());
        */


        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.darksky.net/forecast/ceb0ced205a989736a845dcdb873d4d1/37.8267,-122.4233?units=si";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            CurrentWeather currentWeather = getCurrentWeatherFromJson(response);
///////////////////// DailyWeather/*
                            ArrayList<Day> days = getDailyWeatherFromJson(response);

                            for (Day day : days) {

                                /*Log.d("DayName" , day.getDayname());
                                Log.d("Summary" , day.getWeatherDescription());
                                Log.d("RainProbability" , day.getRainProbability());*/

                            }
////////////////////

////////////////////Hourly Weather

                            ArrayList<Hour> hours = getHourlyWeatherFromJson(response);

                            /*for (Hour hour : hours) {

                                Log.d("HourTitle" , hour.getTitle());
                                Log.d("HourlyDescription" , hour.getWeatherDescription());

                            }*/


//////////////////////

////////////////////Minutely Weather

                            ArrayList<Minute> minutes = getMinutelyWeatherFromJson(response);

                            for (Minute minute : minutes) {
                                Log.d("Minute" , minute.getTitle());
                                Log.d("Rain Probability" , minute.getRainProbability());
                            }
//////////////////////
                            iconImageView.setImageDrawable(currentWeather.getIconDrawableResource());
                            descriptionTextView.setText(currentWeather.getDescription());
                            currentTempTextView.setText(currentWeather.getCurrentTemperature());
                            highestTempTextView.setText(String.format("H: %s",currentWeather.getHighestTemperature()));
                            lowestTempTextView.setText(String.format("L: %s",currentWeather.getLowestTemperature()));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    @OnClick(R.id.dailyWeatherTextView)
    public void dailyWeatherClick() {

        Intent dailyActivityIntent = new Intent(MainActivity.this, DailyWeatherActivity.class);

        startActivity(dailyActivityIntent);
    }

    @OnClick(R.id.hourlyWeatherTextView)
    public void hourlyWeatherClick() {
        Intent hourlyActivityIntent = new Intent(MainActivity.this, HourlyWeatherActivity.class);

        startActivity(hourlyActivityIntent);
    }

    @OnClick(R.id.minutelyWeatherTextView)
    public void minutelyWeatherClick() {
        Intent minutelyActivityIntent = new Intent(MainActivity.this, MinutelyWeatherActivity.class);

        startActivity(minutelyActivityIntent);
    }

    private CurrentWeather getCurrentWeatherFromJson(String json)throws JSONException{

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonWithCurrentWeather = jsonObject.getJSONObject("currently");

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject("daily");

        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray("data");

        JSONObject jsonWithTodayData = jsonWithDailyWeatherData.getJSONObject(0);

        String summary =  jsonWithCurrentWeather.getString("summary");
        String icon = jsonWithCurrentWeather.getString("icon");
        String temperature = Math.round(jsonWithCurrentWeather.getDouble("temperature")) + "";

        String  maxTemperature = Math.round(jsonWithTodayData.getDouble("temperatureMax")) +"";
        String minTemperature = Math.round(jsonWithTodayData.getDouble("temperatureMin"))+"";


        CurrentWeather currentWeather = new CurrentWeather(MainActivity.this);
        currentWeather.setDescription(summary);
        currentWeather.setIconImage(icon);
        currentWeather.setCurrentTemperature(temperature);
        currentWeather.setHighestTemperature(maxTemperature);
        currentWeather.setLowestTemperature(minTemperature);

        return currentWeather;
    }

    private ArrayList<Day> getDailyWeatherFromJson(String json) throws JSONException{

        DateFormat dateFormat = new SimpleDateFormat("EEEE");

        ArrayList<Day> days = new ArrayList<>();


        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject("daily");

        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray("data");

        for (int i = 0; i< jsonWithDailyWeatherData.length(); i++){

            Day day = new Day();

            JSONObject jsonObjectActual = jsonWithDailyWeatherData.getJSONObject(i);

            String rainProbability = jsonObjectActual.getDouble("precipProbability") + "";
            String weatherDescription = jsonObjectActual.getString("summary");
            String dayName = dateFormat.format(jsonObjectActual.getLong("time")*1000);

            day.setDayname(dayName);
            day.setRainProbability(rainProbability);
            day.setWeatherDescription(weatherDescription);

            days.add(day);


        }


        return days;
    }


    private ArrayList<Hour> getHourlyWeatherFromJson(String json) throws JSONException{

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));

        ArrayList<Hour> hours = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonObjectData = jsonObject.getJSONObject("hourly");

        JSONArray jsonArrayHoras = jsonObjectData.getJSONArray("data");

        for (int i = 0; i < jsonArrayHoras.length(); i++){

            Hour hour = new Hour();

            JSONObject jsonObjectActual = jsonArrayHoras.getJSONObject(i);

            String title = dateFormat.format(jsonObjectActual.getLong("time")*1000);
            String description = jsonObjectActual.getString("summary");

            hour.setTitle(title);
            hour.setWeatherDescription(description);

            hours.add(hour);

        }

        return hours;
    }

    private ArrayList<Minute> getMinutelyWeatherFromJson(String json) throws JSONException{

        ArrayList<Minute> minutes = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("mm");

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonMinutelyWeather = jsonObject.getJSONObject("minutely");

        JSONArray jsonMinutelyWeatherData = jsonMinutelyWeather.getJSONArray("data");

        for (int i = 0; i<jsonMinutelyWeatherData.length(); i++){

            Minute minute = new Minute();

            JSONObject jsonObjectAntual = jsonMinutelyWeatherData.getJSONObject(i);

            String title = dateFormat.format(jsonObjectAntual.getDouble("time")*1000);
            String rainProbability = jsonObjectAntual.getDouble("precipProbability") + "";

            minute.setTitle(title);
            minute.setRainProbability(rainProbability);

            minutes.add(minute);

        }

        return minutes;
    }

}

package com.example.jesus.colorweather;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {


    public static final String DATA = "data";
    public static final String SUMMARY = "summary";
    public static final String TIME = "time";
    public static final String PRECIP_PROBABILITY = "precipProbability";
    public static final String CURRENTLY = "currently";
    public static final String DAILY = "daily";
    public static final String ICON = "icon";
    public static final String TEMPERATURE = "temperature";
    public static final String TEMPERATURE_MAX = "temperatureMax";
    public static final String TEMPERATURE_MIN = "temperatureMin";
    public static final String HOURLY = "hourly";
    public static final String TIMEZONE = "timezone";
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
    ArrayList<Day> days;
    ArrayList<Hour> hours;
    ArrayList<Minute> minutes;

    String latitudFromGPS = "19.409757011748276";
    String longitudFromGPS = "-99.16954457759857";


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


        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 10);

        LocationManager mLocManger = (LocationManager) getSystemService(LOCATION_SERVICE);
        Localizacion localizacion = new Localizacion();

        mLocManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0 , 0, localizacion);



        RequestQueue queue = Volley.newRequestQueue(this);

        String forecastURL = "https://api.darksky.net/forecast";
        String apiKey = "ceb0ced205a989736a845dcdb873d4d1";
        String latitud = "37.8267";
        String longitud = "-122.4233";
        String units = "units=si";

        String url = forecastURL + "/" + apiKey+ "/" +  latitudFromGPS + "," + longitudFromGPS + "?" + units + "";
        Log.d("Valor de URL", longitudFromGPS + "-....-" + latitudFromGPS);

        //String url = "https://api.darksky.net/forecast/ceb0ced205a989736a845dcdb873d4d1/37.8267,-122.4233?units=si";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            CurrentWeather currentWeather = getCurrentWeatherFromJson(response);
                            days = getDailyWeatherFromJson(response);
                            hours = getHourlyWeatherFromJson(response);
                            minutes = getMinutelyWeatherFromJson(response);

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

                Toast.makeText(MainActivity.this, "Conection Error", Toast.LENGTH_SHORT).show();
                
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }





    @OnClick(R.id.dailyWeatherTextView)
    public void dailyWeatherClick() {

        Intent dailyActivityIntent = new Intent(MainActivity.this, DailyWeatherActivity.class);
        dailyActivityIntent.putParcelableArrayListExtra("days", days);
        startActivity(dailyActivityIntent);
    }

    @OnClick(R.id.hourlyWeatherTextView)
    public void hourlyWeatherClick() {
        Intent hourlyActivityIntent = new Intent(MainActivity.this, HourlyWeatherActivity.class);
        hourlyActivityIntent.putParcelableArrayListExtra("hours", hours);
        startActivity(hourlyActivityIntent);
    }

    @OnClick(R.id.minutelyWeatherTextView)
    public void minutelyWeatherClick() {
        Intent minutelyActivityIntent = new Intent(MainActivity.this, MinutelyWeatherActivity.class);
        minutelyActivityIntent.putParcelableArrayListExtra("minutes", minutes);
        startActivity(minutelyActivityIntent);
    }

    private CurrentWeather getCurrentWeatherFromJson(String json)throws JSONException{

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonWithCurrentWeather = jsonObject.getJSONObject(CURRENTLY);

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);

        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray(DATA);

        JSONObject jsonWithTodayData = jsonWithDailyWeatherData.getJSONObject(0);

        String summary =  jsonWithCurrentWeather.getString(SUMMARY);
        String icon = jsonWithCurrentWeather.getString(ICON);
        String temperature = Math.round(jsonWithCurrentWeather.getDouble(TEMPERATURE)) + "";

        String  maxTemperature = Math.round(jsonWithTodayData.getDouble(TEMPERATURE_MAX)) +"";
        String minTemperature = Math.round(jsonWithTodayData.getDouble(TEMPERATURE_MIN))+"";


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

        JSONObject jsonWithDailyWeather = jsonObject.getJSONObject(DAILY);

        JSONArray jsonWithDailyWeatherData = jsonWithDailyWeather.getJSONArray(DATA);

        for (int i = 0; i< jsonWithDailyWeatherData.length(); i++){

            Day day = new Day();

            JSONObject jsonObjectActual = jsonWithDailyWeatherData.getJSONObject(i);

            String rainProbability = jsonObjectActual.getDouble(PRECIP_PROBABILITY) + "";
            String weatherDescription = jsonObjectActual.getString(SUMMARY);
            String dayName = dateFormat.format(jsonObjectActual.getLong(TIME)*1000);

            day.setDayname(dayName);
            day.setRainProbability(rainProbability);
            day.setWeatherDescription(weatherDescription);

            days.add(day);


        }


        return days;
    }


    private ArrayList<Hour> getHourlyWeatherFromJson(String json) throws JSONException{



        ArrayList<Hour> hours = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(json);

        String timezone = jsonObject.getString(TIMEZONE);

        JSONObject jsonObjectData = jsonObject.getJSONObject(HOURLY);

        JSONArray jsonArrayHoras = jsonObjectData.getJSONArray(DATA);

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));

        for (int i = 0; i < jsonArrayHoras.length(); i++){

            Hour hour = new Hour();

            JSONObject jsonObjectActual = jsonArrayHoras.getJSONObject(i);

            String title = dateFormat.format(jsonObjectActual.getLong(TIME)*1000);
            String description = jsonObjectActual.getString(SUMMARY);

            hour.setTitle(title);
            hour.setWeatherDescription(description);

            hours.add(hour);

        }

        return hours;
    }

    private ArrayList<Minute> getMinutelyWeatherFromJson(String json) throws JSONException{

        ArrayList<Minute> minutes = new ArrayList<>();

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        JSONObject jsonObject = new JSONObject(json);

        JSONObject jsonMinutelyWeather = jsonObject.getJSONObject("minutely");

        JSONArray jsonWithMinutelyWeatherData = jsonMinutelyWeather.getJSONArray(DATA);

        for (int i = 0; i<jsonWithMinutelyWeatherData.length(); i++){

            Minute minute = new Minute();

            JSONObject jsonObjectAntual = jsonWithMinutelyWeatherData.getJSONObject(i);

            String title = dateFormat.format(jsonObjectAntual.getDouble(TIME)*1000);
            String rainProbability = jsonObjectAntual.getDouble(PRECIP_PROBABILITY) + "";

            minute.setTitle(title);
            minute.setRainProbability(rainProbability);

            minutes.add(minute);

        }

        return minutes;
    }


    public class Localizacion implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            latitudFromGPS = location.getLatitude()+"";
            longitudFromGPS = location.getLongitude()+"";
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}

package com.ezloop.raindrop;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ezloop.raindrop.Data.WeatherObject;
import com.ezloop.raindrop.Permissions.MarshmallowPermissions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;
    private MarshmallowPermissions mPermissions = new MarshmallowPermissions(this);
    private String mLongitude;
    private String mLatitude;
    private WeatherObject mCurrentWeather;
    private ArrayList<WeatherObject> mForecastList;

    private String mApiString = "https://api.openweathermap.org/data/2.5/weather?";
    private String mForecastApiString = "https://api.openweathermap.org/data/2.5/forecast?";
    private final String API_KEY = "ed22a51e255cba44a14b4b6e9042f623";
    private String mUnit = "metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        mCurrentWeather = new WeatherObject();
        mForecastList = new ArrayList<WeatherObject>();
        client = LocationServices.getFusedLocationProviderClient(this);

        //CHECK PERMISSIONS FOR LOCATION
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mPermissions.requestPermissionForFineLocation();
            mPermissions.requestPermissionForCoarseLocation();
            return;
        }
        //Get location and start HTTP call
        client.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mLatitude = String.format("%.2f", location.getLatitude());
                    mLongitude = String.format("%.2f", location.getLongitude());
                    mApiString += "lat=" + mLatitude + "&lon=" + mLongitude +
                            "&units=" + mUnit +
                            "&appid=" + API_KEY;
                    mForecastApiString += "lat=" + mLatitude + "&lon=" + mLongitude +
                            "&units=" + mUnit +
                            "&appid=" + API_KEY;
                    getCurrentWeather(mApiString);
                    getForecast(mForecastApiString);
                }
            }
        });

        //Go to main activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,
                        MainActivity.class);
                intent.putExtra("current", mCurrentWeather);
                intent.putExtra("forecast", mForecastList);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }

    public void getCurrentWeather(String mApiString) {


        JsonObjectRequest mJsonObjectRequest = new JsonObjectRequest(Request.Method.GET, mApiString, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject mMainObject = response.getJSONObject("main");
                    JSONArray mWeatherArray = response.getJSONArray("weather");
                    JSONObject mWeatherObject = mWeatherArray.getJSONObject(0);
                    JSONObject mWindObject = response.getJSONObject("wind");
                    JSONObject mCloudsObject = response.getJSONObject("clouds");
                    JSONObject mSysObject = response.getJSONObject("sys");

                    //Get city name and country code
                    mCurrentWeather.setmCityName(response.getString("name"));
                    mCurrentWeather.setmCountryCode(mSysObject.getString("country"));

                    //Get data from Object "weather"
                    mCurrentWeather.setmWeatherID(mWeatherObject.getDouble("id"));
                    mCurrentWeather.setmWeatherMain(mWeatherObject.getString("main"));
                    mCurrentWeather.setmWeatherDesc(mWeatherObject.getString("description").substring(0, 1).toUpperCase() + mWeatherObject.getString("description").substring(1));

                    //Get data from Object "main"
                    mCurrentWeather.setmMainTemp(mMainObject.getDouble("temp"));
                    mCurrentWeather.setmMainPressure(mMainObject.getDouble("pressure"));
                    mCurrentWeather.setmMainHumidity(mMainObject.getDouble("humidity"));
                    mCurrentWeather.setmMainMin(mMainObject.getDouble("temp_min"));
                    mCurrentWeather.setmMainMax(mMainObject.getDouble("temp_max"));

                    //Get data from Object "wind"
                    mCurrentWeather.setmWindSpeed(mWindObject.getDouble("speed"));
                    mCurrentWeather.setmWindDeg(mWindObject.getDouble("deg"));

                    //Get data from Object "clouds"
                    mCurrentWeather.setmCloudPercentage(mCloudsObject.getDouble("all"));

                    //Get data from Object "sys"
                    mCurrentWeather.setmSunRise(mSysObject.getDouble("sunrise"));
                    mCurrentWeather.setmSunSet(mSysObject.getDouble("sunset"));

                    //Set data to UI

                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );

        RequestQueue mQueue = Volley.newRequestQueue(this);
        mQueue.add(mJsonObjectRequest);
    }

    public void getForecast(String mApiString){
        JsonObjectRequest mForecastRequest = new JsonObjectRequest(Request.Method.GET, mApiString,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray mForecastArray = response.getJSONArray("list");

                    for (int i = 0; i < mForecastArray.length(); i++){
                        JSONObject mJObject = mForecastArray.getJSONObject(i);
                        JSONObject mMainObject = mJObject.getJSONObject("main");
                        JSONArray mWeatherArray = mJObject.getJSONArray("weather");
                        JSONObject mWeatherObject = mWeatherArray.getJSONObject(0);
                        JSONObject mCloudsObject = mJObject.getJSONObject("clouds");
                        JSONObject mWindObject = mJObject.getJSONObject("wind");

                        WeatherObject mWObject = new WeatherObject();
                        mWObject.setmDT(mJObject.getDouble("dt"));

                        //Get data from Object "main"
                        mWObject.setmMainTemp(mMainObject.getDouble("temp"));
                        mWObject.setmMainPressure(mMainObject.getDouble("pressure"));
                        mWObject.setmMainHumidity(mMainObject.getDouble("humidity"));
                        mWObject.setmMainMin(mMainObject.getDouble("temp_min"));
                        mWObject.setmMainMax(mMainObject.getDouble("temp_max"));

                        //Get data from Object "weather"
                        mWObject.setmWeatherID(mWeatherObject.getDouble("id"));
                        mWObject.setmWeatherMain(mWeatherObject.getString("main").
                                substring(0,1).toUpperCase() +
                                mWeatherObject.getString("main").substring(1));
                        mWObject.setmWeatherDesc(mWeatherObject.getString("description")
                                .substring(0,1).toUpperCase() +
                                mWeatherObject.getString("description").substring(1));

                        //Get data from Object "wind"
                        mWObject.setmWindSpeed(mWindObject.getDouble("speed"));
                        mWObject.setmWindDeg(mWindObject.getDouble("deg"));

                        //Get data from Object "clouds"
                        mWObject.setmCloudPercentage(mCloudsObject.getDouble("all"));

                        mForecastList.add(mWObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "JSon Exception " + e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Volley Exception " + error, Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue mQueue = Volley.newRequestQueue(this);
        mQueue.add(mForecastRequest);
    }
}

package com.ezloop.raindrop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ezloop.raindrop.Data.WeatherObject;
import com.ezloop.raindrop.RecyclerViewAdapters.ForecastListAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String APP_ID = "ca-app-pub-3940256099942544~3347511713";      //Test
    //private static final String APP_ID = "ca-app-pub-6328147982755691~8483682807";      //Full
    private AdView mAdView;

    private WeatherObject mCurrentWeather;
    private ArrayList<WeatherObject> mForecastList;

    private RecyclerView mRecyclerView;
    private ForecastListAdapter mAdapter;

    private TextView mTxtTemp, mTxtDescription;
    private ImageView mImgWeatherIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mCurrentWeather = (WeatherObject) getIntent().getSerializableExtra("current");
        mForecastList = (ArrayList<WeatherObject>) getIntent()
                .getSerializableExtra("forecast");

        CharSequence mTitle = mCurrentWeather.getmCityName();
        getSupportActionBar().setTitle(mTitle);

        //Initialize AddMob
        MobileAds.initialize(this, APP_ID);
        mAdView = findViewById(R.id.adView);
        AdRequest mAdRequest = new AdRequest.Builder().build();
        mAdView.loadAd(mAdRequest);

        //Recycler View
        mRecyclerView = findViewById(R.id.rvForecast);
        mAdapter = new ForecastListAdapter(mForecastList, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Main UI
        mTxtTemp = findViewById(R.id.txtMainTemp);
        mTxtDescription = findViewById(R.id.txtMainDesc);
        mImgWeatherIcon = findViewById(R.id.icoMainCurrent);
        CharSequence mTemp = Integer.toString((int) Math.round(mCurrentWeather.getmMainTemp())) +
                (char) 0x00B0 + "C";
        mTxtTemp.setText(mTemp);
        mTxtDescription.setText(mCurrentWeather.getmWeatherDesc());
        setIcon(mCurrentWeather.getmWeatherID());

    }

    private void setIcon(double mWeatherID) {
        switch ((int) mWeatherID) {
            //Change icon to Rain with Thunder
            case 200:       //thunderstorm with light rain
            case 201:       //thunderstorm with rain
            case 202:       //thunderstorm with heavy rain
            case 230:       //thunderstorm with light drizzle
            case 231:       //thunderstorm with drizzle
            case 232:       //thunderstorm with heavy drizzle
                mImgWeatherIcon.setImageResource(R.drawable.ic_rain_w_thunder);
                break;
            //Change icon to Thunderstorm
            case 210:       //light thunderstorm
            case 211:       //thunderstorm
            case 212:       //heavy thunderstorm
            case 221:       //ragged thunderstorm
                mImgWeatherIcon.setImageResource(R.drawable.ic_thunder_storm);
                break;
            //Change icon to Light Rain
            case 300:       //light intensity drizzle
            case 301:       //drizzle
            case 302:       //heavy intensity drizzle
            case 310:       //light intensity drizzle rain
            case 311:       //drizzle rain
            case 312:       //heavy intensity drizzle rain
            case 313:       //shower rain and drizzle
            case 314:       //heavy shower rain and drizzle
            case 321:       //shower drizzle
                mImgWeatherIcon.setImageResource(R.drawable.ic_showers);
                break;
            //Change icon to Rain
            case 500:       //light rain
            case 501:       //moderate rain
            case 520:       //light intensity shower rain
            case 521:       //shower rain
            case 531:       //ragged shower rain
                mImgWeatherIcon.setImageResource(R.drawable.ic_rainy);
                break;
            //Change icon to Heavy Rain
            case 502:       //heavy intensity rain
            case 503:       //very heavy rain
            case 504:       //extreme rain
            case 522:       //heavy intensity shower rain
                mImgWeatherIcon.setImageResource(R.drawable.ic_heavy_rain);
                break;
            /*//Change icon to Freezing Rain
            case 511:       //freezing rain
            case 611:       //sleet
            case 612:       //shower sleet
            case 616:       //rain and snow
                break;
            //Change icon to Snow
            case 600:       //light snow
            case 601:       //snow
            case 620:       //light shower snow
            case 621:       //shower snow
                break;
            //Change icon to Heavy Snow
            case 602:       //heavy snow
            case 622:       //heavy shower snow
                break;
            //Change icon to Mist
            case 701:       //mist
            case 721:       //haze
            case 741:       //fog
            case 771:       //squalls
                break;
            //Change icon to Dust
            case 731:       //sand, dust whirls
            case 751:       //sand
            case 761:       //dust
                break;
            // Change icon to Ash
            case 711:       //smoke
            case 762:       //volcanic ash
                break;
            //Change icon to Tornado
            case 781:       //tornado
                break;*/
            //Change icon to Clear
            case 800:       //clear sky
                mImgWeatherIcon.setImageResource(R.drawable.ic_sunny);
                break;
            //Change icon to Cloudy
            case 801:       //few clouds
            case 802:       //scattered clouds
                mImgWeatherIcon.setImageResource(R.drawable.ic_cloudy);
                break;
            //Change icon to Overcast
            case 803:       //broken clouds
            case 804:       //overcast clouds
                mImgWeatherIcon.setImageResource(R.drawable.ic_overcast);
                break;
        }
    }
}

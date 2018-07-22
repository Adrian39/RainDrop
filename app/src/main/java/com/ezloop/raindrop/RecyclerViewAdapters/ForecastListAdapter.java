package com.ezloop.raindrop.RecyclerViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezloop.raindrop.Data.WeatherObject;
import com.ezloop.raindrop.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.MyViewHolder> {

    private ArrayList<WeatherObject> mForecastList = new ArrayList<WeatherObject>();
    private Context mContext;

    public ForecastListAdapter(ArrayList<WeatherObject> mForecastList, Context mContext){
        this.mForecastList = mForecastList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_forecast,
                parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtForecast.setText(mForecastList.get(position).getmWeatherDesc() + " " +
                Math.round(mForecastList.get(position).getmMainMin()) + "°/" +
                Math.round(mForecastList.get(position).getmMainMax()) + "°");
        holder.txtTime.setText(formatTime(mForecastList.get(position).getmDT()));
        holder.icoForecast.setImageResource(setIcon(mForecastList.get(position).getmWeatherID()));
    }

    @Override
    public int getItemCount() {
        return mForecastList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtForecast;
        TextView txtTime;
        ImageView icoForecast;
        public MyViewHolder(View itemView) {
            super(itemView);
            txtForecast = itemView.findViewById(R.id.txtForecast);
            txtTime = itemView.findViewById(R.id.txtTime);
            icoForecast = itemView.findViewById(R.id.icoForecast);
        }
    }
    private String formatTime(double mUTX){
        long unixSeconds = (long) mUTX;
        Date mDate = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
        return sdf.format(mDate);
    }

    public void updateData(ArrayList<WeatherObject> newData){
        this.mForecastList = newData;
        notifyDataSetChanged();
    }

    private int setIcon(double mWeatherID) {
        switch ((int) mWeatherID) {
            //Change icon to Rain with Thunder
            case 200:       //thunderstorm with light rain
            case 201:       //thunderstorm with rain
            case 202:       //thunderstorm with heavy rain
            case 230:       //thunderstorm with light drizzle
            case 231:       //thunderstorm with drizzle
            case 232:       //thunderstorm with heavy drizzle
                return R.drawable.ic_rain_w_thunder;
            //Change icon to Thunderstorm
            case 210:       //light thunderstorm
            case 211:       //thunderstorm
            case 212:       //heavy thunderstorm
            case 221:       //ragged thunderstorm
                return R.drawable.ic_thunder_storm;
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
                return R.drawable.ic_showers;
            //Change icon to Rain
            case 500:       //light rain
            case 501:       //moderate rain
            case 520:       //light intensity shower rain
            case 521:       //shower rain
            case 531:       //ragged shower rain
                return R.drawable.ic_rainy;
            //Change icon to Heavy Rain
            case 502:       //heavy intensity rain
            case 503:       //very heavy rain
            case 504:       //extreme rain
            case 522:       //heavy intensity shower rain
                return R.drawable.ic_heavy_rain;
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
                return R.drawable.ic_sunny;
            //Change icon to Cloudy
            case 801:       //few clouds
            case 802:       //scattered clouds
                return R.drawable.ic_cloudy;
            //Change icon to Overcast
            case 803:       //broken clouds
            case 804:       //overcast clouds
                return R.drawable.ic_overcast;
        }
        return 0;
    }
}

package com.ezloop.raindrop.Data;

import java.io.Serializable;

public class WeatherObject implements Serializable{

    private double mDT;
    private double mMainTemp;
    private double mMainPressure;
    private double mMainHumidity;
    private double mMainMin;
    private double mMainMax;
    private double mWindSpeed;
    private double mWindDeg;
    private double mCloudPercentage;
    private double mRainVol;
    private double mSnowVol;
    private double mSunRise;
    private double mSunSet;
    private double mWeatherID;

    private String mWeatherMain, mWeatherDesc,
            mCountryCode, mCityName;

    public WeatherObject(){
    }

    public double getmDT() {
        return mDT;
    }

    public void setmDT(double mDT) {
        this.mDT = mDT;
    }

    public double getmMainTemp() {
        return mMainTemp;
    }

    public void setmMainTemp(double mMainTemp) {
        this.mMainTemp = mMainTemp;
    }

    public double getmMainPressure() {
        return mMainPressure;
    }

    public void setmMainPressure(double mMainPressure) {
        this.mMainPressure = mMainPressure;
    }

    public double getmMainHumidity() {
        return mMainHumidity;
    }

    public void setmMainHumidity(double mMainHumidity) {
        this.mMainHumidity = mMainHumidity;
    }

    public double getmMainMin() {
        return mMainMin;
    }

    public void setmMainMin(double mMainMin) {
        this.mMainMin = mMainMin;
    }

    public double getmMainMax() {
        return mMainMax;
    }

    public void setmMainMax(double mMainMax) {
        this.mMainMax = mMainMax;
    }

    public double getmWindSpeed() {
        return mWindSpeed;
    }

    public void setmWindSpeed(double mWindSpeed) {
        this.mWindSpeed = mWindSpeed;
    }

    public double getmWindDeg() {
        return mWindDeg;
    }

    public void setmWindDeg(double mWindDeg) {
        this.mWindDeg = mWindDeg;
    }

    public double getmCloudPercentage() {
        return mCloudPercentage;
    }

    public void setmCloudPercentage(double mCloudPercentage) {
        this.mCloudPercentage = mCloudPercentage;
    }

    public double getmRainVol() {
        return mRainVol;
    }

    public void setmRainVol(double mRainVol) {
        this.mRainVol = mRainVol;
    }

    public double getmSnowVol() {
        return mSnowVol;
    }

    public void setmSnowVol(double mSnowVol) {
        this.mSnowVol = mSnowVol;
    }

    public double getmSunRise() {
        return mSunRise;
    }

    public void setmSunRise(double mSunRise) {
        this.mSunRise = mSunRise;
    }

    public double getmSunSet() {
        return mSunSet;
    }

    public void setmSunSet(double mSunSet) {
        this.mSunSet = mSunSet;
    }

    public double getmWeatherID(){
        return mWeatherID;
    }

    public void setmWeatherID(double mWeatherId){
        this.mWeatherID = mWeatherId;
    }

    public String getmWeatherMain() {
        return mWeatherMain;
    }

    public void setmWeatherMain(String mWeatherMain) {
        this.mWeatherMain = mWeatherMain;
    }

    public String getmWeatherDesc() {
        return mWeatherDesc;
    }

    public void setmWeatherDesc(String mWeatherDesc) {
        this.mWeatherDesc = mWeatherDesc;
    }

    public String getmCountryCode() {
        return mCountryCode;
    }

    public void setmCountryCode(String mCountryCode) {
        this.mCountryCode = mCountryCode;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }
}

package com.xli.airportinfo_json;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ShangyuSun on 2018/2/20.
 */

public class ParseJsonInfo {
    private final static String TAG = "AirPortInfo_HttpURLConn";
    AirportInfo airportInfo = null;
    Forcast forcast = null;
    String airport = null;
    JSONObject jObject;
    JSONObject jResultObject;
    JSONObject jLocationObject;
    JSONObject jWindObject;
    JSONObject jWeatherObject;
    JSONObject jAstronomyObject;
    // constructors
    public ParseJsonInfo() {
    }
    public ParseJsonInfo(String airport) {
        this.airport = airport;
    }
    // getters and setters
    public void setAirport(String airport) {
        this.airport = airport;
    }
    public String getAirport() {
        return airport;
    }
    public AirportInfo getAirportInfo() {
        return airportInfo;
    }
    @Override
    public String toString() {
        return super.toString() + airportInfo.toString();
    }

    public AirportInfo decodeMessage(String message) {
        try {
            Log.d(TAG, "Parsing: " + message);
            airportInfo = new AirportInfo();
            //airportInfo.setAirport(airportSymbol);
            jObject = new JSONObject(message);
            jResultObject = jObject.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
            jLocationObject = jResultObject.getJSONObject("location");
            jWindObject = jResultObject.getJSONObject("wind");
            jWeatherObject = jResultObject.getJSONObject("item");
            jAstronomyObject = jResultObject.getJSONObject("astronomy");
            airportInfo.setDate(jWeatherObject.getJSONObject("condition").getString("date"));
            airportInfo.setTemperature(jWeatherObject.getJSONObject("condition").getString("temp"));
            airportInfo.setText(jWeatherObject.getJSONObject("condition").getString("text"));
            airportInfo.setCity(jLocationObject.getString("city"));
            airportInfo.setState(jLocationObject.getString("region"));
            airportInfo.setCountry(jLocationObject.getString("country"));
            airportInfo.setChill(jWindObject.getString("chill"));
            airportInfo.setDirection(jWindObject.getString("direction"));
            airportInfo.setSpeed(jWindObject.getString("speed"));
            airportInfo.setSunrise(jAstronomyObject.getString("sunrise"));
            airportInfo.setSunset(jAstronomyObject.getString("sunset"));

        } catch (Exception e) {
            Log.e(TAG, "decodeMessage: exception during parsing");
            e.printStackTrace();
            return null;
        }
        return airportInfo;
    }

    public ArrayList<Forcast> decodeForecast(String message) {
        ArrayList<Forcast> list = new ArrayList<>();
        try {
            jObject = new JSONObject(message);
            jResultObject = jObject.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
            jWeatherObject = jResultObject.getJSONObject("item");
            JSONArray jArrayFeatures = jWeatherObject.getJSONArray("forecast");
            JSONObject jForcastObject;
            Forcast forcast;
            for (int i = 0; i < 5; i++) {
                forcast = new Forcast();
                jForcastObject = jArrayFeatures.getJSONObject(i);
                forcast.setDate(jForcastObject.getString("date"));
                forcast.setDay(jForcastObject.getString("day"));
                forcast.setHigh(jForcastObject.getString("high"));
                forcast.setLow(jForcastObject.getString("low"));
                forcast.setText(jForcastObject.getString("text"));
                list.add(forcast);
            }
        }catch (Exception e) {
            Log.e(TAG, "decodeMessage: exception during parsing");
            e.printStackTrace();
            return null;
        }
        return list;
    }
}

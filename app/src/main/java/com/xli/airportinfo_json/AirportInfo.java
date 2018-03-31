package com.xli.airportinfo_json;

/**
 * Created by ShangyuSun on 2018/2/20.
 */

public class AirportInfo {
    private String date;
    private String temperature;
    private String text;
    private String chill;
    private String direction;
    private String speed;
    private String sunrise;
    private String sunset;
    private String city;
    private String state;
    private String country;


    public AirportInfo(){
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }


    @Override
    public String toString() {
      //  System.out.println("I AM PRINTING TEST!!!! ============");
      //  System.out.println(TAG_NAME);
        return super.toString() + "City: " + city + " State: " + state + "Country: " + country +
                " Date: " + date + "Temperature: " + temperature + " Text:" + text +"\n" +
                "Chill: " + chill + "Direction: " + direction + "Speed: " + speed + "\n" +  "Sunrise: " +
                sunrise + "Sunset: " + sunset;
    }

}

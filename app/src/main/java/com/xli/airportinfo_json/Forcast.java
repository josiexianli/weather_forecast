package com.xli.airportinfo_json;

/**
 * Created by ShangyuSun on 2018/3/20.
 */

public class Forcast {
    private String date;
    private String day;
    private String high;
    private String low;
    private String text;

    public Forcast (){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        //  System.out.println("I AM PRINTING TEST!!!! ============");
        //  System.out.println(TAG_NAME);

        return "Date: " + date + "   Day: " + day + " \nHigh: " + high +
                "   Low: " + low + "   Text: " + text;
    }

}

package com.xli.airportinfo_json;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class AirportActivityFragment extends Fragment
        implements VolleyClassString.OnInfoListener<String> {

    ImageView imgClick;
    private final static String TAG = "AirPortInfo_HttpURLConn";
    private VolleyClassString volleyClassString;
    private ArrayList<String> mDataSet = new ArrayList<>();
    ArrayList<Forcast> forecast;
    RecyclerViewAdapter adapter = null;

    public AirportActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View aView =inflater.inflate(R.layout.fragment_airport, container, false);

        imgClick = (ImageView)aView.findViewById(R.id.imageView);

        imgClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this, "You clicked on ImageView", Toast.LENGTH_LONG).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.yahoo.com/?ilc=401"));
                startActivity(browserIntent);
            }

        });
        return aView;
    }


    public void displayInfo(AirportInfo airportInfo) {
        {
            TextView tvCity = (TextView) getActivity().findViewById(R.id.city);
            TextView tvState = (TextView) getActivity().findViewById(R.id.state);
            TextView tvCountry = (TextView) getActivity().findViewById(R.id.country);
            TextView tvDate = (TextView) getActivity().findViewById(R.id.date);
            TextView tvTemperature = (TextView) getActivity().findViewById(R.id.temperature);
            TextView tvText = (TextView) getActivity().findViewById(R.id.text);
            TextView tvChill = (TextView) getActivity().findViewById(R.id.chill);
            TextView tvDirection = (TextView) getActivity().findViewById(R.id.direction);
            TextView tvSpeed = (TextView) getActivity().findViewById(R.id.speed);
            TextView tvSunrise = (TextView) getActivity().findViewById(R.id.sunrise);
            TextView tvSunset = (TextView) getActivity().findViewById(R.id.sunset);

            if (airportInfo != null) {
                Log.d(TAG, this.getClass().getSimpleName() + " : " + airportInfo.toString());
               // String subMsg = getResources().getString(R.string.Airport) + " " + airportInfo.getAirport();
               // tvSymbol.setText(subMsg);
                tvCity.setText("City: " + airportInfo.getCity());
                tvState.setText("State: " + airportInfo.getState());
                tvCountry.setText("Country: " + airportInfo.getCountry());
              //  subMsg = getResources().getString(R.string.Weather) + " " + airportInfo.getAirportWeather();
                tvDate.setText("Date: " + airportInfo.getDate());
                tvTemperature.setText("Temperature: " + airportInfo.getTemperature());
                tvText.setText("Text: " + airportInfo.getText());
                tvChill.setText("Chill: " + airportInfo.getChill());
                tvDirection.setText("Direction: " + airportInfo.getDirection());
                tvSpeed.setText("Speed: " + airportInfo.getSpeed());
                tvSunrise.setText("Sunrise: " + airportInfo.getSunrise());
                tvSunset.setText("Sunset: " + airportInfo.getSunset());

            } else {
                String badMsg = "Problem downloading airport info. Please try again later.";
                tvCity.setText(badMsg);
            }
        }
    }

    private void setUpData(ArrayList<Forcast> forecast) {
        //ArrayList<String> testList =new ArrayList<String>();

//        for(int i =0;i<5 ;i++){
//           // testList.add("test string" +  i);
//            mDataSet.add("test string" +  i);
//        }

        //System.out.println("I AM HERE!");
        mDataSet = new ArrayList<>();


        for (Forcast e: forecast) {
            mDataSet.add(e.toString());
            System.out.println(e.toString());
        }
        adapter = new RecyclerViewAdapter(mDataSet);
        RecyclerView recyclerView = getActivity().findViewById(R.id.EQRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button decodeAirPortInfo = (Button) getActivity().findViewById(R.id.buttonGetInfo);
        decodeAirPortInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                getNetworkInfo();
            }
        });
//        Button decodeForecast = (Button)getActivity().findViewById(R.id.button);
//        decodeForecast.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View arg0) {
//                getNetworkInfo();
//            }
//        });

//        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.yahoo.com/?ilc=401"));
//
//        ImageView imgFavorite = ()findViewById(R.id.imageView);
//        imgFavorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(browserIntent);
//            }
//        });


        RecyclerView recyclerView = getActivity().findViewById(R.id.EQRecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    public void getNetworkInfo() {
    /* Get what user typed to the EditText so we can get the stock symbol */
        String queryBaseWeather = "https://query.yahooapis.com/v1/public/yql";
        String queryKey = "q";
        String selectParameter =
                "Select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"" ;
        String selectParameterEnd = "\")";
        String envKey = "env";
        String datatables = "store://datatables.org/alltableswithkeys" ;
        String formatKey = "format";
        String formatType = "json";
        String city = ((EditText) getActivity().findViewById(R.id.edit_input))
                .getText().toString();
        String state = ((EditText) getActivity().findViewById(R.id.editText))
                .getText().toString();
        String cityStateQuery = selectParameter + city + "," + state + selectParameterEnd;
        final String queryWeatherString = Uri.parse(queryBaseWeather).buildUpon()
                .appendQueryParameter(queryKey,cityStateQuery)
                .appendQueryParameter(formatKey, formatType)
                .appendQueryParameter(envKey, datatables)
                .build().toString();
        Log.d( TAG, ": getNetworkInfo: " +   " url: " + queryWeatherString);
        if (volleyClassString == null){
            volleyClassString = new VolleyClassString(getActivity(), this);
        }
        volleyClassString.makeNetworkRequests(queryWeatherString);
    }



    @Override
    public void onInfoAvailable(String responseString) {
        System.out.println(responseString);
        AirportInfo airportInfo;
        if(responseString != null) {
            ParseJsonInfo parseJsonInfo = new ParseJsonInfo();
            airportInfo = parseJsonInfo.
                    decodeMessage(responseString);
            displayInfo(airportInfo);

            ParseJsonInfo parseJsonForcast = new ParseJsonInfo();
//            for(Forcast e :forecast){
//                System.out.println(e.toString());
//            }
            forecast = parseJsonForcast.decodeForecast(responseString);
            setUpData(forecast);



        }
    }


}

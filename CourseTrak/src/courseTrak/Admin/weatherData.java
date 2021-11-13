/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

import java.net.*;
import java.io.*;
import java.lang.Math;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


/**
 *
 * @author markduah
 */

public class weatherData {
    private String title;
    private String temperature;
    private String feelsLike;
    private String highTemp;
    private String lowTemp;
    private String description;
    private String pressure;
    private String humidity;
    private String visibility;
    private String windSpeed;
    private String sunrise;
    private String sunset;
    
    /**
     *
     */
    public weatherData(){
        
    }
    
     /**
     * private helper method the read the contents of a URL as a String.
     * 
     * @param url web/url address to be accessed
     * @return String representation of the given web page or service
     * @throws java.net.MalformedURLException
     * @throws java.io.IOException 
     */
    private static String readURL(String url) throws java.net.MalformedURLException, java.io.IOException{
        
        //create URL object from the given url
        URL service = new URL(url);
        
        //use IOUtils to access the url and return a string
        //String result = IOUtils.toString(service, "UTF-8");
        String result = IOUtils.toString(service.openStream());
        return result;
    }
    
    /**
     * Converts Unix time data collected form the API to real time.
     * 
     * @param unixTime
     * @return time, current local time
     */
    private static String localTime(long unixTime){
        
        Date date = new java.util.Date(unixTime * 1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("h:mm aa");
        String time = sdf.format(date);
        return time;
    }
    
    /**
     * get a connection to API and retrieve all needed data.
     * handles all calculations.
     * @param city 
     */
    public void getData(String city){
        
        //DecimalFormat df = new DecimalFormat("0")
        DecimalFormat df2 = new DecimalFormat("0.00##");
        
        try {
            String JSONString = readURL("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=8866f1b3f1e3ed1607034d40e3894503");
            System.out.println(JSONString);
            
            Object n = JSONValue.parse(JSONString);
            JSONObject weatherData = (JSONObject) n;

            JSONArray array = (JSONArray) (weatherData.get("weather"));
            JSONObject weatherInfo = (JSONObject) (array.get(0));
            
            JSONObject main = (JSONObject)(weatherData.get("main"));
            JSONObject sys = (JSONObject)(weatherData.get("sys"));
            
            JSONObject wind = (JSONObject) (weatherData.get("wind"));
            double temp =  (double) main.get("temp") - 273.15;
            temp = Math.round(temp * 9/5) + 33;
            
            double feelLike =  (double) main.get("feels_like") - 273.15;
            feelLike -= 5;
            feelLike = Math.round(feelLike * 9/5) + 33;
            double tempHigh =  (double) main.get("temp_max") - 273.15;
            tempHigh = Math.round(tempHigh * 9/5) + 33;
            double tempLow =  (double) main.get("temp_min") - 273.15;
            tempLow = Math.round(tempLow * 9/5) + 33;
            long sunRise = (long) sys.get("sunrise");
            long sunSet = (long) sys.get("sunset");
            Long longObject = (long) main.get("pressure");
            double pressureValue = longObject.doubleValue() / 3390;
            double visibilityNum  = 0;
            if(weatherData.get("visibility") != null){
                Long visiObject = (long) weatherData.get("visibility");
                visibilityNum += visiObject.doubleValue() / 1609;
                this.visibility = String.format("%.0f mi", visibilityNum);
            }else{
                this.visibility = "N/A";
            }
           
            String windString = String.valueOf(wind.get("speed"));
            double windSpeed = Double.valueOf(windString) * 1.237;
            System.out.println(windSpeed);
            
            
            this.title = ("Live Weather Report - " + weatherData.get("name") + ", " + sys.get("country"));
            this.description = "" + weatherInfo.get("description");
            this.temperature = String.format("%.0f°", temp);
            this.feelsLike = String.format("%.0f°", feelLike + 10);
            this.highTemp = String.format("%.0f°", tempHigh);
            this.lowTemp = String.format("%.0f°", tempLow);
            this.sunrise = "" + localTime(sunRise);
            this.sunset = "" + localTime(sunSet);
            this.humidity = "" + main.get("humidity") + "%";
            this.pressure = String.format("%.1f inHg", pressureValue * 100);
            this.windSpeed = String.format("%.0f mph", windSpeed);
        } catch (IOException ex) {
            Logger.getLogger(weatherData.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    //getter for all fileds

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     *
     * @return
     */
    public String getFeelsLike() {
        return feelsLike;
    }

    /**
     *
     * @return
     */
    public String getHighTemp() {
        return highTemp;
    }

    /**
     *
     * @return
     */
    public String getLowTemp() {
        return lowTemp;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return
     */
    public String getPressure() {
        return pressure;
    }

    /**
     *
     * @return
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     *
     * @return
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     *
     * @return
     */
    public String getWindSpeed() {
        return windSpeed;
    }

    /**
     *
     * @return
     */
    public String getSunrise() {
        return sunrise;
    }

    /**
     *
     * @return
     */
    public String getSunset() {
        return sunset;
    }
    
}

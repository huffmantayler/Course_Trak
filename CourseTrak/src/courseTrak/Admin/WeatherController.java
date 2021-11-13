/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class WeatherController implements Initializable {

    private Label sunRise;
    private Label temp;
    private Label pressure;
    private Label windSpeed;
    private Label sunSet;
    private Label feelsLike;
    private Label humidity;
    private Label visibility;
    private Label tempNum;
    private Label weatherCity;
    private Label currentMood;
    private Label highLow;
    private TextField citySearch;
    
    private weatherData data = new weatherData();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Get the data (String) from data class and set them to it's
     * corresponding information.
     * @param evt 
     */
    public void checkWeather(ActionEvent event) throws IOException {
        weatherCity.setText(citySearch.getText().toUpperCase());
        data.getData(citySearch.getText());
        
        currentMood.setText(data.getDescription().substring(0, 1).toUpperCase() + data.getDescription().substring(1));
        highLow.setText("The high will be " + data.getHighTemp() + ", with a low of " + data.getLowTemp() + ".");
        
        sunRise.setText(data.getSunrise());
        sunSet.setText(data.getSunset());
        tempNum.setText(data.getTemperature());
        temp.setText(data.getTemperature());
        feelsLike.setText(data.getFeelsLike());
        pressure.setText(data.getPressure());
        humidity.setText(data.getHumidity());
        windSpeed.setText(data.getWindSpeed());
        visibility.setText(data.getVisibility());
    }
}

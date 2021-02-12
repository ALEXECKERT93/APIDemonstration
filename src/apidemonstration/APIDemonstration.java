/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package apidemonstration;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.*;
/**
 *
 * @author M4700
 */

public class APIDemonstration {
    public final static double kelvinDifferential = 273.15;
    public static void getTemperature(){
        // Create a HTTP Connection.
        String baseUrl = "https://api.openweathermap.org/data/2.5";
        String callAction = "/weather?q=";
        String city = "Raleigh";
        String apiKey = "e727adccfe6c9670b6ec4a5f77523915";
        String state = "US-NC";
        String urlString = baseUrl + callAction + city +","+ state + "&appid=" + apiKey;       
        URL url;
        try {
            // Make the connection.
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            // Examine the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.printf("Error: Unable to get weather data: " + status);
            } else {
                // Parsing input stream into a text string.
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();              
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                    
                }
                // Close the connections.
                in.close();
                con.disconnect();
                // Print out our complete JSON string.
                //System.out.println("Output: " + content.toString());
                // Parse that object into a usable Java JSON object.
                JSONObject obj = new JSONObject(content.toString());
                // Convert data into array
                String tempData = obj.getString("main");                           
                String[] dataAsArray = tempData.split(",");
                // get rid of superfluous charachters & cast into double                
                tempData = dataAsArray[0].substring(8);                
                double tempAsDouble = Double.parseDouble(tempData);
                tempAsDouble -= kelvinDifferential;
                // display results
                System.out.println("temp: " + String.valueOf(tempAsDouble)+ " celcius");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return;
        }}
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        getTemperature();
    }
    
}

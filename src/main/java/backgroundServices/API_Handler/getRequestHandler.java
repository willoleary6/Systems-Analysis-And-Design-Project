package backgroundServices.API_Handler;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import backgroundServices.resourceReader.reader;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Properties;

public class getRequestHandler {
    private reader myReader;
    private Properties apiProperties;
    private JSONObject apiResponse;

    public getRequestHandler(){
        myReader = new reader();
        apiProperties = myReader.readFromResources("APIs.properties");
    }

    /**
     * Method that retrieves a list of airports from AWS
     */
    public void getAllAirports(){
        try {
            HttpResponse<String> jsonResponse =
                    Unirest.get(apiProperties.getProperty("ApiUrl")+apiProperties.getProperty("getAllAirports"))
                            .asString();

            apiResponse =  new JSONObject(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that retrieves a list of flights from AWS
     */
    public void getAllFlights(){
        try {
            HttpResponse<String> jsonResponse =
                    Unirest.get(apiProperties.getProperty("ApiUrl")+apiProperties.getProperty("getAllFlights"))
                            .asString();

            apiResponse =  new JSONObject(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that retrieves a list of flights by the id number of their departing airport.
     * @param id integer number acting as a unique identifier
     */
    public void getFlightsByDepartureAirport(int id){
        try {
            HttpResponse<String> jsonResponse =
                    Unirest.get(apiProperties.getProperty("ApiUrl")+apiProperties.getProperty("getFlightsInfoByDepartureAirportID"))
                            .queryString("airportID", id)
                            .asString();

            apiResponse =  new JSONObject(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that retrieves any discounts applied to specified flight.
     * @param id integer number acting as a unique identifier for the flight
     */
    public void getDiscountsByFlightID(int id){
        try {
            HttpResponse<String> jsonResponse =
                    Unirest.get(apiProperties.getProperty("ApiUrl")+apiProperties.getProperty("getDiscountsByFlightID"))
                            .queryString("flightID", id)
                            .asString();

            apiResponse =  new JSONObject(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that retrieves a users information by using that user's id number.
     * @param id integer number acting as a unique identifier
     */
    public void getUserInformation(int id){
        try {
            HttpResponse<String> jsonResponse =
                    Unirest.get(apiProperties.getProperty("ApiUrl")+apiProperties.getProperty("getUserById"))
                            .queryString("id", id)
                            .asString();

            apiResponse =  new JSONObject(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that retrieves a users information by using that user's username.
     * Will only return user's username back if it exists (Use with registration)
     * @param username String containing user's username
     */
    public void getUserInformation(String username){
        try {
            HttpResponse<String> jsonResponse =
                    Unirest.get(apiProperties.getProperty("ApiUrl")+apiProperties.getProperty("getUserByUsername"))
                            .queryString("username", username)
                            .asString();

            apiResponse =  new JSONObject(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that retrieves a users information by using that user's username and password.
     * @param username String containing user's username
     * @param password String containing user's password
     */
    public void getUserInformation(String username, String password){
        try {
            HashMap queries = new HashMap<String, String>();
            queries.put("username", username);
            queries.put("password", password);
            HttpResponse<String> jsonResponse =
                    Unirest.get(apiProperties.getProperty("ApiUrl")+apiProperties.getProperty("getUserByUsernameAndPassword"))
                            .queryString(queries)
                            .asString();

            apiResponse =  new JSONObject(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method that formats the AWS response to the last query executed and returns a JSON object.
     * @return Returns an array of JSONObjects that contain the response from AWS.
     */
    public JSONObject [] getApiResponseResults(){
        // get the raw results data and remove any characters we cant play with.
        String results = apiResponse.get("results").toString()
                .replaceAll("\\[", "").replaceAll("\\]", "");
        /*
         Since we need to convert this string to an array we need to
         specify where to split the string with out causing "issues".
        */
        results = results.toString()
                .replaceAll("\\},", "}~,");

        String [] resultsArray = results.split("~,") ;
        // store results in array of JSONs
        JSONObject [] formatedDataFromAPI = new JSONObject[resultsArray.length];
        for (int i = 0; i < resultsArray.length; i++){
            formatedDataFromAPI[i] = new JSONObject(resultsArray[i]);
        }
        return formatedDataFromAPI;
    }

    /**
     * Method that formats and returns an array of keys to be used access the server response of the last query.
     * @return String array containing the keys to the last server response.
     */
    public String [] getApiResponseKeys(){
        // remove any problem characters and split it on the comma.
        String [] keys = apiResponse.get("keys").toString()
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\"", "").split(",");
        
        return keys;
    }
}

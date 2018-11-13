package backgroundServices.API_Handlers;


import backgroundServices.resourceReader.reader;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Properties;

public class getRequestHandler implements ApiRequestHandler{
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
                    Unirest.get(apiProperties.getProperty("getUrl")+apiProperties.getProperty("getAllAirports"))
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
                    Unirest.get(apiProperties.getProperty("getUrl")+apiProperties.getProperty("getAllFlights"))
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
                    Unirest.post(apiProperties.getProperty("getUrl")+apiProperties.getProperty("getFlightsInfoByDepartureAirportID"))
                            .header("accept", "application/json")
                            .body("" +
                                    "{" +
                                    "\"airportID\":\""+id+"\"" +
                                    "}"
                            )
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
                    Unirest.post(apiProperties.getProperty("getUrl")+apiProperties.getProperty("getDiscountsByFlightID"))
                            .header("accept", "application/json")
                            .body("" +
                                    "{" +
                                    "\"flightID\":\""+id+"\"" +
                                    "}"
                            )
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
                    Unirest.post(apiProperties.getProperty("getUrl")+apiProperties.getProperty("getUserById"))
                            .header("accept", "application/json")
                            .body("" +
                                    "{" +
                                    "\"id\":\""+id+"\"" +
                                    "}"
                            )
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
                    Unirest.post(apiProperties.getProperty("getUrl")+apiProperties.getProperty("getUserByUsername"))
                            .header("accept", "application/json")
                            .body("" +
                                    "{" +
                                    "\"username\":\""+username+"\"" +
                                    "}"
                            )
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
            HttpResponse<String> jsonResponse =
                    Unirest.post(apiProperties.getProperty("getUrl")+apiProperties.getProperty("getUserByUsernameAndPassword"))
                            .header("accept", "application/json")
                            .body("" +
                                    "{" +
                                    "\"username\":\""+username+"\"," +
                                    "\"password\":\""+password+"\"" +
                                    "}")
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
                .replaceAll(" ", "")
                .replaceAll("\"", "").split(",");
        
        return keys;
    }
}

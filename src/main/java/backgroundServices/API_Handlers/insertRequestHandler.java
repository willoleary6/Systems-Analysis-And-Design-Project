package backgroundServices.API_Handlers;

import backgroundServices.resourceReader.reader;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.util.Properties;

public class insertRequestHandler  implements ApiRequestHandler{
    private reader myReader;
    private Properties apiProperties;
    private JSONObject apiResponse;

    public insertRequestHandler(){
        myReader = new reader();
        apiProperties = myReader.readFromResources("APIs.properties");
    }


    public void addNewUser(String username, String email, String password){
        try {
            HttpResponse<String> jsonResponse =
                    Unirest.post(apiProperties.getProperty("insertUrl")+apiProperties.getProperty("addNewUser"))
                            .header("accept", "application/json")
                            .body("" +
                                    "{" +
                                    "\"username\":\""+username+"\"," +
                                    "\"email\":\""+email+"\"," +
                                    "\"password\":\""+password+"\"" +
                                    "}")
                            .asString();

            apiResponse =  new JSONObject(jsonResponse.getBody());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


    public void addNewDiscount(String userID, String flightID,String discountStartDate,
                               String discountEndDate, String discountPercentage){
        try {
            HttpResponse<String> jsonResponse =
                    Unirest.post(apiProperties.getProperty("insertUrl")+apiProperties.getProperty("addNewDiscount"))
                            .header("accept", "application/json")
                            .body("" +
                                    "{" +
                                    "\"userID\":\""+userID+"\"," +
                                    "\"flightID\":\""+flightID+"\"," +
                                    "\"discountStartDate\":\""+discountStartDate+"\"," +
                                    "\"discountEndDate\":\""+discountEndDate+"\"," +
                                    "\"discountPercentage\":\""+discountPercentage+"\"" +
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
            formatedDataFromAPI[i] = new JSONObject("{result:"+resultsArray[i]+"}");
        }
        return formatedDataFromAPI;
    }

    /**
     * Method that formats and returns an array of keys to be used access the server response of the last query.
     * @return String array containing the keys to the last server response.
     */
    public String [] getApiResponseKeys(){
        // remove any problem characters and split it on the comma.
        System.out.println(apiResponse);
        String [] keys = apiResponse.get("keys").toString()
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\"", "").split(",");

        return keys;
    }

}

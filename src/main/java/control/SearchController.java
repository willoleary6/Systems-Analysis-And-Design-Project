package control;

import backgroundServices.API_Handler.getRequestHandler;
import org.json.JSONObject;
import routeCalculation.flight;

public class SearchController {
    private getRequestHandler dbHandler;

    public SearchController(){
        dbHandler = new getRequestHandler();
    }

    public void routeCalcultion(int searchParams, String origin, String destination, String timestamp) {
        //connect to wills handler pull data pick relevant and pass
    }

    public void retrieveFlights(){
        dbHandler.getAllFlights();
        JSONObject []response = dbHandler.getApiResponseResults();
        String []keys = dbHandler.getApiResponseKeys();
        for(int i = 0; i < response.length;i++) {
            JSONObject obj = response[i];
            System.out.println(obj.get("departureTime"));
        }
    }

    public flight jobjToFLight(JSONObject jobj) {
        //do stuff
        return null;
    }
}

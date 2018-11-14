package control;

import backgroundServices.API_Handlers.getRequestHandler;
import org.json.JSONObject;
import org.json.JSONString;
import routecalculation.flight;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchController {
    private getRequestHandler dbHandler;
    private ArrayList<flight> flights;

    public SearchController(){
        dbHandler = new getRequestHandler();
    }

    public void routecalcultion(int searchParams, String origin, String destination, String timestamp) {
        //connect to wills handler pull data pick relevant and pass
    }

    public void retrieveFlights(){
        flights = new ArrayList<flight>();
        dbHandler.getAllFlights();
        JSONObject []response = dbHandler.getApiResponseResults();
        for(int i = 0; i < response.length;i++) {
            flights.add(jobjToFLight(response[i]));
        }
        for(int i = 0; i < flights.size(); i++){
            System.out.println(flights.get(i).getFlightnumber());
        }
    }

    public flight jobjToFLight(JSONObject jobj) {
        JSONObject depart = new JSONObject(jobj.getString("departureTime"));
        JSONObject arrive = new JSONObject(jobj.getString("arrivalTime"));
        return new flight(
                jobj.getInt("departureAirport"), jobj.getInt("destinationAirport")
                , jobj.getDouble("price"));

    }
}

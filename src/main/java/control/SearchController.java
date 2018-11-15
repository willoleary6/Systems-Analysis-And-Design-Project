package control;

import backgroundServices.API_Handlers.getRequestHandler;
import org.json.JSONObject;
import routeCalculation.Flight;

import java.util.ArrayList;

public class SearchController {
    private getRequestHandler dbHandler;
    private ArrayList<Flight> Flights;

    public SearchController(){
        dbHandler = new getRequestHandler();
    }

    public void routeCalcultion(int searchParams, String origin, String destination, String timestamp) {
        //connect to wills handler pull data pick relevant and pass
    }

    public void retrieveFlightsFromDb(){
        Flights = new ArrayList<Flight>();
        dbHandler.getAllFlights();
        JSONObject []response = dbHandler.getApiResponseResults();
        for(int i = 0; i < response.length;i++) {
            Flights.add(jobjToFLight(response[i]));
        }
        for(int i = 0; i < Flights.size(); i++){
            System.out.println(Flights.get(i).getFlightnumber());
        }
    }

    public Flight jobjToFLight(JSONObject jobj) {
        JSONObject depart = new JSONObject(jobj.getString("departureTime"));
        JSONObject arrive = new JSONObject(jobj.getString("arrivalTime"));
        return new Flight(jobj.getString("flightNumber"),
                jobj.getInt("departureAirport"), jobj.getInt("destinationAirport"),
                depart.getString("time"), arrive.getString("time"), depart.getString("day"),
                arrive.getString("day"), jobj.getDouble("price"),
                jobj.getInt("airlineID"));

    }
}

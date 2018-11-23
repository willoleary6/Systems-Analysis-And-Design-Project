package control;

import backgroundServices.API_Handlers.getRequestHandler;
import org.json.JSONObject;
import routeCalculation.Airport;
import routeCalculation.Flight;
import routeCalculation.Grapher;
import routeCalculation.Route;
//import routeCalculation.Grapher;

import java.util.ArrayList;
import java.util.List;

public class SearchController {
    private getRequestHandler dbHandler;
    private ArrayList<Airport> airports = new ArrayList<Airport>();

    public SearchController(){
        dbHandler = new getRequestHandler();
    }

    public void routeCalculation() {
        Grapher g = new Grapher();
        ArrayList<Route> routeToDestination = g.startCalculation(airports.get(0), airports.get(3), 0, airports);
        for(int i =0; i< routeToDestination.size(); i++){
            System.out.println(routeToDestination.get(i).getOrigin()
                    +" --- "+routeToDestination.get(i).getDest()
                    +"  Flight: "+routeToDestination.get(i).getFlight().getFlightNumber()
                    +"  Cost: "+routeToDestination.get(i).getFlight().getCost()
            );
        }
    }

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public void retrieveAirports(){
        dbHandler.getAllAirports();
        JSONObject[] response = dbHandler.getApiResponseResults();

        for(int i = 0; i < response.length;i++) {
            airports.add(jsonObjectToAirport(response[i]));
            airports.get(i).setFlightsDeparting(retrieveFlights(airports.get(i).getAutoKey()));
            //System.out.println("------------------------");
            //System.out.println(airports.get(i).getAirportName());
            for(int j =0; j < airports.get(i).getFlights().size(); j++){
                //System.out.println(airports.get(i).getFlights().get(j).getSourceAirport() + "   :    "+airports.get(i).getFlights().get(j).getTargetAirport()+"   :   "+airports.get(i).getFlights().get(j).getCost());
            }
        }

    }

    public ArrayList<Flight> retrieveFlights(int departureAirportID){
        dbHandler.getFlightsByDepartureAirport(departureAirportID);
        ArrayList<Flight> flights = new ArrayList<Flight>();
        JSONObject[] response = dbHandler.getApiResponseResults();
        for(int i = 0; i < response.length;i++) {
            flights.add(jsonObjectToFlight(response[i]));
        }
        /*
        Flights = new ArrayList<Flight>();
        dbHandler.getAllFlights();
        JSONObject[] response = dbHandler.getApiResponseResults();
        for(int i = 0; i < response.length;i++) {
            Flights.add(jsonObjectToFlight(response[i]));
        }
        for(int i = 0; i < Flights.size(); i++){
            //System.out.println(Flights.get(i).getDepartureAirportIndex());
        }
        */
        return flights;
    }

    public Flight jsonObjectToFlight(JSONObject jsonObject) {
        JSONObject depart = new JSONObject(jsonObject.getString("departureTime"));
        JSONObject arrive = new JSONObject(jsonObject.getString("arrivalTime"));

        return new Flight(
                jsonObject.getInt("departureAirport"),
                jsonObject.getInt("destinationAirport"),
                jsonObject.getInt("airlineID"),
                jsonObject.getString("flightNumber"),
                depart.getString("time"), arrive.getString("time"), depart.getString("day"),
                arrive.getString("day"),
                jsonObject.getInt("price")
        );

        /*return  new ApplyDiscount(double discount, new Flight(
                jsonObject.getInt("departureAirport"),
                jsonObject.getInt("destinationAirport"),
                jsonObject.getInt("airlineID"),
                jsonObject.getString("flightNumber"),
                depart.getString("time"), arrive.getString("time"), depart.getString("day"),
                arrive.getString("day"),
                jsonObject.getInt("price")
        ));*/


    }
    public Airport jsonObjectToAirport(JSONObject jsonObject) {
        return new Airport(jsonObject.getInt("autoID"),jsonObject.getString("name"));
    }
}
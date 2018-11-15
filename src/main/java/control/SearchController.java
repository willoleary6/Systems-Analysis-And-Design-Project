package control;

import backgroundServices.API_Handlers.getRequestHandler;
import org.json.JSONObject;
import routeCalculation.Airport;
import routeCalculation.Flight;
import routeCalculation.Grapher;
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
        /*
        Flight[] Flights = {
                new Flight(0, 2, 1),
                new Flight(0, 3, 4),
                new Flight(0, 4, 2),
                new Flight(0, 1, 3),
                new Flight(1, 3, 2),
                new Flight(1, 4, 3),
                new Flight(1, 5, 1),
                new Flight(2, 4, 1),
                new Flight(3, 5, 4),
                new Flight(4, 5, 2),
                new Flight(4, 6, 7),
                new Flight(4, 7, 2),
                new Flight(5, 6, 4),
                new Flight(6, 7, 5)
        };
        */
        /*
        // TODO fix me bitch!
        Flight [] flightsArray = new Flight[Flights.size()];
        for (int i = 0; i < flightsArray.length; i++){
            flightsArray[i] = Flights.get(i);
            System.out.println("---------------------------------");
            System.out.println(": "+flightsArray[i].getDepartureAirportIndex());
            System.out.println(": "+flightsArray[i].getDestinationAirportIndex());
            System.out.println(": "+flightsArray[i].getCost());
            System.out.println("---------------------------------");

        }*/
        Grapher g = new Grapher();
        g.computePaths(airports.get(0), airports); // run test.Dijkstra
        System.out.println("Distance to " + airports.get(3).getAirportName() + ": " + airports.get(3).getMinimumDistance());
        List<Airport> path = g.getShortestPathTo(airports.get(3));
        System.out.print("Path: ");
        for(int i = 0; i < path.size(); i++){
            System.out.print(path.get(i).getAirportName()+" - ");
        }



        int source = 1;
        // Grapher g = new Grapher(flightsArray);
        // g.calculateShortestDistance(source);
        //g.printResult(); // let's try it !
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
                depart,
                arrive,
                jsonObject.getInt("price")
        );

    }
    public Airport jsonObjectToAirport(JSONObject jsonObject) {
        return new Airport(jsonObject.getInt("autoID"),jsonObject.getString("name"));
    }
}
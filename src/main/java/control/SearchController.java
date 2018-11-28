package control;

import backgroundServices.API_Handlers.getRequestHandler;
import org.json.JSONObject;
import routeCalculation.*;
//import routeCalculation.CostGrapher;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;

public class SearchController {
    private getRequestHandler dbHandler;
    private ArrayList<Airport> airports = new ArrayList<Airport>();
    private Grapher shortestPath;

    private SearchController(Grapher shortestpath){
        this.shortestPath = shortestpath;
    }

    public SearchController(){
        dbHandler = new getRequestHandler();
    }

    public void routeCalculation(int searchType) {
        Date input = new Date();
        shortestPath.startCalculation(airports.get(2), airports.get(8), airports);
        /*
        if(searchType == 0) {
            CostGrapher g = new CostGrapher();
            ArrayList<Route> routeToDestination =  g.startCalculation(airports.get(2), airports.get(8), airports);
            for (int i = 0; i < routeToDestination.size(); i++) {
                System.out.println(routeToDestination.get(i).getOrigin()
                        + " --- " + routeToDestination.get(i).getDestination()
                        + "  Flight: " + routeToDestination.get(i).getFlight().getFlightNumber()
                        + "  Cost: " + routeToDestination.get(i).getFlight().getWeight()
                        + "  Departing: " + convertFlightTimeToDate(routeToDestination.get(i).getFlight().getDepartDay(), routeToDestination.get(i).getFlight().getDepartTime(),input)
                        + "  Arriving: " + convertFlightTimeToDate(routeToDestination.get(i).getFlight().getArriveDay(), routeToDestination.get(i).getFlight().getArriveTime(),input)
                );
                input = convertFlightTimeToDate(routeToDestination.get(i).getFlight().getDepartDay(), routeToDestination.get(i).getFlight().getDepartTime(),input);
            }

        }else{

            TimeGrapher g = new TimeGrapher();
            ArrayList<Route> routeToDestination = g.startCalculation(airports.get(2), airports.get(8),  airports);

            for (int i = 0; i < routeToDestination.size(); i++) {
                System.out.println(routeToDestination.get(i).getOrigin()
                        + " --- " + routeToDestination.get(i).getDestination()
                        + "  Flight:\t" + routeToDestination.get(i).getFlight().getFlightNumber()
                       // + "  Cost: " + routeToDestination.get(i).getFlight().getWeight()
                        + "  Departing:\t" + convertFlightTimeToDate(routeToDestination.get(i).getFlight().getDepartDay(), routeToDestination.get(i).getFlight().getDepartTime(), input)
                        + "  Arriving: " + convertFlightTimeToDate(routeToDestination.get(i).getFlight().getArriveDay(), routeToDestination.get(i).getFlight().getArriveTime(),input)

                );
                input = convertFlightTimeToDate(routeToDestination.get(i).getFlight().getDepartDay(), routeToDestination.get(i).getFlight().getDepartTime(),input);
            }


        }*/

    }

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    public Date convertFlightTimeToDate(String day, String hour, Date input){
        int minutes = 60*1000;
        LocalDate ld = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        ld = ld.with(TemporalAdjusters.next(DayOfWeek.valueOf(day.toUpperCase())));
        Date date = Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int minutesPassedInDay = convertStringToMinutes(hour);
        return new Date(date.getTime() + minutesPassedInDay * minutes);
    }
    private int convertStringToMinutes(String stringTime){
        String [] hoursAndMinutes = stringTime.split(":");
        int hoursToMinutes = Integer.parseInt(hoursAndMinutes[0])*60;
        int minutes = Integer.parseInt(hoursAndMinutes[1]);
        return hoursToMinutes+minutes;
    }

    public void retrieveAirports(){
        dbHandler.getAllAirports();
        JSONObject[] response = dbHandler.getApiResponseResults();

        for(int i = 0; i < response.length;i++) {
            airports.add(jsonObjectToAirport(response[i]));
            airports.get(i).setFlightsDeparting(retrieveFlights(airports.get(i).getAutoKey()));
            //System.out.println("------------------------");
            //System.out.println(airports.get(i).getAirportName());
            for(int j = 0; j < airports.get(i).getEdges().size(); j++){
                //System.out.println(airports.get(i).getEdges().get(j).getSourceAirport() + "   :    "+airports.get(i).getEdges().get(j).getTargetAirport()+"   :   "+airports.get(i).getEdges().get(j).getWeight());
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
                jsonObject.getInt("autoID"),
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
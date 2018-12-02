package control;

import backgroundServices.API_Handlers.getRequestHandler;
import org.json.JSONObject;
import routeCalculation.*;
//import routeCalculation.CostGrapher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SearchController {
    private getRequestHandler dbHandler;
    private ArrayList<Airport> airports = new ArrayList<Airport>();
    private Grapher shortestPath;

    public SearchController(){
        dbHandler = new getRequestHandler();
        retrieveAirports();
    }

    public ArrayList<Route> searchForFlight(Airport departure, Airport destination, Date departureDate, boolean costBased) {
        int searchType = costBased ? 0 : 1;
        Grapher g = new Grapher(searchType);
        g.startCalculation(departure, airports, departureDate);
        ArrayList<Route> routeToDestination =  g.calculateTraceBack(destination);
        return routeToDestination;
    }

    public void routeCalculation(int searchType) {
        Date input = new Date();
        //shortestPath.startCalculation(airports.get(2), airports.get(8), airports);
        Grapher g = new Grapher(searchType);
        g.startCalculation(airports.get(2), airports, new Date());
        ArrayList<Route> routeToDestination =  g.calculateTraceBack(airports.get(8));
        for (int i = 0; i < routeToDestination.size(); i++) {
            Flight flightOnThisRoute = routeToDestination.get(i).getFlightDecorator().getFlight();
            System.out.println(routeToDestination.get(i).getOrigin()
                        + " --- " + routeToDestination.get(i).getDestination()
                        + "  Flight: " + flightOnThisRoute.getFlightNumber()
                        + "  Cost: " + routeToDestination.get(i).getCost()
                        + "  Departing: " + convertFlightTimeToDate(flightOnThisRoute.getDepartDay(), flightOnThisRoute.getDepartTime(),input)
                        + "  Arriving: " + convertFlightTimeToDate(flightOnThisRoute.getArriveDay(), flightOnThisRoute.getArriveTime(),input)
                );
                input = convertFlightTimeToDate(flightOnThisRoute.getDepartDay(), flightOnThisRoute.getDepartTime(),input);
            }
    }

    public ArrayList<Airport> getAirports() {
        return airports;
    }

    private Date convertFlightTimeToDate(String day, String hour, Date input){
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
        airports.clear();
        for(int i = 0; i < response.length;i++) {
            airports.add(jsonObjectToAirport(response[i]));
            airports.get(i).setFlightsDeparting(retrieveFlights(airports.get(i).getAutoKey()));
        }

    }

    private ArrayList<FlightDiscountDecorator> retrieveFlights(int departureAirportID){
        dbHandler.getFlightsByDepartureAirport(departureAirportID);
        ArrayList<FlightDiscountDecorator> flights = new ArrayList<FlightDiscountDecorator>();
        JSONObject[] response = dbHandler.getApiResponseResults();
        for(int i = 0; i < response.length;i++) {
            flights.add(jsonObjectToFlight(response[i]));
        }
        return flights;
    }

    private FlightDiscountDecorator jsonObjectToFlight(JSONObject jsonObject) {
        JSONObject depart = new JSONObject(jsonObject.getString("departureTime"));
        JSONObject arrive = new JSONObject(jsonObject.getString("arrivalTime"));
        return new FlightDiscountDecorator( new Flight(
                jsonObject.getInt("autoID"),
                jsonObject.getInt("departureAirport"),
                jsonObject.getInt("destinationAirport"),
                jsonObject.getInt("airlineID"),
                jsonObject.getString("flightNumber"),
                depart.getString("time"), arrive.getString("time"), depart.getString("day"),
                arrive.getString("day"),
                jsonObject.getInt("price")
        ), retrieveDiscountByFlightID(jsonObject.getInt("autoID")));

    }

    private double retrieveDiscountByFlightID(int flightID){
        dbHandler.getDiscountsByFlightID(flightID);
        try {
            JSONObject[] response = dbHandler.getApiResponseResults();
            Double percentageDiscount = 0.0;
            Date now = new Date();
            Date startOfDiscount;
            Date endOfDiscount;
            DateFormat format = new SimpleDateFormat("YYYY-mm-dd HH:mm:ss", Locale.ENGLISH);
            for(int i = 0; i < response.length;i++){
                startOfDiscount = format.parse(response[i].getString("discountStartDate"));
                endOfDiscount = format.parse(response[i].getString("discountEndDate"));
                if(startOfDiscount.getTime() < now.getTime() && endOfDiscount.getTime() > now.getTime()){
                    percentageDiscount+= response[i].getDouble("discountPercentage");
                }
            }
            return percentageDiscount;
        }catch (Exception e){
            return 0;
        }


    }

    private Airport jsonObjectToAirport(JSONObject jsonObject) {
        return new Airport(jsonObject.getInt("autoID"),jsonObject.getString("name"));
    }
}
package routeCalculation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class TimeGrapher {
    private ArrayList<Route> routes = new ArrayList<Route>();
    public void computePaths(Airport source, ArrayList<Airport> listOfAirports) {
        //calculateTimeToTraverseFlight((Flight) listOfAirports.get(0).getEdges().get(0));
        //convertFlightTimeToDate((Flight) listOfAirports.get(0).getEdges().get(0));
        source.setMinimumDistance(0.);
        PriorityQueue<Airport> vertexQueue = new PriorityQueue<Airport>();
        vertexQueue.add(source);
        Route mostEfficientRouteToNextNode;
        Date sourceDate = new Date();
        while (!vertexQueue.isEmpty()) {
            Airport currentAirport = vertexQueue.poll();
            mostEfficientRouteToNextNode = new Route();
            for (Edge currentEdge :  currentAirport.getEdges()) {
                Flight currentFlight = (Flight)currentEdge;
                Date targetDate = convertFlightTimeToDate(currentFlight.getArriveDay(),currentFlight.getArriveTime());
                double costOfCurrentFlight, costThroughCurrentAirport;

                Airport targetAirport = getAirportByID(currentFlight.getTarget(), listOfAirports);
                costOfCurrentFlight = targetDate.getTime() - sourceDate.getTime();
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;

                if (costThroughCurrentAirport < targetAirport.getMinimumDistance()) {
                    vertexQueue.remove(costThroughCurrentAirport);
                    mostEfficientRouteToNextNode.setOrigin(currentAirport);
                    mostEfficientRouteToNextNode.setDest(targetAirport);
                    mostEfficientRouteToNextNode.setFlight(currentFlight);
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    vertexQueue.add(targetAirport);
                    sourceDate = convertFlightTimeToDate(currentFlight.getDepartDay(),currentFlight.getDepartTime());
                }
            }
            routes.add(mostEfficientRouteToNextNode);
        }
    }

    public int calculateTimeToTraverseFlight(Flight currentFlight){

        //System.out.println(newDate);
        //currentFlight.getDepartTime();
        //.//dt.plusHours(6);
        //System.out.println(currentFlight.getArriveDay()+":"+currentFlight.getArriveTime());
        return 0;
    }

    public Date convertFlightTimeToDate(String day, String hour){
        int minutes = 60*1000;
        LocalDate ld = LocalDate.now();
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

    public Route getRouteWithSpecifiedDestination(Airport target) {
        for(int i = 0; i < routes.size(); i++){
            if(routes.get(i).getDest() == target){
                return routes.get(i);
            }
        }
        return null;
    }

    public ArrayList<Route> startCalculation(Airport start, Airport destination, int methodOfCalculation, ArrayList<Airport> airports) {
        computePaths(start, airports); // run test.Dijkstra

        Route traceBack = getRouteWithSpecifiedDestination(destination);
        ArrayList<Route> routeToDestination = new ArrayList<Route>();
        while(traceBack.getOrigin() != start){
            traceBack = getRouteWithSpecifiedDestination(destination);
            destination = traceBack.getOrigin();
            routeToDestination.add(traceBack);
        }
        Collections.reverse(routeToDestination);

        return routeToDestination;
    }

    public Airport getAirportByID(int airportID, ArrayList<Airport> listOfAirports) {
        for (int i = 0; i < listOfAirports.size(); i++) {
            if (airportID == listOfAirports.get(i).getAutoKey())
                return listOfAirports.get(i);
        }
        return listOfAirports.get(0);
    }
}
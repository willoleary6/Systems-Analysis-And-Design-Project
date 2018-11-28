package routeCalculation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.PriorityQueue;

public class TimeGrapherState implements GrapherState {
    private ArrayList<Route> routes = new ArrayList<Route>();

    public TimeGrapherState(){
        routes = new ArrayList<Route>();
    }
    
    @Override
    public ArrayList<Route> startCalculation(Airport start, Airport destination, ArrayList<Airport> airports) {
        computeShortestRouteToEveryAirport(start, airports);
        Route traceBack;
        ArrayList<Route> routeToDestination = new ArrayList<Route>();
        do{
            traceBack = getRouteWithSpecifiedDestination(destination);
            destination = traceBack.getOrigin();
            routeToDestination.add(traceBack);
        }while(traceBack.getOrigin() != start);
        Collections.reverse(routeToDestination);
        return routeToDestination;
    }



    private void computeShortestRouteToEveryAirport(Airport sourceAirport, ArrayList<Airport> listOfAirports) {
        sourceAirport.setMinimumDistance(0.);
        PriorityQueue<Airport> airportQueue = new PriorityQueue<Airport>();
        ArrayList<Airport> visitedAirports = new ArrayList<Airport>();
        // start with our departure airport
        airportQueue.add(sourceAirport);
        Date sourceDate = new Date();
        while (!airportQueue.isEmpty()) {
            Airport currentAirport = airportQueue.poll();
            // run through each of the flights leaving this airport.
            Date targetDate = new Date();
            for (Edge currentEdge :  currentAirport.getEdges()) {
                Flight currentFlight = (Flight)currentEdge;
                double costOfCurrentFlight, costThroughCurrentAirport;
                // find target airport through the flight destination
                Airport targetAirport = getAirportByID(currentFlight.getTarget(), listOfAirports);
                // Date and time of arrival date for current flight
                targetDate = convertFlightTimeToDate(currentFlight.getArriveDay(), currentFlight.getArriveTime());
                //Date sourceDate = convertFlightTimeToDate(currentFlight.getDepartDay(), currentFlight.getDepartTime());
                //System.out.println(targetDate);
                costOfCurrentFlight = targetDate.getTime() - sourceDate.getTime();
                // calculate the cost of getting to this next node
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;
                //System.out.println(sourceDate+"----"+ targetDate);
                if (costThroughCurrentAirport < targetAirport.getMinimumDistance() && (!visitedAirports.contains(targetAirport))) {
                    // seems like a valid route, lets add it to our list
                    //System.out.println(sourceDate);
                    routes.add(new Route(currentAirport, targetAirport, currentFlight, costOfCurrentFlight));
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    airportQueue.add(targetAirport);
                    visitedAirports.add(targetAirport);
                    sourceDate =  convertFlightTimeToDate(currentFlight.getDepartDay(), currentFlight.getDepartTime());
                    eliminateDuplicateTargets();
                    //break;
                    //System.out.print("");
                }
            }
            //sourceDate = targetDate;


        }
    }

    private void eliminateDuplicateTargets() {
        ArrayList<Route> listOfAirports = new ArrayList<Route>();
        Route routeToRemove = null;
        Route matchedWith = null;
        for(Route currentRoute : routes){
            matchedWith = checkForDuplicate(listOfAirports, currentRoute.getDestination());
            if(matchedWith == null){
                listOfAirports.add(currentRoute);
            }else{
                routeToRemove = currentRoute;
                break;
            }
        }
        if(routeToRemove != null){
            if(calculateCostOfTraceBack(matchedWith) <= calculateCostOfTraceBack(routeToRemove)){
                routes.remove(matchedWith);
            }else {
                routes.remove(routeToRemove);
            }
            eliminateDuplicateTargets();
        }
    }

    private double calculateCostOfTraceBack(Route routeToTraceBack){
        double summaryCost = 0;
        Airport originAirport = routeToTraceBack.getOrigin();
        while (getRouteWithSpecifiedDestination(originAirport) != null) {
            summaryCost += routeToTraceBack.getCost();
            routeToTraceBack = getRouteWithSpecifiedDestination(originAirport);
            originAirport = routeToTraceBack.getOrigin();
        }
        return summaryCost;
    }

    private Route checkForDuplicate(ArrayList<Route> listOfAirports, Airport currentAirport){
        for(Route airportCheck : listOfAirports){
            if(airportCheck.getDestination() == currentAirport){
                return airportCheck;
            }
        }
        return null;
    }

    private Route getRouteWithSpecifiedDestination(Airport target) {
        for(int i = 0; i < routes.size(); i++){
            if(routes.get(i).getDestination().getAirportName().equals(target.getAirportName())){
                return routes.get(i);
            }
        }
        return null;
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

    private Airport getAirportByID(int airportID, ArrayList<Airport> listOfAirports) {
        Airport toReturn = null;
        for (int i = 0; i < listOfAirports.size(); i++) {
            if (airportID == listOfAirports.get(i).getAutoKey())
                toReturn = listOfAirports.get(i);
        }
        return toReturn;
    }

}

package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class MoneyGrapherState implements GrapherState {
    public MoneyGrapherState(){
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

    private ArrayList<Route> routes;


    private void computeShortestRouteToEveryAirport(Airport sourceAirport, ArrayList<Airport> listOfAirports) {
        sourceAirport.setMinimumDistance(0.);
        PriorityQueue<Airport> airportQueue = new PriorityQueue<Airport>();
        // start with our departure airport
        airportQueue.add(sourceAirport);
        while (!airportQueue.isEmpty()) {
            Airport currentAirport = airportQueue.poll();
            // run through each of the flights leaving this airport.
            for (Edge currentFlight :  currentAirport.getEdges()) {
                double costOfCurrentFlight, costThroughCurrentAirport;
                // find target airport through the flight destination
                Airport targetAirport = getAirportByID(currentFlight.getTarget(), listOfAirports);

                costOfCurrentFlight = currentFlight.getWeight();
                // calculate the cost of getting to this next node
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;
                if (costThroughCurrentAirport < targetAirport.getMinimumDistance()) {
                    // seems like a valid route, lets add it to our list
                    routes.add(new Route(currentAirport, targetAirport, currentFlight, costOfCurrentFlight));
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    airportQueue.add(targetAirport);
                    eliminateDuplicateTargets();
                }
            }
        }
    }

    private void eliminateDuplicateTargets(){
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
            if(routes.get(i).getDestination().getAirportName() == target.getAirportName()){
                return routes.get(i);
            }
        }
        return null;
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

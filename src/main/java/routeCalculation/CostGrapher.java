package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class CostGrapher {
    private ArrayList<Route> routes = new ArrayList<Route>();
    public void computePaths(Airport source, ArrayList<Airport> listOfAirports) {
        source.setMinimumDistance(0.);
        PriorityQueue<Airport> vertexQueue = new PriorityQueue<Airport>();
        vertexQueue.add(source);
        while (!vertexQueue.isEmpty()) {
            Airport currentAirport = vertexQueue.poll();
            for (Edge currentFlight :  currentAirport.getEdges()) {
                Route mostEfficientRouteToNextNode = new Route();
                double costOfCurrentFlight, costThroughCurrentAirport;
                Airport targetAirport = getAirportByID(currentFlight.getTarget(), listOfAirports);
                Airport originAirport = getAirportByID(currentFlight.getOrigin(), listOfAirports);
                //System.out.println(originAirport+"     "+targetAirport+"   "+currentFlight.getWeight());

                costOfCurrentFlight = currentFlight.getWeight();
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;
                if (costThroughCurrentAirport < targetAirport.getMinimumDistance()) {
                    vertexQueue.remove(costThroughCurrentAirport);
                    mostEfficientRouteToNextNode.setOrigin(currentAirport);
                    mostEfficientRouteToNextNode.setDest(targetAirport);
                    mostEfficientRouteToNextNode.setFlight(currentFlight);
                    mostEfficientRouteToNextNode.setCost(costOfCurrentFlight);
                    vertexQueue.add(targetAirport);
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    routes.add(mostEfficientRouteToNextNode);
                    eliminateDuplicateTargets();
                }
            }
        }
    }

    private void eliminateDuplicateTargets(){
        ArrayList<Route> listOfAirports = new ArrayList<Route>();
        Route routeToRemove = null;
        Route matchedWith = null;
        //Route matchedWith = null;
        for(Route currentRoute : routes){
            matchedWith = checkForDuplicate(listOfAirports, currentRoute.getDest());
            if(matchedWith == null){
                listOfAirports.add(currentRoute);
            }else{
                routeToRemove = currentRoute;
                break;
            }
        }

        if(routeToRemove != null){
            //System.out.println(matchedWith.getDest()+"  :  "+matchedWith.getCost()+" -  "+routeToRemove.getDest()+"  :  "+routeToRemove.getCost());

            if(calculateCostOfTraceback(matchedWith) <= calculateCostOfTraceback(routeToRemove)){
                routes.remove(matchedWith);
            }else {
                routes.remove(routeToRemove);
            }
            //System.out.println(calculateCostOfTraceback(routeToRemove));

            //routes.remove(routeToRemove);
            eliminateDuplicateTargets();
        }

    }

    private double calculateCostOfTraceback(Route routeToTraceBack){
        double summaryCost = 0;
        Airport origin = routeToTraceBack.getOrigin();
        //System.out.println("================");
        while (getRouteWithSpecifiedDestination(origin) != null) {
            //System.out.println(routeToTraceBack.getOrigin() +"   "+ routeToTraceBack.getDest());
            summaryCost += routeToTraceBack.getCost();
            routeToTraceBack = getRouteWithSpecifiedDestination(origin);
            origin = routeToTraceBack.getOrigin();
        }
        return summaryCost;
    }

    private Route checkForDuplicate( ArrayList<Route> listOfAirports, Airport currentAirport){
        Route found = null;
        for(Route airportCheck : listOfAirports){
            if(airportCheck.getDest() == currentAirport){
                found = airportCheck;
            }
        }
        return found;
    }

    public Route getRouteWithSpecifiedDestination(Airport target) {
        for(int i = 0; i < routes.size(); i++){
            if(routes.get(i).getDest().getAirportName() == target.getAirportName()){
               return routes.get(i);
           }
        }
        return null;
    }

    public ArrayList<Route> startCalculation(Airport start, Airport destination, int methodOfCalculation, ArrayList<Airport> airports) {
        computePaths(start, airports); // run test.Dijkstra
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

    public Airport getAirportByID(int airportID, ArrayList<Airport> listOfAirports) {
        Airport toReturn = null;
        for (int i = 0; i < listOfAirports.size(); i++) {
            if (airportID == listOfAirports.get(i).getAutoKey())
                toReturn = listOfAirports.get(i);
        }
        return toReturn;
    }
}
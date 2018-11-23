package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Grapher {
    private ArrayList<Route> routes = new ArrayList<Route>();
    public void computePaths(Airport source, ArrayList<Airport> listOfAirports) {
        source.setMinimumDistance(0.);
        PriorityQueue<Airport> vertexQueue = new PriorityQueue<Airport>();
        vertexQueue.add(source);
        Route mostEfficientRouteToNextNode;
        while (!vertexQueue.isEmpty()) {
            Airport currentAirport = vertexQueue.poll();
            mostEfficientRouteToNextNode = new Route();
            for (Flight currentFlight : currentAirport.getFlights()) {
                double costOfCurrentFlight, costThroughCurrentAirport;

                Airport targetAirport = getAirportByID(currentFlight.getTargetAirportID(), listOfAirports);
                costOfCurrentFlight = currentFlight.getCost();
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;

                if (costThroughCurrentAirport < targetAirport.getMinimumDistance()) {
                    vertexQueue.remove(costThroughCurrentAirport);
                    mostEfficientRouteToNextNode.setOrigin(currentAirport);
                    mostEfficientRouteToNextNode.setDest(targetAirport);
                    mostEfficientRouteToNextNode.setFlight(currentFlight);
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    vertexQueue.add(targetAirport);
                }
            }
            routes.add(mostEfficientRouteToNextNode);

        }
    }

    public Route getRouteWithSpecifiedDestination(Airport target) {
        for(int i = 0; i < routes.size(); i++){
           if(routes.get(i).getDest() == target){
               return routes.get(i);
           }
        }
        return null;
    }

    public ArrayList<Route> startCalculation(Airport start, Airport destination, int methodOfCalc, ArrayList<Airport> airports) {
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
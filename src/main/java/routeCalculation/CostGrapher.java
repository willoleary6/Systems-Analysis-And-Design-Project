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
        Route mostEfficientRouteToNextNode;
        while (!vertexQueue.isEmpty()) {
            Airport currentAirport = vertexQueue.poll();
            mostEfficientRouteToNextNode = new Route();
            for (Edge currentFlight :  currentAirport.getEdges()) {
                double costOfCurrentFlight, costThroughCurrentAirport;

                Airport targetAirport = getAirportByID(currentFlight.getTarget(), listOfAirports);
                costOfCurrentFlight = currentFlight.getWeight();
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;
                if (costThroughCurrentAirport < targetAirport.getMinimumDistance()) {
                    vertexQueue.remove(costThroughCurrentAirport);
                    System.out.println(currentAirport);
                    mostEfficientRouteToNextNode.setOrigin(currentAirport);
                    mostEfficientRouteToNextNode.setDest(targetAirport);
                    mostEfficientRouteToNextNode.setFlight(currentFlight);
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    vertexQueue.add(targetAirport);
                }
            }
            //System.out.println(mostEfficientRouteToNextNode.getOrigin());
            routes.add(mostEfficientRouteToNextNode);
        }
    }

    public Route getRouteWithSpecifiedDestination(Airport target) {
        //System.out.println(routes.size());
        for(int i = 0; i < routes.size(); i++){
            //System.out.println(routes.get(i).getOrigin());
           if(routes.get(i).getDest() == target){
               return routes.get(i);
           }
        }
        return null;
    }

    public ArrayList<Route> startCalculation(Airport start, Airport destination, int methodOfCalculation, ArrayList<Airport> airports) {
        computePaths(start, airports); // run test.Dijkstra
        /*
        Route traceBack = getRouteWithSpecifiedDestination(destination);
        //System.out.println(traceBack);
        ArrayList<Route> routeToDestination = new ArrayList<Route>();
        while(traceBack.getOrigin() != start){
            traceBack = getRouteWithSpecifiedDestination(destination);
            //System.out.println(traceBack.getOrigin().getAirportName());
            destination = traceBack.getOrigin();
            routeToDestination.add(traceBack);
        }
        Collections.reverse(routeToDestination);
        */
        return null;
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
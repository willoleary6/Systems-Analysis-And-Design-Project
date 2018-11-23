package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Grapher {
    private Route routeToDestination;
    private ArrayList<Route> routes = new ArrayList<Route>();
    public void computePaths(Airport source, ArrayList<Airport> listOfAirports) {
        source.setMinimumDistance(0.);
        PriorityQueue<Airport> vertexQueue = new PriorityQueue<Airport>();
        vertexQueue.add(source);
        routeToDestination = new Route();
        while (!vertexQueue.isEmpty()) {

            routeToDestination.setOrigin(source);
            Airport currentAirport = vertexQueue.poll();

            for (Flight currentFlight : currentAirport.getFlights()) {
                double costOfCurrentFlight, costThroughCurrentAirport;

                Airport targetAirport = getAirportByID(currentFlight.getTargetAirportID(), listOfAirports);
                costOfCurrentFlight = currentFlight.getCost();
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;

                if (costThroughCurrentAirport < targetAirport.getMinimumDistance()) {
                    vertexQueue.remove(costThroughCurrentAirport);
                    routeToDestination.addToVisitedAirports(currentAirport);
                    routeToDestination.addFlight(currentFlight);
                    routeToDestination.addToCost(costOfCurrentFlight);
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    targetAirport.setPrevious(currentAirport);
                    vertexQueue.add(targetAirport);
                }
            }
            routes.add(routeToDestination);

        }
    }

    public List<Airport> getShortestPathTo(Airport target) {
        for(int i = 0; i < routes.size(); i++){
           if(routes.get(i).getLastAirportVisited() == target){
              routeToDestination = routes.get(i);
           }
        }

        List<Airport> path = new ArrayList<Airport>();
        //routeToDestination.addToVisitedAirports(currentAirport);
        //routeToDestination.addFlight(currentFlight);
        //routeToDestination.addToCost(costOfCurrentFlight);
        for (Airport vertex = target; vertex != null; vertex = vertex.getPrevious()){
            path.add(vertex);

        }


        Collections.reverse(path);
        return path;
    }

    public Route startCalculation(Airport start, Airport destination, int methodOfCalc, ArrayList<Airport> airports) {
        //starts off all calulations need to return a route
        // route should contain arraylist of flights dest airport , start airport, cost and time
        computePaths(start, airports); // run test.Dijkstra
        //System.out.println("Cost to " + destination.getAirportName() + ": " + destination.getMinimumDistance());
        List<Airport> path = getShortestPathTo(destination);
        //System.out.print("Path: ");
        for(int i = 0; i < path.size(); i++){
            //System.out.print(path.get(i).getAirportName()+" - ");
        }



        int source = 1;

        ArrayList<Airport> visited = routeToDestination.getVisitedAirports();
        for(int i = 0; i < visited.size(); i++){
            System.out.println(visited.get(i).getAirportName());
        }

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
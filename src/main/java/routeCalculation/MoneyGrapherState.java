package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class MoneyGrapherState implements GrapherState {
    private ArrayList<Route> routes;
    private GrapherLambdaFunctions grapherLambdaFunctions;

    public MoneyGrapherState(){
        routes = new ArrayList<Route>();
        grapherLambdaFunctions = () -> 0;
        grapherLambdaFunctions.initialise();
    }

    @Override
    public ArrayList<Route> startCalculation(Airport start, Airport destination, ArrayList<Airport> airports) {
        computeShortestRouteToEveryAirport(start, airports);
        Route traceBack;
        ArrayList<Route> routeToDestination = new ArrayList<Route>();
        do{
            traceBack = grapherLambdaFunctions.getRouteWithSpecifiedDestination(destination, routes);
            destination = traceBack.getOrigin();
            routeToDestination.add(traceBack);
        }while(traceBack.getOrigin() != start);
        Collections.reverse(routeToDestination);
        return routeToDestination;
    }

    private void computeShortestRouteToEveryAirport(Airport sourceAirport, ArrayList<Airport> listOfAirports) {
        sourceAirport.setMinimumDistance(0.);
        PriorityQueue<Airport> airportQueue = new PriorityQueue<Airport>();
        // start with our departure airport
        GrapherLambdaFunctions grapherLambdaFunctions = () -> 0;
        grapherLambdaFunctions.initialise();
        airportQueue.add(sourceAirport);
        while (!airportQueue.isEmpty()) {
            Airport currentAirport = airportQueue.poll();
            // run through each of the flights leaving this airport.
            for (Edge currentFlight :  currentAirport.getEdges()) {
                double costOfCurrentFlight, costThroughCurrentAirport;
                // find target airport through the flight destination
                Airport targetAirport = grapherLambdaFunctions.getAirportByID(currentFlight.getTarget(), listOfAirports);

                costOfCurrentFlight = currentFlight.getWeight();
                // calculate the cost of getting to this next node
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;
                if (costThroughCurrentAirport < targetAirport.getMinimumDistance()) {
                    // seems like a valid route, lets add it to our list
                    routes.add(new Route(currentAirport, targetAirport, currentFlight, costOfCurrentFlight));
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    airportQueue.add(targetAirport);
                    routes = grapherLambdaFunctions.eliminateDuplicateTargets(routes);
                }
            }
        }
    }
}

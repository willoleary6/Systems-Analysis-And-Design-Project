package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class MoneyGrapherState implements GrapherState {
    private ArrayList<Route> routes;
    private GrapherLambdaFunctions grapherLambdaFunctions;
    private Grapher currentGrapherState;

    public MoneyGrapherState(Grapher currentGrapherState){
        routes = new ArrayList<Route>();
        grapherLambdaFunctions = () -> 0;
        grapherLambdaFunctions.initialise();
        this.currentGrapherState = currentGrapherState;
    }

    @Override
    public void startCalculation(Airport start, ArrayList<Airport> airports) {
        computeShortestRouteToEveryAirport(start, airports);
        currentGrapherState.setGrapherState(new TraceBackGrapherState(routes, start));
    }

    public  ArrayList<Route> calculateTraceBack(Airport destination){
        return null;
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

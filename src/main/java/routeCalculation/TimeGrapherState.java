package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.PriorityQueue;

public class TimeGrapherState implements GrapherState {
    private ArrayList<Route> routes = new ArrayList<Route>();
    private GrapherLambdaFunctions grapherLambdaFunctions;
    private FlightDayAndTimeToDateLambdaFunctions flightDayAndTimeToDateLambdaFunctions;

    public TimeGrapherState(){
        routes = new ArrayList<Route>();
        grapherLambdaFunctions = () -> 0;
        grapherLambdaFunctions.initialise();

        flightDayAndTimeToDateLambdaFunctions = () -> 0;
        flightDayAndTimeToDateLambdaFunctions.initialise();
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
        ArrayList<Airport> visitedAirports = new ArrayList<Airport>();

        // start with our departure airport
        airportQueue.add(sourceAirport);
        Date sourceDate = new Date();
        while (!airportQueue.isEmpty()) {
            Airport currentAirport = airportQueue.poll();
            // run through each of the flights leaving this airport.
            Date targetDate = new Date();
            for (Edge currentEdge :  currentAirport.getEdges()) {
                Flight currentFlight = ((FlightDiscountDecorator)currentEdge).getFlight();
                double costOfCurrentFlight, costThroughCurrentAirport;
                // find target airport through the flight destination
                Airport targetAirport = grapherLambdaFunctions.getAirportByID(currentFlight.getTarget(), listOfAirports);
                // Date and time of arrival date for current flight
                targetDate = flightDayAndTimeToDateLambdaFunctions.convertFlightTimeToDate(currentFlight.getArriveDay(),
                                currentFlight.getArriveTime());
                costOfCurrentFlight = targetDate.getTime() - sourceDate.getTime();
                // calculate the cost of getting to this next node
                costThroughCurrentAirport = currentAirport.getMinimumDistance() + costOfCurrentFlight;
                if (costThroughCurrentAirport < targetAirport.getMinimumDistance()
                        && (!visitedAirports.contains(targetAirport))) {
                    // seems like a valid route, lets add it to our list
                    routes.add(new Route(currentAirport, targetAirport, currentEdge, currentEdge.getWeight()));
                    targetAirport.setMinimumDistance(costThroughCurrentAirport);
                    airportQueue.add(targetAirport);
                    visitedAirports.add(targetAirport);
                    sourceDate =  flightDayAndTimeToDateLambdaFunctions.convertFlightTimeToDate(currentFlight.getDepartDay(),
                            currentFlight.getDepartTime());
                    routes = grapherLambdaFunctions.eliminateDuplicateTargets(routes);
                }
            }



        }
    }

}

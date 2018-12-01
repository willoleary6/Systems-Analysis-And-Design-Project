package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;

public class TraceBackGrapherState implements GrapherState {
    private ArrayList<Route> routes;
    private GrapherLambdaFunctions grapherLambdaFunctions;
    private Airport start;

    public TraceBackGrapherState(ArrayList<Route> routes, Airport start){
        this.routes = routes;
        grapherLambdaFunctions = () -> 0;
        grapherLambdaFunctions.initialise();
        this.start = start;
    }
    @Override
    public void startCalculation(Airport start, ArrayList<Airport> airports) {
    }

    @Override
    public ArrayList<Route> calculateTraceBack(Airport destination) {
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
}

package routeCalculation;

import java.util.ArrayList;

public interface GrapherState {
    ArrayList<Route> startCalculation(Airport start, Airport destination, ArrayList<Airport> airports);
}
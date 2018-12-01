package routeCalculation;

import java.util.ArrayList;

public interface GrapherState {
    void startCalculation(Airport start,  ArrayList<Airport> airports);
    ArrayList<Route> calculateTraceBack(Airport destination);
}
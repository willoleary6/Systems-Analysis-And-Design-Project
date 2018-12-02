package routeCalculation;

import java.util.ArrayList;
import java.util.Date;

public interface GrapherState {
    void startCalculation(Airport start,  ArrayList<Airport> airports, Date departureDate);
    ArrayList<Route> calculateTraceBack(Airport destination);
}
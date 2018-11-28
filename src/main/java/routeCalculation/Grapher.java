package routeCalculation;

import java.util.ArrayList;

public interface Grapher {
    ArrayList<Route> startCalculation(Airport start, Airport destination, ArrayList<Airport> airports);
}

package routeCalculation;

import java.util.ArrayList;

public class Grapher{
    private GrapherState grapher;
    private GrapherState[] states  = {new MoneyGrapherState(), new TimeGrapherState()};
    private int currentState = 0;

    public Grapher(int currentState){
        this.currentState = currentState;
    }

    public ArrayList<Route> startCalculation(Airport start, Airport destination, ArrayList<Airport> airports){
        return states[currentState].startCalculation(start, destination, airports);
    }
}
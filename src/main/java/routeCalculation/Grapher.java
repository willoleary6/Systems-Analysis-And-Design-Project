package routeCalculation;

import java.util.ArrayList;

public class Grapher{
    private GrapherState grapher;
    private GrapherState[] states  = {new MoneyGrapherState(this), new TimeGrapherState(this)};

    public Grapher(int currentState){
        this.grapher = states[currentState];
    }

    public void setGrapherState(GrapherState grapher){
        this.grapher = grapher;
    }

    public void startCalculation(Airport start,  ArrayList<Airport> airports){
        grapher.startCalculation(start, airports);
    }

    public ArrayList<Route> calculateTraceBack(Airport destination){
       return  grapher.calculateTraceBack(destination);
    }

}
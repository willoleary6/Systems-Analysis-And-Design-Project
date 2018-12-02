package routeCalculation;

import java.util.ArrayList;
import java.util.Date;

public class Grapher{
    private GrapherState grapher;
    private GrapherState[] states  = {new MoneyGrapherState(this), new TimeGrapherState(this)};

    public Grapher(int currentState){
        this.grapher = states[currentState];
    }

    public void setGrapherState(GrapherState grapher){
        this.grapher = grapher;
    }

    public void startCalculation(Airport start,  ArrayList<Airport> airports, Date departureDate){
        grapher.startCalculation(start, airports, departureDate);
    }

    public ArrayList<Route> calculateTraceBack(Airport destination){
       return  grapher.calculateTraceBack(destination);
    }

}
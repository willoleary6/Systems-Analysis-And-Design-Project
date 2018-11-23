package routeCalculation;

import java.util.ArrayList;

public class Airport implements Comparable<Airport>, Node {
    private String airportName;
    private int autoKey;
    private ArrayList<Flight> flights =  new ArrayList<Flight>();
    private double minimumDistance = Double.POSITIVE_INFINITY;
    private Airport previous;

    public Airport(int autoKey, String airportName) {
        this.autoKey = autoKey;
        this.airportName = airportName;
    }

    public String getAirportName() {
        return airportName;
    }

    public int getAutoKey() {
        return autoKey;
    }

    public void setFlightsDeparting(ArrayList<Flight> flights){
        this.flights = flights;
    }

    public ArrayList<Flight> getEdges() {
        return flights;
    }



    public double getMinimumDistance() {
        return minimumDistance;
    }

    public void setMinimumDistance(Double newDistance) {
        minimumDistance = newDistance;
    }


    public int compareTo(Airport other)
    {
        return Double.compare(minimumDistance, other.minimumDistance);
    }

    @Override
    public String toString() {
        return airportName;
    }
}
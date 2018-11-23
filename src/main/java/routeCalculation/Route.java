package routeCalculation;

import java.util.ArrayList;

public class Route {
    private Airport dest, origin;
    private ArrayList<Flight> flights ;
    private ArrayList<Airport> visitedAirports;
    private double finalCost;
    private String time;

    public Route(){
        flights = new ArrayList<Flight>();
        visitedAirports = new ArrayList<Airport>();
    }

    public void addToVisitedAirports(Airport vistedAirport){
        visitedAirports.add(vistedAirport);
    }

    public Airport getLastAirportVisited(){
        if(visitedAirports.size() > 0){
            return visitedAirports.get(visitedAirports.size() -1 );
        }
        return null;

    }

    public Airport getDest() {
        return dest;
    }

    public void setDest(Airport dest) {
        this.dest = dest;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public void addToCost(double cost){
        finalCost += cost;
    }

    public ArrayList<Airport> getVisitedAirports() {
        return visitedAirports;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight) ;
    }
/*
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    */
}

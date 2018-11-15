package routeCalculation;

import org.json.JSONObject;

public class Flight {

    private int sourceAirportID;
    private int targetAirportID;
    private int airlineID;
    private String flightNumber;
    private JSONObject depart;
    private JSONObject arrive;
    private double cost;

    public Flight(int sourceAirportID, int targetAirportID, int airlineID, String flightNumber, JSONObject depart,
                  JSONObject arrive, double cost) {
        this.sourceAirportID = sourceAirportID;
        this.targetAirportID = targetAirportID;
        this.airlineID = airlineID;
        this.flightNumber = flightNumber;
        this.depart = depart;
        this.arrive = arrive;
        this.cost = cost;
    }

    public int getSourceAirportID() {
        return sourceAirportID;
    }

    public int getTargetAirportID() {
        return targetAirportID;
    }

    public int getAirlineID() {
        return airlineID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public JSONObject getDepart() {
        return depart;
    }

    public JSONObject getArrive() {
        return arrive;
    }

    public double getCost() {
        return cost;
    }

    /*// determines the neighbouring node of a supplied node, based on the two nodes connected by this edge
    public int getNeighbourIndex(int nodeIndex) {
        if (this.fromAirportIndex == nodeIndex) {
            return this.toAirportIndex;
        } else {
            return this.fromAirportIndex;
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getDepartureAirport() {
        return departureAirport;
    }

    public int getDestinationAirport() {
        return destinationAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }*/

}

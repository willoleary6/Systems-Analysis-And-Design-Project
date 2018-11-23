package routeCalculation;


import java.util.Date;

public class Flight implements Edge {
    private int sourceAirportID;
    private int targetAirportID;
    private int airlineID;
    private int flightID;
    private String flightNumber;
    private String departTime;
    private String departDay;
    private String arriveTime;
    private String arriveDay;
    private double cost;

    public Flight(int flightID, int sourceAirportID, int targetAirportID, int airlineID, String flightNumber, String departureTime,
                  String arrivalTime, String departureDay, String arrivalDay, double cost) {
        this.flightID = flightID;
        this.sourceAirportID = sourceAirportID;
        this.targetAirportID = targetAirportID;
        this.airlineID = airlineID;
        this.flightNumber = flightNumber;
        this.departTime = departureTime;
        this.departDay = departureDay;
        this.arriveTime = arrivalTime;
        this.arriveDay = arrivalDay;
        this.cost = cost;
    }

    public Flight() {
    }

    public int getOrigin() {
        return sourceAirportID;
    }

    public int getTarget() {
        return targetAirportID;
    }

    public int getAirlineID() {
        return airlineID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getDepartDay() {
        return departDay;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public String getArriveDay() {
        return arriveDay;
    }

    public double getWeight() {
        return cost;
    }

    public int getFlightID() {
        return flightID;
    }
}
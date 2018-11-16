package routeCalculation;


import java.util.Date;

public class Flight {
    private int sourceAirportID;
    private int targetAirportID;
    private int airlineID;
    private String flightNumber;
    private String departTime;
    private String departDay;
    private String arriveTime;
    private String arriveDay;
    private double cost;

    public Flight(int sourceAirportID, int targetAirportID, int airlineID, String flightNumber, String departureTime,
                  String arrivalTime, String departureDay, String arrivalDay, double cost) {
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

    public double getCost() {
        return cost;
    }
}
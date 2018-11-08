package routeCalculation;


import java.util.Date;

public class flight {
    private int flightnumber;
    private String departureAirport;
    private String destinationAirport;
    private Date departureTime;
    private Date arrivalTime;
    private double price;
    private int airlineID;

    public flight(int flightnumber, String departureAirport, String destinationAirport,
                    Date departureTime, Date arrivalTime, double price, int airlineID) {
        this.flightnumber = flightnumber;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        this.airlineID = airlineID;
    }

    public int getFlightnumber() {
        return flightnumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public int getAirlineID() {
        return airlineID;
    }
}

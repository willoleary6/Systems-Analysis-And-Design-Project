package routeCalculation;


import java.util.Date;

public class Flight {
    private String flightnumber;
    private int departureAirport;
    private int destinationAirport;
    private String departureTime;
    private String arrivalTime;
    private String departureDay;
    private String arrivalDay;
    private double price;
    private int airlineID;

    public Flight(String flightnumber, int departureAirport, int destinationAirport,
                  String departureTime, String arrivalTime, String departureDay, String arrivalDay,
                  double price, int airlineID) {
        this.flightnumber = flightnumber;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.departureDay = departureDay;
        this.arrivalDay = arrivalDay;
        this.price = price;
        this.airlineID = airlineID;
    }

    public String getFlightnumber() {
        return flightnumber;
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
    }

    public double getPrice() {
        return price;
    }

    public int getAirlineID() {
        return airlineID;
    }

    public String getDepartureDay() {
        return departureDay;
    }

    public String getArrivalDay() {
        return arrivalDay;
    }
}

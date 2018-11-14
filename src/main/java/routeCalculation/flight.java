package routecalculation;


import java.util.Date;

public class flight implements Edge {
    private String flightnumber;
    private int departureAirport;
    private int destinationAirport;
    private String departureTime;
    private String arrivalTime;
    private String departureDay;
    private String arrivalDay;
    private double price;
    private int airlineID;
    private int fromNodeIndex;
    private int toNodeIndex;
    private double cost;

    public flight( int departureAirport, int destinationAirport,
                    double price) {
        //this.flightnumber = flightnumber;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        //this.departureTime = departureTime;
        //this.arrivalTime = arrivalTime;
        //this.departureDay = departureDay;
        //this.arrivalDay = arrivalDay;
        this.price = price;
        //this.airlineID = airlineID;
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

    public int getFromNodeIndex() {
        return departureAirport;
    }

    public int getToNodeIndex() {
        return destinationAirport;
    }

    public int getNeighbourIndex(int nodeIndex) {
        if (this.fromNodeIndex == nodeIndex)
            return  this.toNodeIndex;
        else
            return  this.fromNodeIndex;

        /*switch (nodeIndex) {
            case nodeIndex:
                return this.toNodeIndex;
                break;
            default:
                return this.fromNodeIndex;
                break;
        }*/
    }

    public double getCost() {
        return cost;
    }
}

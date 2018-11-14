package routeCalculation;

public class Flight implements Edge {

    private int fromNodeIndex;
    private int toNodeIndex;
    private int cost;
    private int departureAirport;
    private int destinationAirport;
    private String flightnumber;
    private String departureTime;
    private String arrivalTime;

    public Flight(int fromNodeIndex, int toNodeIndex, int cost) {
        this.fromNodeIndex = fromNodeIndex;
        this.toNodeIndex = toNodeIndex;
        this.cost = cost;
    }

    public int getDepartureAirportIndex() {
        return fromNodeIndex;
    }

    public int getDestinationAirportIndex() {
        return toNodeIndex;
    }

    public int getCost() {
        return cost;
    }

    // determines the neighbouring node of a supplied node, based on the two nodes connected by this edge
    public int getNeighbourIndex(int nodeIndex) {
        if (this.fromNodeIndex == nodeIndex) {
            return this.toNodeIndex;
        } else {
            return this.fromNodeIndex;
        }
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

}

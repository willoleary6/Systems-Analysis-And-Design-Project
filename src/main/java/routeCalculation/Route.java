package routeCalculation;


public class Route {
    private Airport destination;
    private Airport origin;
    private Flight flight ;
    private double cost ;

    public Route(Airport origin, Airport destination, Edge flight, double cost){
        this.origin = origin;
        this.destination = destination;
        this.flight = (Flight) flight;
        this.cost = cost;
    }
    public Airport getDest() {
        return destination;
    }

    public void setDest(Airport destination) {
        this.destination = destination;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Edge flight) {
        this.flight = (Flight) flight;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

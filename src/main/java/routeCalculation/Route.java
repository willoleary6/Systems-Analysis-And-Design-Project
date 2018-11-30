package routeCalculation;


public class Route {
    private Airport destination;
    private Airport origin;
    private Edge discountedFlight;
    private double cost ;

    public Route(Airport origin, Airport destination, Edge flight, double cost){
        this.origin = origin;
        this.destination = destination;
        this.discountedFlight = flight;
        this.cost = cost;
    }
    public Airport getDestination() {
        return destination;
    }

    public Airport getOrigin() {
        return origin;
    }


    public FlightDiscountDecorator getFlightDecorator() {
        return (FlightDiscountDecorator) discountedFlight;
    }


    public double getCost() {
        return cost;
    }

}

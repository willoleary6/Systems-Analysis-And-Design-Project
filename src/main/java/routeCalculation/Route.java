package routeCalculation;


public class Route {
    private Airport dest, origin;
    private Flight flight ;

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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Edge flight) {
        this.flight = (Flight) flight;
    }
}

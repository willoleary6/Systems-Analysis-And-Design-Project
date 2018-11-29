package routeCalculation;

public class FlightDecorator implements Edge {
    protected Flight decoratedFlight;
    private double discountOnFlight;

    public FlightDecorator(Flight decoratedFlight, int discountOnFlight){
        this.decoratedFlight = decoratedFlight;
        this.discountOnFlight = discountOnFlight;
    }
    @Override
    public int getOrigin() {
        return decoratedFlight.getOrigin();
    }

    @Override
    public int getTarget() {
        return decoratedFlight.getTarget();
    }

    @Override
    public double getWeight() {
        discountOnFlight = discountOnFlight/100;
        if(discountOnFlight > 0){
            return decoratedFlight.getWeight()*discountOnFlight;
        }
        System.out.println("in decorator returning weight");
        return decoratedFlight.getWeight();
    }
}

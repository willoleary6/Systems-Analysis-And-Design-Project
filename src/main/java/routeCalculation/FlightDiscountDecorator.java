package routeCalculation;

public class FlightDiscountDecorator extends EdgeDecorator {
    private double discountOnFlight;
    public FlightDiscountDecorator(Flight flightToBeDiscounted, double discountOnFlight){
        super.decoratedEdge = flightToBeDiscounted;
        this.discountOnFlight = discountOnFlight/100;
    }
    public Flight getFlight(){
        return (Flight) super.decoratedEdge;
    }

    @Override
    public double getWeight() {
        if(discountOnFlight > 0 && discountOnFlight <= 100){
            return decoratedEdge.getWeight()* discountOnFlight;
        }else if(discountOnFlight > 100){
            return 0;
        }
        return decoratedEdge.getWeight();
    }
}

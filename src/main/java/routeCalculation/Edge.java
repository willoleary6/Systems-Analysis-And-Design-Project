package routeCalculation;

public interface Edge {
    int getDepartureAirportIndex();
    int getDestinationAirportIndex();
    int getNeighbourIndex(int nodeIndex);
    int getCost();
}

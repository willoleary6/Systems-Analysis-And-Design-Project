package routeCalculation;

public interface Edge {
    int getOrigin();
    int getTarget();
    /**
     * @post return >= 0
     */
    double getWeight();
}

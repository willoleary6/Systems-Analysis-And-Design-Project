package routeCalculation;

import java.util.ArrayList;

public interface Node {
    ArrayList<Flight> getEdges();
    void setMinimumDistance(Double newDistance);
    double getMinimumDistance();
}

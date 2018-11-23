package routeCalculation;

import java.util.ArrayList;

public interface Node {
    ArrayList<? extends Edge> getEdges();
    void setMinimumDistance(Double newDistance);
    double getMinimumDistance();
}

package routeCalculation;

import java.util.ArrayList;

public interface Node {
    /**
     * @post return.size() > 0
     */
    ArrayList<? extends Edge> getEdges();

    /**
     *
     * @pre newDistance >= 0
     */
    void setMinimumDistance(Double newDistance);

    double getMinimumDistance();
}

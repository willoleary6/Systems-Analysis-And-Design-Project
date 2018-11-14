package routecalculation;

import java.util.ArrayList;

public interface Node {
    ArrayList<Integer> getJourney();
    boolean isVisited();
    void setVisited(boolean visited);
    int getDistanceFromSource();
    void setDistanceFromSource(int distanceFromSource);
    void addNodeToJourney(int nodeIndex);
}

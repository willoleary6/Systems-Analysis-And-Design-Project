package routecalculation;

public interface Edge {
    int getFromNodeIndex();
    int getToNodeIndex();
    int getNeighbourIndex(int nodeIndex);
    double getCost();
}

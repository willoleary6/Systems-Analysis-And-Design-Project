package routeCalculation;

import java.util.ArrayList;

public class Airport implements Node{

    private int distanceFromSource = Integer.MAX_VALUE;
    private boolean visited;
    private ArrayList<Integer> journey = new ArrayList<Integer>();
    private ArrayList<Flight> edges = new ArrayList<Flight>(); // now we must create edges

    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public void addAirportToJourney(int nodeIndex){
        boolean duplicates = false;
        for(int i = 0; i < journey.size(); i++){
            if(nodeIndex == journey.get(i)){
                duplicates = true;
            }
        }
        if(!duplicates){
            journey.add(nodeIndex);
        }
    }

    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Flight> getFlights() {
        return edges;
    }

    public ArrayList<Integer> getJourney() {
        return journey;
    }

    public void setEdges(ArrayList<Flight> edges) {
        this.edges = edges;
    }

}

package routecalculation;

import java.util.ArrayList;

public class Airport implements Node {
    private int airportID, distanceFromSource = Integer.MAX_VALUE;
    private ArrayList<flight> flights;
    private ArrayList<Integer> journey;
    private boolean visited;

    public Airport() {
        flights = new ArrayList<flight>();
        journey = new ArrayList<Integer>();
       // this.airportID = airportID;
    }

    public int getAirportID() {
        return airportID;
    }

    @Override
    public int getDistanceFromSource() {
        return distanceFromSource;
    }

    public ArrayList<flight> getFlights() {
        return flights;
    }

    @Override
    public ArrayList<Integer> getJourney() {
        return journey;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public void setDistanceFromSource(int distanceFromSource) {
        this.distanceFromSource = distanceFromSource;
    }

    @Override
    public void addNodeToJourney(int nodeIndex) {
        boolean duplicates = false;

        for (int i: journey) {
            if (nodeIndex == journey.get(i))
                duplicates = true;
        }

        if (!duplicates)
            journey.add(nodeIndex);
    }
}

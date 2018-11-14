package routecalculation;

import java.util.ArrayList;

public class Grapher {
    private Airport[] airports;
    private flight[] flights;
    private int numOfAirports, numOfFlights, source;

    public Grapher(flight[] flights) {
        this.flights = flights;
        this.numOfAirports = calculateNumOfAirports(flights);
        this.airports = new Airport[this.numOfAirports];

        for (int n = 0; n < this.numOfAirports; n++)
            this.airports[n] = new Airport();

        this.numOfFlights = flights.length;


        for (int e = 0; e < this.numOfFlights; e++) {
            this.airports[flights[e].getFromNodeIndex()].getFlights().add(flights[e]);
            this.airports[flights[e].getToNodeIndex()].getFlights().add(flights[e]);
        }
    }

    private int calculateNumOfAirports(Edge[] flights) {
        int numOfAirports = 0;

        for (Edge e : flights) {
            if (e.getToNodeIndex() > numOfAirports)
                numOfAirports = e.getToNodeIndex();
            if (e.getFromNodeIndex() > numOfAirports)
                numOfAirports = e.getFromNodeIndex();
        }
        numOfAirports++;
        return numOfAirports;
    }

    public void calculateShortestDistance(int source) {
        // node 0 as source
        this.source = source;
        this.airports[this.source].setDistanceFromSource(0);
        int nextNode = this.source;
        for (int i = 0; i < this.airports.length; i++) {
            airports[i].addNodeToJourney(this.source);
        }
        // visit every node
        for (int i = 0; i < this.airports.length; i++) {
            // loop around the edges of current node

            ArrayList<flight> currentNodeFlights = this.airports[nextNode].getFlights();
            for (int joinedEdge = 0; joinedEdge < currentNodeFlights.size(); joinedEdge++) {
                int neighbourIndex = currentNodeFlights.get(joinedEdge).getNeighbourIndex(nextNode);
                // only if not visited
                if (!this.airports[neighbourIndex].isVisited()) {
                    int tentative = this.airports[nextNode].getDistanceFromSource() + (int) currentNodeFlights.get(joinedEdge).getCost();
                    if (tentative < airports[neighbourIndex].getDistanceFromSource()) {
                        airports[neighbourIndex].addNodeToJourney(nextNode);
                        airports[neighbourIndex].setDistanceFromSource(tentative);
                    }
                }
            }

            // all neighbours checked so node visited
            airports[nextNode].setVisited(true);
            // next node must be with shortest distance
            nextNode = getNodeShortestDistance();

        }

        for (int i = 0; i < this.airports.length; i++) {
            airports[i].addNodeToJourney(i);
        }
    }

    private int getNodeShortestDistance() {
        int i = 0, storedNodeIndex = 0, storedDistance = Integer.MAX_VALUE;

        for (Node n : airports) {
            int currentDistance = this.airports[i].getDistanceFromSource();

            if (!this.airports[i].isVisited() && currentDistance < storedDistance) {
                storedDistance = currentDistance;
                storedNodeIndex = i;
            }
            i++;
        }
        return storedNodeIndex;
    }

    public void printResult() {
        String output = "Number of nodes = " + this.numOfFlights;
        output += "\nNumber of edges = " + this.numOfAirports;

        for (int i = 0; i < this.airports.length; i++) {
            output += ("\nThe shortest distance from node " + this.source + " to node " + i + " is " + airports[i].getDistanceFromSource()) + "\n";
            ArrayList<Integer> nodesOnJourney = airports[i].getJourney();
            for (int j = 0; j < nodesOnJourney.size(); j++) {
                output += nodesOnJourney.get(j);
                if (j < nodesOnJourney.size() - 1) {
                    output += "->";
                }
            }
        }
        System.out.println(output);
    }

    public Node[] getAirports() {
        return airports;
    }

    public int getNumOfAirports() {
        return numOfAirports;
    }

    public Edge[] getFlights() {
        return flights;
    }

    public int getNumOfFlights() {
        return numOfFlights;
    }
}

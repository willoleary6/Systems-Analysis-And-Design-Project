package routeCalculation;


import java.util.ArrayList;

// now we must create graph object and implement dijkstra algorithm
public class Grapher {

    private Airport[] airports;
    private int numOfAirports;
    private Flight[] flights;
    private int numOfLights;
    private int source;

    public Grapher(Flight[] flights) {
        this.flights = flights;
        this.numOfAirports = calculateNoOfNodes(flights);
        this.airports = new Airport[this.numOfAirports];
        for (int n = 0; n < this.numOfAirports; n++) {
            this.airports[n] = new Airport();
        }
        this.numOfLights = flights.length;
        for (int flightToAdd = 0; flightToAdd < this.numOfLights; flightToAdd++) {
            this.airports[flights[flightToAdd].getDepartureAirportIndex()].getFlights().add(flights[flightToAdd]);
            this.airports[flights[flightToAdd].getDestinationAirportIndex()].getFlights().add(flights[flightToAdd]);
        }
    }

    private int calculateNoOfNodes(Flight[] edges) {
        int noOfAirports = 0;
        for (Flight e : edges) {
            if (e.getDestinationAirportIndex() > noOfAirports)
                noOfAirports = e.getDestinationAirportIndex();
            if (e.getDepartureAirportIndex() > noOfAirports)
                noOfAirports = e.getDepartureAirportIndex();
        }
        noOfAirports++;
        return noOfAirports;
    }

    // next video to implement the Dijkstra algorithm !!!
    public void calculateShortestDistance(int source) {
        // node 0 as source
        this.source = source;
        this.airports[this.source].setDistanceFromSource(0);
        int nextAirport = this.source;
        for (int i = 0; i < this.airports.length; i++) {
            airports[i].addAirportToJourney(this.source);
        }
        // visit every node
        for (int i = 0; i < this.airports.length; i++) {
            // loop around the flights of current node

            ArrayList<Flight> currentAirportFlights = this.airports[nextAirport].getFlights();
            for (int joinedFlight = 0; joinedFlight < currentAirportFlights.size(); joinedFlight++) {
                int neighbourIndex = currentAirportFlights.get(joinedFlight).getNeighbourIndex(nextAirport);
                // only if not visited
                if (!this.airports[neighbourIndex].isVisited()) {
                    int tentative = this.airports[nextAirport].getDistanceFromSource() + currentAirportFlights.get(joinedFlight).getCost();
                    if (tentative < airports[neighbourIndex].getDistanceFromSource()) {
                        airports[neighbourIndex].addAirportToJourney(nextAirport);
                        airports[neighbourIndex].setDistanceFromSource(tentative);
                    }
                }
            }

            // all neighbours checked so node visited
            airports[nextAirport].setVisited(true);
            // next node must be with shortest distance
            nextAirport = getAirportShortestDistanced();

        }

        for (int i = 0; i < this.airports.length; i++) {
            airports[i].addAirportToJourney(i);
        }
    }

    // now we're going to implement this method in next part !
    private int getAirportShortestDistanced() {
        int storedAirportIndex = 0;
        int storedDistance = Integer.MAX_VALUE;

        for (int i = 0; i < this.airports.length; i++) {
            int currentDistance = this.airports[i].getDistanceFromSource();

            if (!this.airports[i].isVisited() && currentDistance < storedDistance) {
                storedDistance = currentDistance;
                storedAirportIndex = i;
            }
        }

        return storedAirportIndex;
    }

    // display result
    public void printResult() {
        String output = "Number of airports = " + this.numOfAirports;
        output += "\nNumber of flights = " + this.numOfLights;

        for (int i = 0; i < this.airports.length; i++) {
            output += ("\nThe shortest distance from node "+this.source+" to node " + i + " is " + airports[i].getDistanceFromSource())+"\n";
            ArrayList<Integer> airportsOnJourney = airports[i].getJourney();
            for (int j = 0; j < airportsOnJourney.size(); j++){
                output += airportsOnJourney.get(j);
                if( j < airportsOnJourney.size()-1 ){
                    output+="->";
                }
            }
        }

        System.out.println(output);
    }

    public Airport[] getAirports() {
        return airports;
    }

    public int getNumOfAirports() {
        return numOfAirports;
    }

    public Flight[] getFlights() {
        return flights;
    }

    public int getNumOfFlights() {
        return numOfLights;
    }

}
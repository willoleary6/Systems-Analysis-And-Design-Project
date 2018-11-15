package routeCalculation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Grapher {
    public static void computePaths(Airport source, ArrayList<Airport> listOfAirports) {
        source.setMinimumDistance(0.);
        PriorityQueue<Airport> vertexQueue = new PriorityQueue<Airport>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Airport u = vertexQueue.poll();

            for (Flight e : u.getFlights()) {
                double weight, distanceThroughU;

                Airport v = getAirportByID(e.getTargetAirportID(), listOfAirports);
                weight = e.getCost();
                distanceThroughU = u.getMinimumDistance() + weight;

                if (distanceThroughU < v.getMinimumDistance()) {
                    vertexQueue.remove(distanceThroughU);
                    v.setMinimumDistance(distanceThroughU);
                    v.setPrevious(u);
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Airport> getShortestPathTo(Airport target) {
        List<Airport> path = new ArrayList<Airport>();

        for (Airport vertex = target; vertex != null; vertex = vertex.getPrevious())
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

    public static Airport getAirportByID(int airportID, ArrayList<Airport> listOfAirports) {
        for (int i = 0; i < listOfAirports.size(); i++) {
            if (airportID == listOfAirports.get(i).getAutoKey())
                return listOfAirports.get(i);
        }
        return listOfAirports.get(0);
    }
}
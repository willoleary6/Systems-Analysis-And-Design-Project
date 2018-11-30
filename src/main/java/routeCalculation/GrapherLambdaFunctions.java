package routeCalculation;

import java.util.ArrayList;

public interface GrapherLambdaFunctions {

    int initialise();

    default ArrayList<Route> eliminateDuplicateTargets(ArrayList<Route> routes){
        ArrayList<Route> listOfAirports = new ArrayList<Route>();
        Route routeToRemove = null;
        Route matchedWith = null;
        for(Route currentRoute : routes){
            matchedWith = checkForDuplicate(listOfAirports, currentRoute.getDestination());
            if(matchedWith == null){
                listOfAirports.add(currentRoute);
            }else{
                routeToRemove = currentRoute;
                break;
            }
        }
        if(routeToRemove != null){
            if(calculateCostOfTraceBack(matchedWith, routes) <= calculateCostOfTraceBack(routeToRemove, routes)){
                routes.remove(matchedWith);
            }else {
                routes.remove(routeToRemove);
            }
            return eliminateDuplicateTargets(routes);
        }
        return routes;
    }

    default Route checkForDuplicate(ArrayList<Route> listOfRoutes, Airport currentAirport){
        for(Route airportCheck : listOfRoutes){
            if(airportCheck.getDestination() == currentAirport){
                return airportCheck;
            }
        }
        return null;
    }

    default double calculateCostOfTraceBack(Route routeToTraceBack,  ArrayList<Route> listOfRoutes){
        double summaryCost = 0;
        Airport originAirport = routeToTraceBack.getOrigin();
        while (getRouteWithSpecifiedDestination(originAirport, listOfRoutes) != null) {
            summaryCost += routeToTraceBack.getCost();
            routeToTraceBack = getRouteWithSpecifiedDestination(originAirport, listOfRoutes);
            originAirport = routeToTraceBack.getOrigin();
        }
        return summaryCost;
    }


    default Route getRouteWithSpecifiedDestination(Airport target, ArrayList<Route> listOfRoutes) {
        for(int i = 0; i < listOfRoutes.size(); i++){
            if(listOfRoutes.get(i).getDestination().getAirportName() == target.getAirportName()){
                return listOfRoutes.get(i);
            }
        }
        return null;
    }

    default Airport getAirportByID(int airportID, ArrayList<Airport> listOfAirports) {
        Airport toReturn = null;
        for (int i = 0; i < listOfAirports.size(); i++) {
            if (airportID == listOfAirports.get(i).getAutoKey())
                toReturn = listOfAirports.get(i);
        }
        return toReturn;
    }
}

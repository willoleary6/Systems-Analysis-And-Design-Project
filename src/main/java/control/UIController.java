package control;

import account.AirlineEmployee;
import account.User;
import backgroundServices.API_Handlers.insertRequestHandler;
import routeCalculation.Airport;
import routeCalculation.Flight;
import routeCalculation.Route;

import java.util.ArrayList;
import java.util.Date;


public class UIController {
    private static final UIController shared = new UIController();
    public User currentUser;
    private UserControl userCon;
    private SearchController searchCon;
    private ArrayList<ArrayList<Route>> archivedRoutes;
    private insertRequestHandler insertHandler;
    private ArrayList<Route> routeResult;

    private UIController(){
        userCon = new UserControl();
        searchCon =  new SearchController();
        archivedRoutes = new ArrayList<>();
        insertHandler = new insertRequestHandler();
    }

    // returns the instance of UIController singleton
    public static UIController getInstance() {
        return shared;
    }

    public boolean logIn(String username, String password) {
        User user = userCon.getUser(username, password);
        if(user != null) {
            currentUser = user;
            return true;
        }
        else
            return false;
    }

    public void register(String username, String password, String email, int userType) {
        User user = userCon.createUser(username, password, email, userType);
        if(user != null)
            currentUser = user;
    }


    public void searchForFlights(Airport departure, Airport destination, Date departureDate, boolean costBased) {
        routeResult = searchCon.searchForFlight(departure, destination, departureDate, costBased);
        archivedRoutes.add(routeResult);
    }

    public ArrayList<Route> getResults() {
        return routeResult;
    }

    public ArrayList<Airport> getAirports() {
        searchCon.retrieveAirports();
        return searchCon.getAirports();
    }

    public void applyDiscount(String userID, Flight flight,String discountStartDate,
                              String discountEndDate, String discountPercentage) {
        if(checkForHigherAccess()) {
            if(flight.getAirlineID() == ((AirlineEmployee) currentUser).getAirlineID()) {
                insertHandler.addNewDiscount(userID, Integer.toString(flight.getFlightID()), discountStartDate,
                        discountEndDate, discountPercentage);
            }
        }
    }

    public void logout() {
        currentUser = null;
        archivedRoutes = new ArrayList<>();
    }

    public boolean checkForHigherAccess(){
        //checks if the user is an airline employee thus giving access to discount system
        if(currentUser.getUserType() > 0)
            return true;
        else
            return false;
    }

}

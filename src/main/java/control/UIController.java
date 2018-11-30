package control;

import account.AirlineEmployee;
import account.User;
import routeCalculation.Flight;


public class UIController {
    public static UIController shared = new UIController();
    public User currentUser;
    private UserControl userCon;
    //private Arrylist<Flight> archivedRoutes;
    //private Arrylist<Flight> routes;
    private String routeOrigin;
    private String routeDestination;

    public UIController(){
        userCon = new UserControl();
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

    /*public ArrayList<Arrylist<Flight>> getPreviousSearchs() {
        return archivedRoutes;
    }*/

    public void searchForFlights(int searchParams, String origin, String destination, String timestamp) {
        /* search param is either 0 or 1, 0 being search for shortest time, 1 for lowest price*/

    }

    public void applyDiscount(Flight flight, int percentage) {
        if(checkForHigherAccess()) {
            if(flight.getAirlineID() == ((AirlineEmployee) currentUser).getAirlineID()) {
                double price = flight.getWeight();
                price = price - price / percentage;
                flight.setCost(price);
                //update db
                //apply discount and update database
            }
        }
    }

    public void logout() {
        currentUser = null;
    }

    public boolean checkForHigherAccess(){
        /**
         *  checks if the user is an airline employee thus giving access to discount system
         *  */
        if(currentUser.getUserType() > 0)
            return true;
        else
            return false;
    }

}

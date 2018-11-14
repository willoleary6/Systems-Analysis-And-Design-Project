package control;

import account.User;

import java.util.ArrayList;


public class uiController {
    public User currentUser;
    private UserControl userCon;
    //private Arrylist<Flight> archivedRoutes;
    //private Arrylist<Flight> routes;
    private String routeOrigin;
    private String routeDestination;

    public uiController(){
        userCon = new UserControl();
    }

    public void logIn(String username, String password, int userType) {
        User user = userCon.getUser(username, password, userType);
        if(user != null)
            currentUser = user;
    }

    public void register(String username, String password, int userType) {
        User user = userCon.createUser(username, password, userType);
        if(user != null)
            currentUser = user;
    }

    /*public ArrayList<Arrylist<Flight>> getPreviousSearchs() {
        return archivedRoutes;
    }*/

    public void searchForFlights(int searchParams, String origin, String destination, String timestamp) {
        /* search param is either 0 or 1, 0 being search for shortest time, 1 for lowest price*/

    }

    public void applyDiscount() {
        /* method should display all flights which below to airline employees airline then he can display discounts*/
    }

}

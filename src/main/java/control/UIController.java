package control;

import account.User;

import java.util.ArrayList;


public class UIController {
    public static UIController shared = new UIController();
    public User currentUser;
    private UserControl userCon;
//    private Arrylist<Airport> airports;

    //private Arrylist<flight> archivedRoutes;
    //private Arrylist<flight> routes;
    private String routeOrigin;
    private String routeDestination;

    private UIController(){
        userCon = new UserControl();
    }

    public void logIn(String username, String password, int userType) {
        User user = userCon.getUser(username, password, userType);
        if(user != null)
            currentUser = user;
    }

    public void register(String username, String password, String email, int userType) {
        User user = userCon.createUser(username, password, email, userType);
        if(user != null)
            currentUser = user;
    }

    /*public ArrayList<Arrylist<flight>> getPreviousSearchs() {
        return archivedRoutes;
    }*/

    public void searchForFlights(int searchParams, String origin, String destination, String timestamp) {
        /* search param is either 0 or 1, 0 being search for shortest time, 1 for lowest price*/

    }

    public void applyDiscount() {
        /* method should display all flights which below to airline employees airline then he can display discounts*/
    }

}

package control;
import account.Customer;

public class mainTest {

    public static void main(String [] args) {
        uiController ui = new uiController();
        SearchController con = new SearchController();
        ui.logIn("basicCustomer", "pass",0 );
        if(ui.currentUser != null) {
            Customer user = (Customer) ui.currentUser;
            System.out.println(user.getUserName());
            System.out.println(user.getUserID());
            System.out.println(user.getPoints().getNumOfPoints());
            con.retrieveFlights();
        }
    }
}

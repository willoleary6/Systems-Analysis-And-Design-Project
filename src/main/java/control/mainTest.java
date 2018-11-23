package control;
import account.Customer;

public class mainTest {

    public static void main(String [] args) {
        UIController ui = new UIController();
        SearchController con = new SearchController();
        ui.register("ChunkyMitts3", "D00rframe","ac2.cleere@gmail.com",0 );
          Customer user = (Customer) ui.currentUser;
           con.retrieveAirports();
            con.routeCalculation();

    }
}

package control;
import account.Customer;

public class mainTest {

    public static void main(String [] args) {
        UIController ui = new UIController();
        SearchController con = new SearchController();
        SearchController con1 = new SearchController();
        //ui.register("ChunkyMitts3", "D00rframe","ac2.cleere@gmail.com",0);
        ui.logIn("ChunkyMitts3","D00rframe");
        Customer user = (Customer) ui.currentUser;
        con.retrieveAirports();
        //con.routeCalculation(1);
        System.out.println("----");
        con.routeCalculation(0);
        System.out.println("----");
        con1.retrieveAirports();
        con1.routeCalculation(1);
    }
}

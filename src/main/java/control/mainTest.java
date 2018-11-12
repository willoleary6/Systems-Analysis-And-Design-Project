package control;
import account.Customer;

public class mainTest {

    public static void main(String [] args) {
        uiController ui = new uiController();
        ui.logIn("Hello_mannn", "D00rFrame",0 );
        Customer user = (Customer)ui.currentUser;
        System.out.println(user.getUserName());
        System.out.println(user.getPoints().getNumOfPoints());

    }
}

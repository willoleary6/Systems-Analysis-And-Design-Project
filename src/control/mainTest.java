package control;

public class mainTest {

    public static void main(String [] args) {
        uiController ui = new uiController();
        UserControl user = new UserControl();

        ui.logIn("Hello_mannn", "D00rFrame",0 );
        System.out.println(ui.currentUser.getUserName());

    }
}

import ui.coordinator.ILoginCoordinator;
import ui.coordinator.LoginCoordinator;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ILoginCoordinator loginCoordinator = new LoginCoordinator();
        loginCoordinator.start();
    }
}

package ui.coordinator;

import ui.controller.LoginFrameController;
import ui.controller.RegisterFrameController;
import ui.controller.WelcomeFrameController;

public class LoginCoordinator extends BaseCoordinator implements ILoginCoordinator {

    public void start() {
        WelcomeFrameController welcome = new WelcomeFrameController(this);
        setViewController(welcome);
    }

    public void goToLogin() {
        LoginFrameController login = new LoginFrameController(this);
        setViewController(login);
    }

    public void goToRegister() {
        RegisterFrameController register = new RegisterFrameController(this);
        setViewController(register);
    }

    public void goToMainMenu() {
        IMainMenuCoordinator mainMenuCoordinator = new MainMenuCoordinator();
        mainMenuCoordinator.start();
        setViewController(null);
    }
}

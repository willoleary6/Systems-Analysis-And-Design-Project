package ui.model;

import control.UIController;

import java.security.InvalidParameterException;

public class LoginModel  {
    private Validator validator;
    private String username;
    private String password;


    public LoginModel() {
        validator = new Validator();
    }

    public void setUsername(String username) throws InvalidParameterException {
//        if (!validator.validateEmail(username)) {
//            throw new InvalidParameterException("Invalid email address");
//        }

        this.username = username;
    }

    public void setPassword(String password) throws InvalidParameterException {
        if (!validator.validatePassword(password)) {
            throw new InvalidParameterException("Passwords must be between 6 and 32 characters.");
        }

        this.password = password;
    }

    public void login() {
        UIController.shared.logIn(username, password);
    }
}

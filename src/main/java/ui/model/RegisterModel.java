package ui.model;

import control.UIController;

import java.security.InvalidParameterException;

public class RegisterModel {
    private Validator validator;
    private String userName;
    private String email;
    private String password;

    public RegisterModel() {
        validator = new Validator();
    }

    public void setUserName(String userName) throws InvalidParameterException {
//        if (!validator.validateName(userName)) {
//            throw new InvalidParameterException("Invalid name");
//        }
        this.userName = userName;
    }

    public void setEmail(String email) throws InvalidParameterException {
        if (!validator.validateEmail(email)) {
            throw new InvalidParameterException("Invalid email address");
        }

        this.email = email;
    }

    public void setPassword(String password, String confirmPassword) throws InvalidParameterException {
        if(!password.equals(confirmPassword)) {
            throw new InvalidParameterException("Passwords do not match.");
        }

        if (!validator.validatePassword(password)) {
            throw new InvalidParameterException("Passwords must be between 6 and 32 characters.");
        }

        this.password = password;
    }

    public void createUser() {
        UIController.getInstance().register(userName, password, email,0);
    }
}

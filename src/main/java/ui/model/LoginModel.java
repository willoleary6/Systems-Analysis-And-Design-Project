package ui.model;

import java.security.InvalidParameterException;

public class LoginModel  {
    private Validator validator;
    private String email;
    private String password;


    public LoginModel() {
        validator = new Validator();
    }

    public void setEmail(String email) throws InvalidParameterException {
        if (!validator.validateEmail(email)) {
            throw new InvalidParameterException("Invalid email address");
        }

        this.email = email;
    }

    public void setPassword(String password) throws InvalidParameterException {
        if (!validator.validatePassword(password)) {
            throw new InvalidParameterException("Passwords must be between 6 and 32 characters.");
        }

        this.password = password;
    }

    public void getUser() {

    }
}

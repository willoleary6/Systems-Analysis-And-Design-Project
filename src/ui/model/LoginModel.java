package ui.model;

import ui.exception.InvalidEmailException;
import ui.exception.InvalidPasswordException;

public class LoginModel  {
    private Validator validator;
    private String email;
    private String password;

    public void setEmail(String email) throws InvalidEmailException {
        if (validator.validateEmail(email)) {
            throw new InvalidEmailException("Invalid email address");
        }

        this.email = email;
    }

    public void setPassword(String password) throws InvalidPasswordException {
        if (!validator.validatePassword(password)) {
            throw new InvalidPasswordException("Passwords must be between 6 and 32 characters.");
        }

        this.password = password;
    }

    public void getUser() {

    }
}

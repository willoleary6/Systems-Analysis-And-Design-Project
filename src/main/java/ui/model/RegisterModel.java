package ui.model;

import ui.exception.InvalidEmailException;
import ui.exception.InvalidNameException;
import ui.exception.InvalidPasswordException;

public class RegisterModel {
    private Validator validator;
    private String fullName;
    private String email;
    private String password;

    public RegisterModel() {
        validator = new Validator();
    }

    public void setFullName(String fullName) throws InvalidNameException {
        if (!validator.validateName(fullName)) {
            throw new InvalidNameException("Invalid name");
        }
        this.fullName = fullName;
    }

    public void setEmail(String email) throws InvalidEmailException {
        if (validator.validateEmail(email)) {
            throw new InvalidEmailException("Invalid email address");
        }

        this.email = email;
    }

    public void setPassword(String password, String confirmPassword) throws InvalidPasswordException {
        if(!password.equals(confirmPassword)) {
            throw new InvalidPasswordException("Passwords do not match.");
        }

        if (!validator.validatePassword(password)) {
            throw new InvalidPasswordException("Passwords must be between 6 and 32 characters.");
        }

        this.password = password;
    }

    public void createUser() {

    }
}

package ui.model;

import java.security.InvalidParameterException;

public class RegisterModel {
    private Validator validator;
    private String fullName;
    private String email;
    private String password;

    public RegisterModel() {
        validator = new Validator();
    }

    public void setFullName(String fullName) throws InvalidParameterException {
        if (!validator.validateName(fullName)) {
            throw new InvalidParameterException("Invalid name");
        }
        this.fullName = fullName;
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

    }
}

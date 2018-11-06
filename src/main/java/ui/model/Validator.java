package ui.model;

public class Validator {

    public boolean validateEmail(String email) {
        return email.matches(".+@.+\\..+");
    }

    public boolean validatePassword(String password) {
        return password.matches(".{6,32}");
    }

    public boolean validateName(String name) {
        return name.matches("[a-zA-Z]+.*\\s[a-zA-Z]+.*");
    }
}

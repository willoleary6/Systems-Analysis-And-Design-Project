package control;
import account.AirlineEmployee;
import account.Customer;
import account.User;

public class UserControl {

    public UserControl(){

    }

    public User getUser(String username, String password, int userType){
        /*pull user from database*/
        if(validateUsername(username) && validatePassword(password)) {
            if(userType == 0)
                return new Customer(username, 2, userType);
            else if(userType == 1)
                return new AirlineEmployee(username, 2, userType);
        }
        return null;
    }

    public Boolean createUser(String username, String password, int userType){
        if(userType != 0 && userType != 1)
            return false;
        else if(validateUsername(username) && validatePassword(password)) {
            /*send new user to database*/
        }
        return true;
    }

    private Boolean validateUsername(String username){
        String pattern = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
        return username.matches(pattern);
    }

    private Boolean validatePassword(String password){
        String pattern = "(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,15})$";
        return password.matches(pattern);
    }


}

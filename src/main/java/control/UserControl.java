package control;
import account.SimpleUserFactory;
import account.User;

public class UserControl {

    private SimpleUserFactory userFactory;
    // simple factory pattern implemented to support extensibility of user types and to better manage dependencies


    public UserControl(){
        userFactory = new SimpleUserFactory();
    }

    public User getUser(String username, String password, int userType){
        /*pull user from database*/
        if(validateUsername(username) && validatePassword(password)) {
            return userFactory.createUser(username, 2, userType);
        }
        return null;
    }

    public User createUser(String username, String password, int userType){
        if(userType != 0 && userType != 1)
            return null;
        else if(validateUsername(username) && validatePassword(password)) {
            /*add to db*/
            return getUser(username, password, userType);
        }
        return null;
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

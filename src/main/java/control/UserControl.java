package control;

import account.SimpleUserFactory;
import account.User;
import backgroundServices.API_Handlers.getRequestHandler;
import backgroundServices.API_Handlers.insertRequestHandler;
import org.json.JSONObject;

public class UserControl {

    private SimpleUserFactory userFactory;
    // simple factory pattern implemented to support extensibility of user types and to better manage dependencies
    private getRequestHandler dbPullHandler;
    private insertRequestHandler dbInsertHandler;


    public UserControl(){
        userFactory = new SimpleUserFactory();
        dbPullHandler = new getRequestHandler();
        dbInsertHandler = new insertRequestHandler();
    }

    public User getUser(String username, String password){
        dbPullHandler.getUserInformation(username,password);
        try {
            JSONObject[] obj = dbPullHandler.getApiResponseResults();
            if(obj[0].get("password").equals(password) && obj[0].get("username").equals(username)) {
                return userFactory.createUser(obj[0]);
            }
            else {
                return null;
            }
        } catch( Exception e) {
            return null;
        }
    }

    public User createUser(String username, String password, String email, int userType){
        dbInsertHandler.addNewUser(username, email, password);
        try {
            JSONObject[] obj = dbInsertHandler.getApiResponseResults();
            return getUser(username, password);
        } catch( Exception e) {
            System.out.println(e);
            return null;
        }
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

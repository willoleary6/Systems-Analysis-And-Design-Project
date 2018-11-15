package account;


import org.json.JSONObject;

public class SimpleUserFactory {

    public SimpleUserFactory(){}

    public User createUser(JSONObject user) {
        if(user.getInt("userType") == 0) {
            return new Customer(user.getString("username"), user.getInt("userID"),
                    user.getInt("userType"), user.getInt("loyaltyPoints"));
        } else if(user.getInt("userType") == 1) {
            return new AirlineEmployee(user.getString("username"), user.getInt("userID"),
                    user.getInt("userType"), user.getInt("extendedDataInJSON"));
        } else {
            return null;
        }
    }

}

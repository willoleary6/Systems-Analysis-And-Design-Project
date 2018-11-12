package account;


public class SimpleUserFactory {

    public SimpleUserFactory(){}

    public User createUser(String username, int userID, int userType) {
        if(userType == 0) {
            return new Customer(username, userID, userType);
        } else if(userType == 1) {
            return new AirlineEmployee(username, userID, userType);
        } else {
            return null;
        }
    }

}

package account;


public class SimpleUserFactory {

    public SimpleUserFactory(){}

    public User createUser(String username, int userID, int userType) {
        if(userType == 0) {
            return new Customer(username, 2, userType);
        } else if(userType == 1) {
            return new AirlineEmployee(username, 2, userType);
        } else {
            return null;
        }
    }

}

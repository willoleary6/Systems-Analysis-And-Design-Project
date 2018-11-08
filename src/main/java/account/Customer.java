package account;

public class Customer extends User {

    private LoyaltyPoints points;

    public Customer(String userName, int userID, int userType){
        this.setUserName(userName);
        this.setUserID(userID);
        this.setUserType(userType);
        points = new LoyaltyPoints();
    }

    public LoyaltyPoints getPoints() {
        return points;
    }
}

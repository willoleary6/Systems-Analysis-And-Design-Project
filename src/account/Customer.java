package account;

public class Customer extends User {

    private LoyaltyPoints points = new LoyaltyPoints();

    Customer(String userName, int userID, int userType){
        this.setUserName(userName);
        this.setUserID(userID);
        this.setUserType(userType);
    }

    public LoyaltyPoints getPoints() {
        return points;
    }
}

package account;

public class AirlineEmployee extends User{
    private int airlineID;

    public AirlineEmployee(String userName, int employeeID, int userType, int airlineID){
        this.setUserName(userName);
        this.setUserID(employeeID);
        this.setUserType(userType);
        this.airlineID = airlineID;
    }

    public int getAirlineID() {
        return airlineID;
    }
}

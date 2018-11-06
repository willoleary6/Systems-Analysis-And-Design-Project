package account;

public class AirlineEmployee extends User{
    private String airline;
    private int employeeID;

    AirlineEmployee(String userName, int employeeID, int userType){
        this.setUserName(userName);
        this.setUserID(employeeID);
        this.setUserType(userType);
    }

}

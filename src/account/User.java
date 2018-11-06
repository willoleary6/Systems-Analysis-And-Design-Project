package account;

abstract public class User {
    private String userName;
    private int userID;
    private int userType;

    protected void setUserName(String userName){
        this.userName = userName;
    }

    protected void setUserID(int userID){
        this.userID = userID;
    }

    protected void setUserType(int userType){
        this.userType = userType;
    }

    public String getUserName(){
        return this.userName;
    }

    protected int getUserID(){
        return this.userID;
    }

    protected int getUserType(){
        return this.userType;
    }
}

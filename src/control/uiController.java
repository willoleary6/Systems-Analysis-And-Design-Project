package control;

import account.User;


public class uiController {
    public User currentUser;
    private UserControl userCon;

    public uiController(){
        userCon = new UserControl();
    }

    public void logIn(String username, String password, int userType) {
        User user = userCon.getUser(username, password, userType);
        if(user != null)
            currentUser = user;
    }

}

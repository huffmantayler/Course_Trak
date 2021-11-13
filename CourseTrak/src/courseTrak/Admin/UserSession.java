/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

/**
 *
 * @author taylorhuffman
 */
public final class UserSession {

    private static UserSession instance;

    private String userName;
    private  int userID;
    private String firstName;

    private UserSession() {
        this.firstName = firstName;
        this.userName = userName;
        this.userID = userID;
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
   
    public int getUserID() {
        return userID;
    }
    
    public void setUserID(int userID){
        this.userID = userID;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return this.firstName;
    }

    @Override
    public String toString() {
        return "UserSession{"
                + "userName = " + userName
                + "First Name = " + firstName
                + "userID = " + String.valueOf(userID);
    }
}

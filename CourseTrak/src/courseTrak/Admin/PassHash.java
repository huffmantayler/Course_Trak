/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author markduah
 */
public class PassHash {
    
    //private String password;
    
    //private String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12)); 
    
    public String getPasswordHasher(String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12)); 
        return hashedPassword;
    }
    
    public boolean checkPassword(String password, String hashedPass){
        System.out.println(hashedPass);
        return BCrypt.checkpw(password, hashedPass);
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }
    
    
}

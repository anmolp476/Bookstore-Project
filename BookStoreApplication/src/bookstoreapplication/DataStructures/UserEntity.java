/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.DataStructures;

/**
 *
 * @author LordV
 */
public abstract class UserEntity {
    private String username;
    private String password;
   
    public UserEntity(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    
    /**
     * Returns a String representation of the type of user this is
     * @return The type of user as a string
     */
    public String getUserType(){ return "User"; }
}

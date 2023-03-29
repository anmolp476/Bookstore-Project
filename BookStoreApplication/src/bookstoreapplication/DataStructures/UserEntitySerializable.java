/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.DataStructures;

import java.io.Serializable;

/**
 *
 * @author Yanny
 */
public class UserEntitySerializable implements Serializable{
    private int points; 
    private String username; 
    private String password; 
    
    public UserEntitySerializable(String username, String password, int points){
        this.username = username;
        this.password = password;
        this.points = points; 
    }
    
    public String getUsername(){
        return username; 
    }
    
    public String getPassword(){
        return password; 
    }
    
    public int getPoints(){
        return points; 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.DataStructures;

import bookstoreapplication.DataStructures.UserEntity;

/**
 *
 * @author LordV
 */
public class CustomerData extends UserEntity{
    private int points;
    public CustomerData(String username, String password, int points){
        super(username, password);
        this.points = points;
    }
    public int getPoints(){
        return points;
    }
    
    public String getStatus(){
        String status;
        if (points >= 1000){
            status = "gold";
        }
        else {
            status = "silver";
        }
        return status;
    }
}

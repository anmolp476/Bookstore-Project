/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.DataStructures;

import java.io.Serializable;

/**
 *
 * @author LordV
 */
public class CustomerData extends UserEntity implements Serializable{
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
    
    public void addPoints(int p){
        points = points + p;
    }
    
    public int removePoints(int p){
        if ((points - p) > 0){
            points = points - p;
            return 0;
        }
        else{
            int usedPoints = 0;
            while (points > 100){
                usedPoints += 100;
                points -= 100;
            }
            return usedPoints;
        }
    }
    
    /**
     * Returns a String representation of the type of user this is
     * @return The type of user as a string
     */
    @Override
    public String getUserType(){ return "User";};
}

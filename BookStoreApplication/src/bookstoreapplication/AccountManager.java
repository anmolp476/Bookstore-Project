/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import bookstoreapplication.DataStructures.CustomerData;
import bookstoreapplication.DataStructures.UserEntity;
import java.util.ArrayList;

/**
 *
 * @author Victor
 */
public class AccountManager {
    private ArrayList<UserEntity> userList;
    private static AccountManager instance;
    
    private AccountManager(){
        userList = new ArrayList<UserEntity>();
    }
    
    public static AccountManager getInstance(){
        if(instance == null){
            instance = new AccountManager();
        }
        
        return instance;
    }
    
    public ArrayList getUserList(){
        return userList;
    }
    
    public void addCustomer(String user, String pass){
        CustomerData a = new CustomerData(user, pass, 0);
        userList.add(a);
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

/**
 *
 * @author Victor
 */
public class LoginManager {
    UserEntity currentUser;

    public void setCurrentUser(UserEntity currentUser) {
        this.currentUser = currentUser;
    }
    
    public void Login(AccountManager AM){
        
    }

    public UserEntity getCurrentUser() {
        return currentUser;
    }
    
    
}

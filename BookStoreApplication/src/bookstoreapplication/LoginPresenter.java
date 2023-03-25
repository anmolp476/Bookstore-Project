/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class LoginPresenter {
    private final BookStoreApplication BSA;
    private final AccountManager accManager;
    
    public LoginPresenter(BookStoreApplication _BSA){
        BSA = _BSA;
        accManager = AccountManager.getInstance();
    }
    
    public boolean login(String username, String password) {
        if (accManager.checkUserExists(username)){
            return (accManager.getUser(username).getPassword().equals(password));
        }
        return false;
    }
    
         /**
     * A method that creates the user's presenter
     * @param username the username of the user
     * @param primaryStage the Stage that the GUI uses
     * @param LG the Viewable GUI
     * @return returns the AccountPresenter
     */
    public ApplicationGUI createApplicationGUI(String username, Stage primaryStage, Viewable LG){
        AccountGUIFactory accountGUIFactory = new AccountGUIFactory();
        return accountGUIFactory.createApplicationGUI(
                accManager.getUser(username).getUserType(), BSA, username, primaryStage, LG);
    }
}

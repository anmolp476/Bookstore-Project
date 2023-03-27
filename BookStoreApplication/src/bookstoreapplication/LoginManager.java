/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import bookstoreapplication.GUIs.ApplicationGUI;
import bookstoreapplication.GUIs.AccountGUIFactory;
import bookstoreapplication.DataStructures.OwnerData;
import bookstoreapplication.DataStructures.CustomerData;
import bookstoreapplication.DataStructures.UserEntity;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class LoginManager {

    private final BookStoreApplication BSA;
    private final AccountManager accManager;
    private ApplicationGUI appState;
    private UserEntity currentUser;

    public UserEntity getCurrentUser() {
        return currentUser;
    }
    

    public LoginManager(BookStoreApplication _BSA) {
        BSA = _BSA;
        accManager = BSA.getAccountManager();
        currentUser = null;
    }

    public boolean login(String username, String password) {
        if (accManager.checkUserExists(username)) {
            boolean LogInSuccess = (accManager.getUser(username).getPassword().equals(password));
            System.out.println(accManager.getUser(username).getUsername() + ", has signed in");
            if (LogInSuccess){
                currentUser = accManager.getUser(username);
                System.out.println(currentUser.getUsername() + ", has been saved to currentUser");
            }
            else{
                
            System.out.println(accManager.getUser(username).getUsername() + ", has NOT signed in");
                currentUser = null;
            }
            return (accManager.getUser(username).getPassword().equals(password));
        }
        return false;
    }

    /**
     * Creates a new user with username, password, and display name
     *
     * @param username the name of the user
     * @param password the password used to login to the conference
     * @param displayName the display name of the user
     * @param type the type of user the user wants to be
     * @return returns true iff the user is successfully created
     */
    public boolean signUp(String username, String password) {
        if (accManager.checkUserExists(username) || username.equals("")
                || password.equals("")) {
            return false;
        } else {
            signUpNewUser(username, password, "User");
            return true;
        }
        
    }

     private void signUpNewUser(String username, String password, String type) {
        switch (type) {
            case "User":
                accManager.addCustomer(username, password);
                break;
            case "Owner":
                accManager.addOwner(username, password);
                break;


        }
        BSA.getLocalFileGateway().saveAllFiles();
    }
     
    /**
     * A method that creates the user's presenter
     *
     * @param username the username of the user
     * @param primaryStage the Stage that the GUI uses
     * @param LG the Viewable GUI
     * @return returns the AccountPresenter
     */
    public ApplicationGUI createApplicationGUI(String username, Stage primaryStage, Viewable LG) {
        AccountGUIFactory accountGUIFactory = new AccountGUIFactory();
        appState = accountGUIFactory.createApplicationGUI(accManager.getUser(username).getUserType(), BSA, this, username, primaryStage, LG);
        return appState;
    }

    public void shutdownSequence() {
        System.out.println("calling fileGateway");
        BSA.getLocalFileGateway().saveAllFiles();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import bookstoreapplication.DataStructures.OwnerData;
import bookstoreapplication.DataStructures.CustomerData;
import bookstoreapplication.DataStructures.UserEntity;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class LoginPresenter {

    private final BookStoreApplication BSA;
    private final AccountManager accManager;

    public LoginPresenter(BookStoreApplication _BSA) {
        BSA = _BSA;
        accManager = AccountManager.getInstance();
    }

    public boolean login(String username, String password) {
        if (accManager.checkUserExists(username)) {
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
        return accountGUIFactory.createApplicationGUI(
                accManager.getUser(username).getUserType(), BSA, username, primaryStage, LG);
    }

    public void shutdownSequence() {
        System.out.println("calling fileGateway");
        BSA.getLocalFileGateway().saveAllFiles();
    }
}

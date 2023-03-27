/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication.GUIs;

import bookstoreapplication.LoginManager;
import bookstoreapplication.BookStoreApplication;
import bookstoreapplication.Viewable;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class AccountGUIFactory {
    
               /**
     * Takes a selection from user input and selects the kind of AccountController to construct.
     * @param selection The input from the user
     * @param CC the controller for the conference
     * @param username the user that the controller is being made for
     * @return returns a new AccountController
     */
    public ApplicationGUI createApplicationGUI(String selection, BookStoreApplication BSA, LoginManager LM, String username,
                                                   Stage primaryStage, Viewable LG) {
        switch (selection) {
            case "Owner":
                ApplicationGUI attGUI = new OwnerGUI();
                return attGUI;
            case "User":
                ApplicationGUI cGUI = new CustomerGUI(LM);
                return cGUI;
            default:
                return null;
        }
    }
}

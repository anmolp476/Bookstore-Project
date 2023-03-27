/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import bookstoreapplication.GUIs.LoginGUI;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Victor
 */
public class main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bookstore Application");
        
        BookStoreApplication BSA = new BookStoreApplication();
        
        LoginGUI loginGUI = new LoginGUI();
        LoginManager loginPresenter = new LoginManager(BSA);
        loginGUI.setLoginPresenter(loginPresenter);
        
        
        loginGUI.accessUI(primaryStage);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}

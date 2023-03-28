/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
/**
 *
 * @author Victor
 */
public class BookStoreApplication implements Viewable {

    private final FileGateway gateway;
    private final AccountManager accManager;
    private final BookManager bookManager;
    private final CartManager cartManager;

    public BookManager getBookManager() {
        return bookManager;
    }
    
    
    public BookStoreApplication() {
        this.gateway = new FileGateway();;
        accManager = AccountManager.getInstance();
        
        //Loadfiles from gateway
        accManager.loadUserList(gateway.readUserFile());
        this.bookManager = new BookManager(gateway.readBookFile2());
        this.cartManager = new CartManager();
    }
    
    
    public void accessUI(Stage primaryStage) {
        
    }

    public AccountManager getAccountManager() {
        return accManager;
    }

    
    public FileGateway getLocalFileGateway(){
        return gateway;
    }
    
    public CartManager getCartManager(){
        return cartManager;
    }
    

    
}
